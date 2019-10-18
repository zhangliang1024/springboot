package com.zhliang.springboot.produce.produce;

import com.zhliang.springboot.produce.constant.Constants;
import com.zhliang.springboot.produce.entity.Order;
import com.zhliang.springboot.produce.mapper.BrokerMessageLogMapper;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @Author: colin
 * @Date: 2019/9/23 18:34
 * @Description:
 * @Version: V1.0
 */
@Component
public class RabbitOrderSender {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private BrokerMessageLogMapper brokerMessageLogMapper;

    //回调函数：confirm 确认
     final RabbitTemplate.ConfirmCallback confirmCallback = new RabbitTemplate.ConfirmCallback() {

        @Override
        public void confirm(CorrelationData correlationData, boolean ack, String cause) {
            System.err.println("correlationData:"+correlationData);
            String messageId = correlationData.getId();
            if(ack) {
                //如果confirm返回成功 则进行更新
                brokerMessageLogMapper.changeBrokerMessageLogStatus(messageId, Constants.ORDER_SEND_SUCCESS, new Date());
            }else {
                //失败则进行具体的后续操作：重试或者补偿等手段
                System.err.println("异常处理。。。");
            }
        }
    };

     //回调：returnCallback
    final RabbitTemplate.ReturnCallback returnCallback = new RabbitTemplate.ReturnCallback() {
        @Override
        public void returnedMessage(org.springframework.amqp.core.Message message, int replyCode, String replyText,
                                    String exchange, String routingKey) {
            System.err.println("return exchange: " + exchange + ", routingKey: "
                    + routingKey + ", replyCode: " + replyCode + ", replyText: " + replyText);
        }
    };

    /**
     * 发送消息方法 构建自定义消息对象
     * @param order
     */
    public void sendOrder(Order order){
        //监听回调对象
        rabbitTemplate.setConfirmCallback(confirmCallback);
        rabbitTemplate.setReturnCallback(returnCallback);
        //消息唯一ID
        CorrelationData correlationData = new CorrelationData(order.getMessageId());

        rabbitTemplate.convertAndSend("order-exchange","order.ABC",order,correlationData);
    }

    public void send(String exchange, String routingKey, Object message) {
        rabbitTemplate.convertAndSend(exchange, routingKey, message);
    }

}
