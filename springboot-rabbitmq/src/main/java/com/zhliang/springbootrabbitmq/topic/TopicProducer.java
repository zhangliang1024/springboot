package com.zhliang.springbootrabbitmq.topic;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Author: colin
 * @Date: 2019/8/6 15:11
 * @Description: topic 主体是Rabbitmq最灵活的一种方式：可以根据binding_key自由绑定不同的队列
 * @Version: V1.0
 */
@Slf4j
@Component
public class TopicProducer {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void send(){
        String msg = "I am topic.message msg  ===";
        log.info("[topic.message] sender 1 :{}",msg);
        rabbitTemplate.convertAndSend("topic.message.A",msg);

        String msg2 = "I am topic.message msg 2 ===";
        log.info("[topic.message] sender 1 :{}",msg2);
        rabbitTemplate.convertAndSend("topic.message.B",msg2);

    }

}
