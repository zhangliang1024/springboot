package com.zhliang.springboot.rabbitmq.producer.fanout;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Author: colin
 * @Date: 2019/8/9 10:47
 * @Description:
 * @Version: V1.0
 */
@Slf4j
@Component
public class FanoutSender {

    @Autowired
    private AmqpTemplate template;


    public void send(){
        log.info("[MQ 测试Fanout发送消息]");
        template.convertAndSend("fanout.exchange","","hello , Rabbit i am fanout message!");
    }

}
