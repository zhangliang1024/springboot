package com.zhliang.springboot.cache.annotation;

import java.lang.annotation.*;

/**
 * @Date: 2020/9/15 15:50
 * 残缺版：
 *  注解指定是否开启二级缓存
 *  如果使用功二级缓存，默认不做数据同步处理，使用自定义的过期时间来到达数据一致性
 *  如果不使用二级缓存，则所有的缓存均保存在Redis一级缓存中。
 */
@Inherited
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface MyCacheable {

    /**
     * 缓存cache名称
     */
    String value() default "";
    /**
     * 缓存指定key
     */
    String key() default "";
    /**
     * 缓存过期时间
     */
    int expire() default 60;
    /**
     * 是否开启二级缓存,默认不开启
     */
    boolean isSecond() default false;
}
