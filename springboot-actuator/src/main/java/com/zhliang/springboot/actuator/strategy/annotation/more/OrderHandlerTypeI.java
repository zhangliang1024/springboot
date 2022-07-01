package com.zhliang.springboot.actuator.strategy.annotation.more;

import org.springframework.stereotype.Service;

import java.lang.annotation.*;

/**
 * 注解：就是一种特殊的接口
 * 定义注解来标识：某个类是用来处理何种来源的订单
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Service
public @interface OrderHandlerTypeI {

    String source();

    String payMethod();
}



