package com.zhliang.springbootrabbitmq.domian;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: colin
 * @Date: 2019/8/6 15:04
 * @Description:
 * @Version: V1.0
 */
@Slf4j
@RestController
public class UserController {


    @Autowired
    private UserProducer userProducer;


    @GetMapping("/user")
    public void sendUser(){
        userProducer.sendPO();
    }
}
