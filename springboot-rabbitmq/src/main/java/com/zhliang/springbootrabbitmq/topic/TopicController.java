package com.zhliang.springbootrabbitmq.topic;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: colin
 * @Date: 2019/8/6 15:30
 * @Description:
 * @Version: V1.0
 */
@Slf4j
@RestController
public class TopicController {

    @Autowired
    private TopicProducer producer;


    @GetMapping("topic")
    public void sendTopic(){
        producer.send();
    }

}
