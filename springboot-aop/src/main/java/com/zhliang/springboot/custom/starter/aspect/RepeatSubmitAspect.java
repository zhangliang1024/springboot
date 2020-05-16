package com.zhliang.springboot.custom.starter.aspect;

import com.google.common.cache.Cache;
import com.zhliang.springboot.custom.starter.annotation.NoRepeatSubmit;
import com.zhliang.springboot.custom.starter.redis.RedisLock;
import com.zhliang.springboot.custom.starter.utils.RequestUtils;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

/**
 * @Author: colin
 * @Date: 2019/9/25 15:45
 * @Description:
 * @Version: V1.0
 */
@Slf4j
@Aspect
@Component
public class RepeatSubmitAspect {

    @Autowired
    private RedisLock redisLock;

    @Autowired
    private Cache<String, Integer> cache;

    @Pointcut("@annotation(noRepeatSubmit)")
    public void pointCut(NoRepeatSubmit noRepeatSubmit){}


    @Around("pointCut(noRepeatSubmit)")
    public Object around(ProceedingJoinPoint point,NoRepeatSubmit noRepeatSubmit) throws Throwable {
        //获取加锁时间
        int seconds = noRepeatSubmit.lockTime();
        HttpServletRequest request = RequestUtils.getRequest();
        Assert.notNull(request,"request can not null ...");

        //获取 token或sessionId
        String authorization = request.getHeader("Authorization");
        //获取 请求路径
        String path = request.getServletPath();
        //组装 redis key : token + path
        String key = getKey(authorization,path);
        //当前请求唯一标识：clientId
        String clientId = getClientId();

        //本地缓存方案
        //cachePlan(clientId);

        boolean success = redisLock.tryLock(key, clientId, seconds);
        log.info("tryLock key = [{}],clientId = [{}]",key,clientId);

        if(success){
            //获取锁成功
            try{
                Object obj = point.proceed();
                return obj;
            }finally{
                //解锁
                redisLock.releaseLock(key,clientId);
            }
        }else {
            //获取锁失败，认为是重复的请求
            log.warn("repeat request ...");
            return null;
        }
    }

    /**
     * 使用本地缓存来代替redis
     * 如果缓存中有这个url视为重复提交
     */
    private boolean cachePlan(String key) {
        if (cache.getIfPresent(key) == null) {
            cache.put(key, 0);
            return true;
        } else {
            log.error("重复提交");
            return false;
        }
    }

    private String getClientId() {
        return UUID.randomUUID().toString();
    }

    private String getKey(String authorization, String path) {
        return path + authorization;
    }


}
