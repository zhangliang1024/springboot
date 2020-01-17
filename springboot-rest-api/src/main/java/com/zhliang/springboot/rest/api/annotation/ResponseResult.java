package com.zhliang.springboot.rest.api.annotation;


import java.lang.annotation.*;

/**
 * @项目名称：springboot
 * @包名：com.zhliang.springboot.rest.api.annotation
 * @类描述：
 * @创建人：colin
 * @创建时间：2020/1/6 10:22
 * @version：V1.0
 */
@Target({ElementType.TYPE,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ResponseResult {

}
