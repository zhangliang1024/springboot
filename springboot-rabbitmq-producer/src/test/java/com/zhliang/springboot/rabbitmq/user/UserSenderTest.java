package com.zhliang.springboot.rabbitmq.user;

import com.zhliang.springboot.rabbitmq.producer.SpringbootRabbitmqProducerApplicationTests;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.*;

public class UserSenderTest extends SpringbootRabbitmqProducerApplicationTests {




    @Autowired
    private UserSender sender;
    @Test
    public void send() {
        sender.send();
    }
}