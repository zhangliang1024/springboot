package com.zhliang.springboot.actuator.strategy.annotation.sample;

import com.zhliang.springboot.actuator.strategy.annotation.OrderHandler;
import com.zhliang.springboot.actuator.strategy.annotation.po.Order;
import org.springframework.stereotype.Service;

/**
 */
@Service
@OrderHandlerType(source = "pc")
public class PCOrderHandler implements OrderHandler {

    @Override
    public void handle(Order order) {
        System.out.println("处理PC端订单");
    }
}
