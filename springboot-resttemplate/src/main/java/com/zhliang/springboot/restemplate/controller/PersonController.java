package com.zhliang.springboot.restemplate.controller;

import com.zhliang.springboot.restemplate.domain.Person;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: colin
 * @Date: 2019/8/29 14:09
 * @Description:
 * @Version: V1.0
 */
@Slf4j
@RestController
public class PersonController {


    @PostMapping("/post-for-object")
    public String postForObjectC(@RequestBody Person p){
        log.info(" Person {}", p);
        return "post for object success";
    }


    @PostMapping("/get-for-object")
    public String getForObjectC(String param1,String param2){
        log.info(" Param1 {} ; Param2 {}", param1,param2);
        return "get for object success";
    }
}
