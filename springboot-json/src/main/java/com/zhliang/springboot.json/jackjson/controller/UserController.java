package com.zhliang.springboot.json.jackjson.controller;
import java.io.IOException;
import	java.text.SimpleDateFormat;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationConfig;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.zhliang.springboot.json.jackjson.domain.TestIgnore;
import com.zhliang.springboot.json.jackjson.domain.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Author: colin
 * @Date: 2019/8/22 11:57
 * @Description:
 * @Version: V1.0
 */
@Api("UserController")
@RestController
public class UserController {

    @ApiOperation(value = "反序列化")
    @GetMapping("stream")
    public User stream() throws IOException {
        String request = "{\"id\":\"122000083049775104\",\"createTime\":1566619753,\"name\":\"zhangsan\"," +
                "\"nick\":\"wo\"}";
        ObjectMapper mapper = new ObjectMapper();
        User user = mapper.readValue(request, User.class);
        return user;
    }


    @ApiOperation(value = "使用自定义序列化")
    @GetMapping("jackson")
    public String result() throws JsonProcessingException {
        User user = new User();
        user.setId(122000083049775104L);
        user.setUsername("zhangsan");
        user.setNickname("wo");
        user.setCreateTime(new Date());
        ObjectMapper mapper = new ObjectMapper();
        //放弃jackson默认的时间格式
        mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        //使用自定义时间格式
        mapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
        String response = mapper.writeValueAsString(user);
        return response;
    }

    @ApiOperation("user")
    @GetMapping("user")
    public User user() throws JsonProcessingException {
        User user = new User();
        user.setId(122000083049775104L);
        user.setUsername("zhangsan");
        user.setNickname("wo");
        user.setCreateTime(new Date());
        return user;
    }

    @ApiOperation("userList")
    @GetMapping("list")
    public TestIgnore userList(){
        TestIgnore test = new TestIgnore();
        test.setTest("test");
        User user = new User();
        user.setId(122000083049775104L);
        user.setUsername("username");
        user.setNickname("nickname");
        user.setCreateTime(new Date());
        List list = new ArrayList();
        list.add(user);
        test.setUserList(list);
        return test;
    }
}
