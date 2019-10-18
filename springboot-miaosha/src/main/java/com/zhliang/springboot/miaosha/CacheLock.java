package com.zhliang.springboot.miaosha;

import java.lang.annotation.*;

/**
 * @Author: colin
 * @Date: 2019/9/5 15:59
 * @Description:
 * @Version: V1.0
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface CacheLock {

    /**
     * redis 锁的前缀
     */
    String lockedPrefix() default "";

    /**
     * 锁时间
     */
    long timeOut() default 2000;

    /**
     * key 在redis中存在的时间 1000S
     */
    int expireTime() default 100000;
}




