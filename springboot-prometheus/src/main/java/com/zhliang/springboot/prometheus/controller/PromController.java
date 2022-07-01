package com.zhliang.springboot.prometheus.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @项目名称：springboot
 * @包名：com.zhliang.springboot.prometheus.controller
 * @类描述：
 * @创建人：zhiang
 * @创建时间：2021/8/13 15:51
 * @version：V1.0
 */
@RestController
public class PromController {

    @GetMapping("/")
    public String index(){
        return "Hello Prometheus";
    }
}
