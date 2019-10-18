package com.zhliang.springboot.rabbitmq.producer.domain;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Author: colin
 * @Date: 2019/8/6 19:32
 * @Description:
 * @Version: V1.0
 */
//@Component
public class UserSender {

    @Autowired
    private AmqpTemplate template;

    public void send(){
        User user = new User();
        user.setUsername("zhangsan");
        user.setPassword("abcd1234");
        template.convertAndSend("user",user);
    }

}
