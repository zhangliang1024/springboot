package com.zhliang.springbootrabbitmq.simple;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @Author: colin
 * @Date: 2019/8/1 17:46
 * @Description:
 * @Version: V1.0
 */
@Slf4j
@Component
@RabbitListener(queues = "hello")
public class Receiver {

    @RabbitHandler
    public void process(String msg){
        log.info("【队列接受到的消息是】：{}",msg);
    }
}
