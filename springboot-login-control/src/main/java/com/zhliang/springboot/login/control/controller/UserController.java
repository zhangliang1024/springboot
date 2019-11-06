package com.zhliang.springboot.login.control.controller;

import com.zhliang.springboot.login.control.pojo.ApiResult;
import com.zhliang.springboot.login.control.pojo.CurrentUser;
import com.zhliang.springboot.login.control.pojo.UserBO;
import com.zhliang.springboot.login.control.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Author: colin
 * @Date: 2019/10/21 10:27
 * @Description:
 * @Version: V1.0
 */
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("login")
    public ApiResult login(@RequestBody UserBO userBO) {
        return new ApiResult(200, "登录成功", userService.buildUserInfo(userBO));
    }

    @GetMapping("user/info")
    public ApiResult info() {
        return new ApiResult(200, null, CurrentUser.get());
    }

    @PostMapping("logout")
    public ApiResult logout(@RequestHeader("Authorization") String jwt) {
        userService.logout(jwt);
        return new ApiResult(200, "成功", null);
    }

}
