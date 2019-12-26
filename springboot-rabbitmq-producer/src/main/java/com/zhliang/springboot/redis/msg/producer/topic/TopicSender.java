package com.zhliang.springboot.redis.msg.producer.topic;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Author: colin
 * @Date: 2019/8/7 11:36
 * @Description:
 * @Version: V1.0
 */
@Component
public class TopicSender {

    @Autowired
    private AmqpTemplate template;

    /**
     * 方法的第一个参数：交换机名称
     *      第二个参数：发送的绑定key
     *      第三个参数：发送的内容
     *
     */
    public void send(){
        template.convertAndSend("exchange","topic.message","hello , rabbit topic 1");
        template.convertAndSend("exchange","topic.messages","hello , rabbit topic 2");
    }
}
