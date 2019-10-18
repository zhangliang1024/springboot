package com.zhliang.springbootrabbitmq.callback;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * @Author: colin
 * @Date: 2019/8/6 15:58
 * @Description:
 * @Version: V1.0
 */
@Slf4j
@Component
public class CallBackProducer implements RabbitTemplate.ConfirmCallback {


    @Autowired
    private RabbitTemplate rabbitTemplate;


    public void send(){
        rabbitTemplate.setConfirmCallback(this);
        String msg = "Callback Producer : i am callback producer";
        log.info("[Callback Producer msg] :{}",msg);
        CorrelationData correlationData = new CorrelationData(UUID.randomUUID().toString());
        rabbitTemplate.convertAndSend("topic.exchange","topic.message",msg,correlationData);
    }

    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String s) {
        log.info("[消息唯一ID]：{}",correlationData.getId());
        log.info("[消息发送状态]：{}",ack);
    }
}
