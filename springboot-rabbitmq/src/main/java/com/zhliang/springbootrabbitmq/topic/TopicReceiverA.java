package com.zhliang.springbootrabbitmq.topic;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @Author: colin
 * @Date: 2019/8/6 15:27
 * @Description:
 * @Version: V1.0
 */
@Slf4j
@Component
@RabbitListener(queues = "topic.message.A")
public class TopicReceiverA {

    @RabbitHandler
    public void process(String msg){
        log.info("[topic] 接受消息：{}",msg);
    }
}

