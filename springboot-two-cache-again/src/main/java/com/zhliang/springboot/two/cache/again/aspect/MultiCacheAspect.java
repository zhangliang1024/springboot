package com.zhliang.springboot.two.cache.again.aspect;

import com.zhliang.springboot.two.cache.again.annotation.CacheEvict;
import com.zhliang.springboot.two.cache.again.annotation.Cacheable;
import com.zhliang.springboot.two.cache.again.config.CacheFactory;
import com.zhliang.springboot.two.cache.again.utils.JsonUtil;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.LocalVariableTableParameterNameDiscoverer;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.lang.reflect.Method;

/**
 * @类描述：多级缓存切面
 * @创建时间：2019/12/6 14:31
 * springboot中使用自定义两级缓存的方法：
 *  https://www.jb51.net/article/140683.htm
 */
@Aspect
@Component
public class MultiCacheAspect {

    private static final Logger logger = LoggerFactory.getLogger(MultiCacheAspect.class);

    @Autowired
    private CacheFactory cacheFactory;

    //这里通过一个容器初始化监听器，根据外部配置的@EnableCaching注解控制缓存开关
    private boolean cacheEnable;

    @Pointcut("@annotation(com.zhliang.springboot.two.cache.again.annotation.Cacheable)")
    public void cacheableAspect() {
    }

    @Pointcut("@annotation(com.zhliang.springboot.two.cache.again.annotation.CacheEvict)")
    public void cacheEvict() {
    }

    @Around("cacheableAspect()")
    public Object cache(ProceedingJoinPoint joinPoint) {

        //得到被切面修饰的方法的参数列表
        Object[] args = joinPoint.getArgs();
        // result是方法的最终返回结果
        Object result = null;
        //如果没有开启缓存，直接调用处理方法返回
        if(!cacheEnable){
            try {
                result = joinPoint.proceed(args);
            } catch (Throwable e) {
                logger.error("",e);
            }
            return result;
        }

        // 得到被代理方法的返回值类型
        Class returnType = ((MethodSignature) joinPoint.getSignature()).getReturnType();
        // 得到被代理的方法
        Method method = ((MethodSignature) joinPoint.getSignature()).getMethod();
        // 得到被代理的方法上的注解
        Cacheable ca = method.getAnnotation(Cacheable.class);
        //获得经过el解析后的key值
        String key = parseKey(ca.key(),method,args);
        Class<?> elementClass = ca.type();
        //从注解中获取缓存名称
        String name = ca.value();

        try {
            //先从ehcache中取数据
            String cacheValue = cacheFactory.ehGet(name,key);
            if(StringUtils.isEmpty(cacheValue)) {
                //如果ehcache中没数据，从redis中取数据
                cacheValue = cacheFactory.redisGet(name,key);
                if(StringUtils.isEmpty(cacheValue)) {
                    //如果redis中没有数据
                    // 调用业务方法得到结果
                    result = joinPoint.proceed(args);
                    //将结果序列化后放入redis
                    cacheFactory.redisPut(name,key,serialize(result));
                } else {
                    //如果redis中可以取到数据
                    //将缓存中获取到的数据反序列化后返回
                    if(elementClass == Exception.class) {
                        result = deserialize(cacheValue, returnType);
                    } else {
                        result = deserialize(cacheValue, returnType,elementClass);
                    }
                }
                //将结果序列化后放入ehcache
                cacheFactory.ehPut(name,key,serialize(result));
            } else {
                //将缓存中获取到的数据反序列化后返回
                if(elementClass == Exception.class) {
                    result = deserialize(cacheValue, returnType);
                } else {
                    result = deserialize(cacheValue, returnType,elementClass);
                }
            }

        } catch (Throwable throwable) {
            logger.error("",throwable);
        }

        return result;
    }

    /**
     * 在方法调用前清除缓存，然后调用业务方法
     * @param joinPoint
     * @return
     * @throws Throwable
     *
     */
    @Around("cacheEvict()")
    public Object evictCache(ProceedingJoinPoint joinPoint) throws Throwable {
        // 得到被代理的方法
        Method method = ((MethodSignature) joinPoint.getSignature()).getMethod();
        //得到被切面修饰的方法的参数列表
        Object[] args = joinPoint.getArgs();
        // 得到被代理的方法上的注解
        CacheEvict ce = method.getAnnotation(CacheEvict.class);
        //获得经过el解析后的key值
        String key = parseKey(ce.key(),method,args);
        //从注解中获取缓存名称
        String name = ce.value();
        // 清除对应缓存
        cacheFactory.cacheDel(name,key);
        return joinPoint.proceed(args);
    }

    /**
     * 获取缓存的key
     * key 定义在注解上，支持SPEL表达式
     * @return
     */
    private String parseKey(String key,Method method,Object [] args){

        if(StringUtils.isEmpty(key)) return null;

        //获取被拦截方法参数名列表(使用Spring支持类库)
        LocalVariableTableParameterNameDiscoverer u = new LocalVariableTableParameterNameDiscoverer();
        String[] paraNameArr = u.getParameterNames(method);

        //使用SPEL进行key的解析
        ExpressionParser parser = new SpelExpressionParser();
        //SPEL上下文
        StandardEvaluationContext context = new StandardEvaluationContext();
        //把方法参数放入SPEL上下文中
        for(int i=0;i<paraNameArr.length;i++){
            context.setVariable(paraNameArr[i], args[i]);
        }
        return parser.parseExpression(key).getValue(context,String.class);
    }

    //序列化
    private String serialize(Object obj) {

        String result = null;
        try {
            result = JsonUtil.serialize(obj);
        } catch(Exception e) {
            result = obj.toString();
        }
        return result;

    }

    //反序列化
    private Object deserialize(String str,Class clazz) {

        Object result = null;
        try {
            if(clazz == JSONObject.class) {
                result = new JSONObject(str);
            } else if(clazz == JSONArray.class) {
                result = new JSONArray(str);
            } else {
                result = JsonUtil.deserialize(str,clazz);
            }
        } catch(Exception e) {
        }
        return result;

    }

    //反序列化,支持List<xxx>
    private Object deserialize(String str,Class clazz,Class elementClass) {

        Object result = null;
        try {
            if(clazz == JSONObject.class) {
                result = new JSONObject(str);
            } else if(clazz == JSONArray.class) {
                result = new JSONArray(str);
            } else {
                result = JsonUtil.deserialize(str,clazz,elementClass);
            }
        } catch(Exception e) {
        }
        return result;

    }

    public void setCacheEnable(boolean cacheEnable) {
        this.cacheEnable = cacheEnable;
    }
}
