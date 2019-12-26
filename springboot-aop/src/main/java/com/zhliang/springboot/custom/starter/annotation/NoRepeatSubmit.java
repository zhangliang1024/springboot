package com.zhliang.springboot.custom.starter.annotation;
import	java.lang.annotation.RetentionPolicy;
import	java.lang.annotation.Retention;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

/**
 * @Author: colin
 * @Date: 2019/9/25 15:24
 * @Description:
 * @Version: V1.0
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface NoRepeatSubmit {

    /**
     * 设置请求锁定时间
     */
    int lockTime() default 10;
}
