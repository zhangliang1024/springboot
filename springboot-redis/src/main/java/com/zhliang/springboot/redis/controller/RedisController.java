package com.zhliang.springboot.redis.controller;

import com.zhliang.springboot.redis.config.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Author: colin
 * @Date: 2019/10/9 14:23
 * @Description:
 * @Version: V1.0
 */
@Slf4j
@RestController
public class RedisController {


    @Resource
    private RedisUtil redisUtil;


    @PostMapping("setKey")
    public boolean setKey(String key,String value){
        log.info("Redis set key : {} value : {}",key,value);
        return redisUtil.set(key,value);
    }


    @GetMapping("getKey")
    public String getKey(String key){
        log.info("Reids get key : {} ",key);
        return (String)redisUtil.get(key);
    }

    @GetMapping("expire")
    public boolean expire(String key){
        return redisUtil.expire(key,60);
    }


    @GetMapping("exists")
    public boolean exists(String key){
        return redisUtil.hasKey(key);
    }






}
