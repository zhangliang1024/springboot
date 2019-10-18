package com.zhliang.springboot.rabbitmq.producer.topic;

import com.zhliang.springboot.rabbitmq.producer.SpringbootRabbitmqProducerApplicationTests;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.*;

public class TopicSenderTest extends SpringbootRabbitmqProducerApplicationTests {

    @Autowired
    private TopicSender sender;

    @Test
    public void send() {
        sender.send();
    }

}