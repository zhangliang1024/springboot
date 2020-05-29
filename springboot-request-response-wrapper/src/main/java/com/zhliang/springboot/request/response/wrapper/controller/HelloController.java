package com.zhliang.springboot.request.response.wrapper.controller;

import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

/**
 * @创建人：zhiang
 * @创建时间：2020/5/28 17:45
 * @version：V1.0
 */
@RestController
@RequestMapping("advice")
@Api(tags = "用户管理相关接口")
public class HelloController {

    @ApiOperation("添加用户")
    @PostMapping("/hello1")
    public String hello(@RequestBody User user){
        return "hello 1:"+ user.getName();
    }

    @ApiOperation("接口")
    @GetMapping("/hello2")
    public String hello(@RequestParam("name") String name){
        return "hello 2:"+name;
    }
}
