package com.zhliang.springboot.cache.service;


import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class HelloServiceTest {

    @Autowired
    private HelloService service;

    @Test
    public void queryAll() {
        List<String> list = this.service.queryAll("service");
        System.out.println(list);
    }
}