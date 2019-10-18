package com.zhliang.springbootrabbitmq.fanout;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Author: colin
 * @Date: 2019/8/6 15:42
 * @Description: Fanout就是我们熟知的广播模式或者订阅模式，给Fanout转发器发送消息，绑定了这个转发器的队列都能接受到这个消息
 * @Version: V1.0
 */
@Slf4j
@Component
public class FanoutProducer {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void sendFanout(){
        String msg = "fanout send : hello i am fanout";
        log.info("[Fanout send msg] :{}",msg);
        rabbitTemplate.convertAndSend("fanout.exchange","fanout.*",msg);
    }


}
