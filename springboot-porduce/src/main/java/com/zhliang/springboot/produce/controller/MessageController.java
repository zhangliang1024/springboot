package com.zhliang.springboot.produce.controller;

import com.zhliang.springboot.produce.entity.Order;
import com.zhliang.springboot.produce.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

/**
 * @Author: colin
 * @Date: 2019/9/24 11:59
 * @Description:
 * @Version: V1.0
 */
@RestController
public class MessageController {


    @Autowired
    private OrderService orderService;


    @GetMapping("/send/message")
    public void testCreateOrder() {
        Order order = new Order();
        order.setId(String.valueOf(UUID.randomUUID()));
        order.setName("测试创建订单");
        order.setMessageId(System.currentTimeMillis()+"$"+ UUID.randomUUID().toString());
        orderService.createOrder(order);
    }
}
