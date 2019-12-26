package com.zhliang.springboot.custom.starter;

import com.zhliang.springboot.custom.starter.service.GreetingService;
import com.zhliang.springboot.custom.starter.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @项目名称：springboot
 * @包名：com.zhliang.springboot.custom.starter
 * @类描述：
 * @创建人：colin
 * @创建时间：2019/12/20 14:12
 * @version：V1.0
 */
@RestController
public class HelloController {

    @Autowired
    private GreetingService service;

    @GetMapping("sayHello")
    public String hello() {
        service.sayHello();
        return "OK";
    }

    @Autowired
    private HelloService helloService;

    @GetMapping
    public String testHello(String name) {
        return helloService.sayHello(name);
    }
}
