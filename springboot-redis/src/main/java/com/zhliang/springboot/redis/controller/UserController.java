package com.zhliang.springboot.redis.controller;

import com.zhliang.springboot.redis.pojo.User;
import com.zhliang.springboot.redis.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: colin
 * @Date: 2019/10/9 17:30
 * @Description:
 * @Version: V1.0
 */
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/finduser1")
    public User finduser1() {
        return userService.findUser("zhangsan");
    }

    @GetMapping("/finduser2")
    public User finduser2() {
        return userService.findUser2("lisi");
    }

    @GetMapping("/adduser2")
    public User adduser2() {
        return userService.save("lisi", 13);
    }

    @GetMapping("/delete")
    public void removeUser() {
        userService.removeUser("zhangsan");
    }
}
