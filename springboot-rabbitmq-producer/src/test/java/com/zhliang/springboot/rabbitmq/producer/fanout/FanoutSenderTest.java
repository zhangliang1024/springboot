package com.zhliang.springboot.rabbitmq.producer.fanout;

import com.zhliang.springboot.rabbitmq.producer.SpringbootRabbitmqProducerApplicationTests;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.*;

public class FanoutSenderTest extends SpringbootRabbitmqProducerApplicationTests {

    @Autowired
    private FanoutSender sender;
    @Test
    public void send() {
        sender.send();
    }
}