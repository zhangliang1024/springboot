package com.zhliang.springbootrabbitmq.controller;

import com.zhliang.springbootrabbitmq.complex.OneToManyProducer;
import com.zhliang.springbootrabbitmq.simple.Producer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: colin
 * @Date: 2019/8/2 20:40
 * @Description:
 * @Version: V1.0
 */
@RestController
public class RabbitController {


    @Autowired
    private Producer producer;
    @Autowired
    private OneToManyProducer oneToManyProducer;


    @GetMapping("/one")
    public void sendOne(){
        producer.send("hello");
    }

    @GetMapping("/oneToMany")
    public void oneToMany(){
        for (int i = 0; i < 10; i++) {
            oneToManyProducer.send("helloMsg: " + i);
        }
    }
}
