package com.zhliang.springboot.cache.controller;

import com.zhliang.springboot.cache.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @项目名称：springboot
 * @包名：com.zhliang.springboot.cache.controller
 * @类描述：
 * @创建人：colin
 * @创建时间：2019/11/27 19:31
 * @version：V1.0
 */
@RestController
public class HelloController {

    @Autowired
    private HelloService service;

    @GetMapping("/hello/cache")
    public List<String> helloCache(String str) {
        return service.query(str);
    }

    @GetMapping("/hello/cache/all")
    public List<String> helloCacheAll(String str) {
        return service.queryAll(str);
    }

}
