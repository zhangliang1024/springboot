package com.zhliang.springboot.actuator.strategy.autowiredsms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: zhliang
 * @Date: 2020/5/20 14:41
 * @Description:
 * @Version: V1.0
 */
@RestController
public class SmsSendController {

    @Autowired
    private SmsSendService  sendService;

    @GetMapping("send")
    public String sendSms(){
        sendService.send("1888888888","send msg");
        return "send success!";
    }

}




