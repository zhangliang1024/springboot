package com.zhliang.springbootrabbitmq.more2more;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: colin
 * @Date: 2019/8/6 11:21
 * @Description:
 * @Version: V1.0
 */
@Slf4j
@RestController
public class HelloM2MController {

    @Autowired
    private Producer1 producer1;
    @Autowired
    private Producer2 producer2;


    @GetMapping("more/2/more")
    public void send(){
        for (int i = 0; i < 10; i++) {
            producer1.sned("hello-msg: " + i);
            producer2.sned("hello-msg: " + i);
        }
    }
}
