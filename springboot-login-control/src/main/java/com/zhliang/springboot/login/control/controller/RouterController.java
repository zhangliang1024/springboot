package com.zhliang.springboot.login.control.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @Author: colin
 * @Date: 2019/10/21 10:28
 * @Description:
 * @Version: V1.0
 */
@Controller
public class RouterController {

    @GetMapping("login.html")
    public String login() {
        return "login";
    }

    @GetMapping("index.html")
    public String index() {
        return "index";
    }

    @GetMapping("/")
    public String _login() {
        return "login";
    }
}