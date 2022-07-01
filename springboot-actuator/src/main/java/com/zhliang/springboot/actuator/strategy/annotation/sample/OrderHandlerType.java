package com.zhliang.springboot.actuator.strategy.annotation.sample;

import org.springframework.stereotype.Service;

import java.lang.annotation.*;

/**
 * 定义注解来标识：某个类是用来处理何种来源的订单
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Service
public @interface OrderHandlerType {

    String source();
}



