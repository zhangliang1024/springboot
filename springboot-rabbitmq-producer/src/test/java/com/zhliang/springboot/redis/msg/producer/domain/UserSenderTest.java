package com.zhliang.springboot.redis.msg.producer.domain;

import com.zhliang.springboot.redis.msg.producer.SpringbootRabbitmqProducerApplicationTests;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class UserSenderTest extends SpringbootRabbitmqProducerApplicationTests {

    @Autowired
    private UserSender sender;

    @Test
    public void send() {
        sender.send();
    }
}