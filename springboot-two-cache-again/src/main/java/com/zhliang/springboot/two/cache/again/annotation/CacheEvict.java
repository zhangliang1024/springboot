package com.zhliang.springboot.two.cache.again.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Author: colin
 * @Date: 2019/12/6 14:31
 * @Description:
 * @Version: V1.0
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface CacheEvict {

    String value() default "";

    String key() default "";

}
