package com.zhliang.springboot.miaosha;
import	java.lang.annotation.RetentionPolicy;
import	java.lang.annotation.Retention;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

/**
 * @Author: colin
 * @Date: 2019/9/5 15:56
 * @Description:
 * @Version: V1.0
 */
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface LockedComplexObject {

    /**
     * 含有成员变量的复杂对象中需要加锁的成员变量  如：一个商品对象的商品ID
     */
    String field() default "";
}
