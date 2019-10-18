package com.zhliang.springbootrabbitmq.fanout;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @Author: colin
 * @Date: 2019/8/6 15:54
 * @Description:
 * @Version: V1.0
 */
@Slf4j
@Component
@RabbitListener(queues = "fanout.B")
public class FanoutReceiverB {


    @RabbitHandler
    public void process(String message){
        log.info("[MQ Fanout receiver msg] :{}",message);
    }
}
