package com.zhliang.springboot.redis.msg.producer.topic;

import com.zhliang.springboot.redis.msg.producer.SpringbootRabbitmqProducerApplicationTests;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class TopicSenderTest extends SpringbootRabbitmqProducerApplicationTests {

    @Autowired
    private TopicSender sender;

    @Test
    public void send() {
        sender.send();
    }

}