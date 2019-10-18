package com.zhliang.springboot.redisson.aspect;
import	java.util.concurrent.TimeUnit;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @Author: colin
 * @Date: 2019/9/10 11:17
 * @Description:
 * @Version: V1.0
 */
@Aspect
//@Component
//@Order(1)
@Slf4j
public class RedissonLockAspect {

    @Resource
    private Redisson redisson;

    /*@Pointcut("execution(* com.zhliang.springboot.redisson.aspect.*(..))")
    private void pointCut(){}*/


    @Around("@annotation(redissonLock)")
    public Object around(ProceedingJoinPoint point,RedissonLock redissonLock) throws Throwable {
        Object obj = null;
        //方法内的所有参数
        Object[] params = point.getArgs();

        int lockIndex = redissonLock.lockIndex();

        //获取方法名
        String methodName = point.getSignature().getDeclaringTypeName() + "." + point.getSignature().getName();

        // lockIndex = -1 代表锁住整个方法;而非具体哪条数据
        if(lockIndex != -1){
            methodName += params[lockIndex];
        }

        //多久会自动释放：默认 10秒
        int leaseTime = redissonLock.leaseTime();
        int waitTime = 5;

        RLock lock = redisson.getLock(methodName);
        boolean result = lock.tryLock(waitTime, leaseTime, TimeUnit.SECONDS);
        if (result) {
            log.info("取到锁");
            obj = point.proceed();
            //释放锁
            lock.unlock();
        }else {
            log.error("获取锁失败");
        }

        return obj;
    }
}
