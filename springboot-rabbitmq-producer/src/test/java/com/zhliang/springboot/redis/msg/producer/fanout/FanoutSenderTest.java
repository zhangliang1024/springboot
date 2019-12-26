package com.zhliang.springboot.redis.msg.producer.fanout;

import com.zhliang.springboot.redis.msg.producer.SpringbootRabbitmqProducerApplicationTests;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class FanoutSenderTest extends SpringbootRabbitmqProducerApplicationTests {

    @Autowired
    private FanoutSender sender;
    @Test
    public void send() {
        sender.send();
    }
}