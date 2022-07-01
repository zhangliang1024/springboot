package com.zhliang.springboot.actuator.strategy.annotation.sample;

import com.zhliang.springboot.actuator.strategy.annotation.OrderHandler;
import com.zhliang.springboot.actuator.strategy.annotation.po.Order;
import com.zhliang.springboot.actuator.strategy.annotation.sample.OrderHandlerType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 通过set注入方式向spring的IOC容器中注入各种订单处理的handler,并在orderHandler方法中，通过策略模式来执行具体的handle
 */
@Service
public class OrderService {

    /**
     * 维护订单来源与具体的执行策略Map
     */
    private Map<String, OrderHandler>  orderHandlerMap;

    /**
     * 通过set注入实现
     */
    @Autowired
    public void setOrderHandlerMap(List<OrderHandler> orderHandlers){
        orderHandlerMap = orderHandlers.stream().collect(
               Collectors.toMap(orderHandler -> AnnotationUtils.findAnnotation(
                       orderHandler.getClass(), OrderHandlerType.class).source(), V -> V ,(V1, V2) -> V1)
       );
    }

    public void orderHandler(Order order){
        //一些前置处理
        OrderHandler handler = orderHandlerMap.get(order.getSource());
        handler.handle(order);
        //一些后置处理
    }
}
