package com.zhliang.springboot.miaosha;

import lombok.extern.slf4j.Slf4j;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @Author: colin
 * @Date: 2019/9/5 16:25
 * @Description:
 * @Version: V1.0
 */
@Slf4j
public class CacheLockInterceptor implements InvocationHandler {

    public static int ERROR_COUNT = 0;
    private Object proxied;


    public CacheLockInterceptor(Object proxied){
        this.proxied = proxied;
    }


    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        CacheLock cacheLock = method.getAnnotation(CacheLock.class);

        //没加注解 说明不需要加锁直接pass
        if(cacheLock == null){
            return method.invoke(proxied,args);
        }

        //获得方法中参数的注解
        Annotation[][] annotations = method.getParameterAnnotations();
        //根据获取到的参数注解和参数列表获取加锁的参数
        Object lockObject = getLockObject(annotations,args);
        String objectValue = lockObject.toString();

        RedisLock lock = new RedisLock(cacheLock.lockedPrefix(), objectValue);
        boolean result = lock.lock(cacheLock.timeOut(), cacheLock.expireTime());
        if(!result){
            ERROR_COUNT += 1;
            log.warn("获取锁失败：{}",ERROR_COUNT);
            //throw new CacheLockException("get lock fail");
        }
        try {
            return method.invoke(proxied,args);
        }finally {
            //释放锁
            lock.unLock();
        }
    }

    /**
     * 从方法中找出 @lockedComplexOnbject的参数，在redis中取该参数对应的锁
     * @param annotations
     * @param args
     * @return
     * @throws CacheLockException
     */
    private Object getLockObject(Annotation[][] annotations, Object[] args) throws CacheLockException{
        if(args == null || args.length == 0){
            throw new CacheLockException("方法参数为空，没有被锁定的对象");
        }
        if(annotations == null || annotations.length == 0){
            throw new CacheLockException("没有被注解的参数");
        }

        int index = -1;
        for (int i = 0; i < annotations.length; i++) {
            for (int j = 0; j < annotations [i].length; j++) {
                // 注解为 LockedComplexObject
                if(annotations [i] [j] instanceof LockedComplexObject){
                    index = i;
                    try{
                        return args[i].getClass().getField(((LockedComplexObject)annotations [i] [j]).field());
                    }catch (Exception e){
                        throw new CacheLockException("注解对象中没有该属性" + ((LockedComplexObject)annotations[i][j]).field());
                    }
                }
                if(annotations [i] [j] instanceof LockedObject){
                    index = i;
                    break;
                }
            }
            //找到第一个后 直接跳出，不支持多参数加锁
            if(index != -1){
                break;
            }
        }
        //没有找到加锁的参数
        if(index == -1){
            throw new CacheLockException("请指定被锁定参数");
        }
        return args[index];
    }
}
