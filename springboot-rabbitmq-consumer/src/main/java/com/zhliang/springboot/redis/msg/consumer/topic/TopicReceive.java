package com.zhliang.springboot.redis.msg.consumer.topic;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @Author: colin
 * @Date: 2019/8/7 11:25
 * @Description:
 * @Version: V1.0
 */
@Slf4j
@Component
public class TopicReceive {

    @RabbitHandler
    @RabbitListener(queues = "topic.message")
    public void processMessage(String message){
        log.info("[MQ topic 测试接受消息 message队列]：{}",message);
    }


    @RabbitHandler
    @RabbitListener(queues = "topic.messages")
    public void processMessages(String message){
        log.info("[MQ topic 测试接受消息 messages队列]：{}",message);
    }
}
