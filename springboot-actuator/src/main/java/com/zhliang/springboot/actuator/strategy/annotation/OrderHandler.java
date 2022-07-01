package com.zhliang.springboot.actuator.strategy.annotation;


import com.zhliang.springboot.actuator.strategy.annotation.po.Order;

/**
 * 用注解代替IF-ELSE：https://www.jianshu.com/p/d55674caf6cf
 * 业务场景：
 * 1. 模拟不同来源(PC、移动端)的订单要实现不同的处理逻辑
 * 2. 模拟业务要求通过：订单来源+支付方式，来决定使用支付逻辑
 *    如：
 *     PC支付宝    PCAliPayOrderHandler
 *     PC微信      PCWeChatOrderHandler
 *     移动端支付宝 MobileAliPayOrderHandler
 *     移动端微信： MobileWeChatOrderHandler
 *
 * 定义处理订单的接口
 */
public interface OrderHandler {

    /**
     * 处理订单接口
     */
    void handle(Order order);
}
