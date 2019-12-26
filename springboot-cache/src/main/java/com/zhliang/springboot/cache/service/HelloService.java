package com.zhliang.springboot.cache.service;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

/**
 * @项目名称：springboot
 * @包名：com.zhliang.springboot.cache.service
 * @类描述：
 * @创建人：colin
 * @创建时间：2019/11/27 19:24
 * @version：V1.0
 */
@Service
public class HelloService {

    @Cacheable(value = "emp" ,key = "targetClass + methodName +#p0")
    public List<String> queryAll(String str) {
        System.out.println("====>>>> 请求进入 request: " + str);
        return Arrays.asList("hello","world",str);
    }



}
