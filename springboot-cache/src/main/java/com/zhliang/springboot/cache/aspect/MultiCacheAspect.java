package com.zhliang.springboot.cache.aspect;

import com.zhliang.springboot.cache.annotation.MyCacheable;
import com.zhliang.springboot.cache.cache.LocalCacheService;
import com.zhliang.springboot.cache.cache.RedisCacheService;
import com.zhliang.springboot.cache.utils.JsonUtil;
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
import org.springframework.beans.factory.annotation.Value;
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
 */
@Aspect
@Component
public class MultiCacheAspect {

    private static final Logger logger = LoggerFactory.getLogger(MultiCacheAspect.class);

    @Value("${spring.cache.enable:false}")
    private boolean cacheEnable;

    @Autowired
    private LocalCacheService localService;
    @Autowired
    private RedisCacheService redisService;


    @Pointcut("@annotation(com.zhliang.springboot.cache.annotation.MyCacheable)")
    public void cacheableAspect() {
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
        MyCacheable ca = method.getAnnotation(MyCacheable.class);
        //获得经过el解析后的key值
        String key = parseKey(ca.key(),method,args);
        boolean second = ca.isSecond();
        int expire = ca.expire();

        try {
            //直接从redis获取
            if(second){
                String value = redisService.get(key);
                if(StringUtils.isEmpty(value)) {
                    result = joinPoint.proceed(args);
                    value = serialize(result);
                    redisService.set(key,value,expire);
                }else {
                    result = deserialize(value, returnType);
                }
            }else {
                //走二级缓存获取数据
                String value = localService.get(key);
                if(StringUtils.isEmpty(value)) {
                    value = redisService.get(key);
                    if(StringUtils.isEmpty(value)){
                        result = joinPoint.proceed(args);
                        value = serialize(result);
                        redisService.set(key,value,expire);
                    }else {
                        result = deserialize(value, returnType);
                    }
                    localService.set(key,value);
                }else {
                    result = deserialize(value, returnType);
                }
            }
        } catch (Throwable throwable) {
            logger.error("",throwable);
        }
        return result;
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
}
