package com.zhliang.springboot.mq.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @项目名称：springboot
 * @包名：com.zhliang.springboot.mq.kafka
 * @类描述：
 * @创建人：colin
 * @创建时间：2020/1/15 15:42
 * @version：V1.0
 */
@RestController
@RequestMapping("/kafka")
public class SendController {

    @Autowired
    private Producer producer;

    @RequestMapping(value = "/send")
    public String send() {
        producer.send();
        return "{\"code\":0}";
    }
}
