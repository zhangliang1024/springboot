package com.zhliang.springboot.date.controller;

import com.zhliang.springboot.date.pojo.Person;
import com.zhliang.springboot.date.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: colin
 * @Date: 2019/9/3 17:17
 * @Description:
 * @Version: V1.0
 */
@RestController
public class PersonController {

    @Autowired
    private PersonService service;


    @GetMapping("person")
    public Person person(){
        return service.getPerson();
    }
}
