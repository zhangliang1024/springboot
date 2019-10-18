package com.zhliang.springboot.redisson.aspect;
import java.lang.annotation.*;

/**
 * @Author: colin
 * @Date: 2019/9/10 11:14
 * @Description:
 * @Version: V1.0
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RedissonLock {


    /**
     * 要锁那个参数
     * @return
     */
    int lockIndex() default -1;


    /**
     * 锁多久后释放：单位/秒
     * @return
     */
    int leaseTime() default 10;
}
