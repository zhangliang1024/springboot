package com.zhliang.springboot.custom.starter.annotation;

import com.zhliang.springboot.custom.starter.autoconfigure.GreetingAutoConfiguration;
import com.zhliang.springboot.custom.starter.autoconfigure.HelloAutoConfiguration;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * @Author: colin
 * @Date: 2019/12/20 11:33
 * @Description:
 * @Version: V1.0
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Import({GreetingAutoConfiguration.class, HelloAutoConfiguration.class})
@Documented
@Inherited
public @interface EnableCustomAuto {
}
