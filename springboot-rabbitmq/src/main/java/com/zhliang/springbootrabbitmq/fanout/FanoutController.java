package com.zhliang.springbootrabbitmq.fanout;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: colin
 * @Date: 2019/8/6 15:56
 * @Description:
 * @Version: V1.0
 */
@Slf4j
@RestController
public class FanoutController {

    @Autowired
    private FanoutProducer producer;


    @GetMapping("fanout")
    public void sendFanout(){
        producer.sendFanout();
    }

}
