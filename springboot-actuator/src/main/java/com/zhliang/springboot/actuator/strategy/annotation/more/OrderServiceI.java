package com.zhliang.springboot.actuator.strategy.annotation.more;

import com.zhliang.springboot.actuator.strategy.annotation.OrderHandler;
import com.zhliang.springboot.actuator.strategy.annotation.more.OrderHandlerTypeImpl;
import com.zhliang.springboot.actuator.strategy.annotation.po.Order;
import com.zhliang.springboot.actuator.strategy.annotation.sample.OrderHandlerType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @项目名称：springboot
 * @包名：com.zhliang.springboot.actuator.strategy.annotation
 * @类描述：
 * @创建人：zhiang
 * @创建时间：2020/9/14 19:34
 * @version：V1.0
 */
@Service
public class OrderServiceI {

    private Map<OrderHandlerType, OrderHandler> orderHandlerMap;

    /**
     * 通过set注入实现
     */
    @Autowired
    public void setOrderHandlerMap(List<OrderHandler> orderHandlers){
        orderHandlerMap = orderHandlers.stream().collect(
                Collectors.toMap(orderHandler -> AnnotationUtils.findAnnotation(
                        orderHandler.getClass(),OrderHandlerType.class),V -> V ,(V1,V2) -> V1)
        );
    }

    public void orderHandler(Order order){
        //一些前置处理
        OrderHandlerTypeImpl handlerType = new OrderHandlerTypeImpl(order.getSource(),order.getPayMethod());
        OrderHandler handler = orderHandlerMap.get(handlerType);
        handler.handle(order);
        //一些后置处理
    }
}
