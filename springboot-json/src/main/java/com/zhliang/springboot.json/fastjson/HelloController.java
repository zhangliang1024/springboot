package com.zhliang.springboot.json.fastjson;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;


/**
 * @Author: colin
 * @Date: 2019/8/22 10:08
 * @Description:
 * @Version: V1.0
 */
@Api("JSON自定义序列化框架")
@RestController
public class HelloController {


    @ApiOperation("person")
    @GetMapping("person")
    public Person person(){
        Person p = new Person();
        p.setId(122000083049775104L);
        p.setEmail("abc@126.com");
        p.setName("hello");
        p.setPassword("password");
        p.setCreateTime(new Date());
        return p;
    }


    @ApiOperation("save Person")
    @PostMapping("save")
    public void save(@RequestBody Person person){
        System.out.println(person);
    }

}
