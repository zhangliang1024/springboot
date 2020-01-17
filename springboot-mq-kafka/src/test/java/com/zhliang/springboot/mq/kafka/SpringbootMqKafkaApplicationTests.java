package com.zhliang.springboot.mq.kafka;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.bind.annotation.RequestMapping;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootMqKafkaApplicationTests {


    @Autowired
    private Producer producer;

    public String send() {
        producer.send();
        return "{\"code\":0}";
    }


    @Test
    public void contextLoads() {
        send();
    }

}
