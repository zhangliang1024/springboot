package com.zhliang.springboot.redis.msg.producer.direct;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Author: colin
 * @Date: 2019/8/6 19:19
 * @Description:
 * @Version: V1.0
 */
@Component
public class DirectSender {


    @Autowired
    private AmqpTemplate template;

    public void send(){
        template.convertAndSend("queue","hello, rabbit MQ");
    }

}
