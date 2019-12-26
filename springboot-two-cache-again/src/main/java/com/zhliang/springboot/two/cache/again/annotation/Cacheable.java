package com.zhliang.springboot.two.cache.again.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Author: colin
 * @Date: 2019/12/6 14:30
 * @Description: 自定义注解：
 *  - 为了不要侵入springboot原本使用缓存的方式
 * @Version: V1.0
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Cacheable {

    String value() default "";

    String key() default "";

    //泛型的Class类型
    Class<?> type() default Exception.class;

}
