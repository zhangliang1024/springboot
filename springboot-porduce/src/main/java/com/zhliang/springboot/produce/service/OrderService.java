package com.zhliang.springboot.produce.service;

import com.alibaba.fastjson.JSONObject;
import com.zhliang.springboot.produce.constant.Constants;
import com.zhliang.springboot.produce.entity.BrokerMessageLog;
import com.zhliang.springboot.produce.entity.Order;
import com.zhliang.springboot.produce.mapper.BrokerMessageLogMapper;
import com.zhliang.springboot.produce.mapper.OrderMapper;
import com.zhliang.springboot.produce.produce.RabbitOrderSender;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @Author: colin
 * @Date: 2019/9/24 08:39
 * @Description:
 * @Version: V1.0
 */
@Service
public class OrderService {

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private BrokerMessageLogMapper brokerMessageLogMapper;

    @Autowired
    private RabbitOrderSender rabbitOrderSender;


    /**
     * 创建订单
     * @param order
     */
    public void createOrder(Order order){
        //当前时间作为订单的创建时间
        Date orderTime = new Date();
        //插入业务数据
        orderMapper.insert(order);

        //插入消息记录
        BrokerMessageLog messageLog = new BrokerMessageLog();
        messageLog.setMessageId(order.getMessageId());
        //消息为： 实体对象
        messageLog.setMessage(JSONObject.toJSONString(order));
        //状态为 发送中
        messageLog.setStatus("0");
        //设置消息未确认的 超时时间窗口为 1分钟
        messageLog.setNextRetry(DateUtils.addMinutes(orderTime, Constants.ORDER_TIMEOUT));

        messageLog.setCreateTime(new Date());
        messageLog.setUpdateTime(new Date());

        brokerMessageLogMapper.insert(messageLog);

        //发送消息
        rabbitOrderSender.sendOrder(order);

    }

}
