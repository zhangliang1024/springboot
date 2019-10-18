package com.zhliang.springboot.date.annotation;

import java.lang.annotation.*;

/**
 * @Author: colin
 * @Date: 2019/9/3 10:17
 * @Description: 自定义日期格式化注解
 * @Version: V1.0
 */
@Target({ElementType.FIELD,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DateFormat {

    //默认 日期格式化形式
    String value() default "yyyy-MM-dd";
}


