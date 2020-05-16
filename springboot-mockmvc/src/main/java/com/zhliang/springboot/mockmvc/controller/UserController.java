package com.zhliang.springboot.mockmvc.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zhliang.springboot.mockmvc.entity.User;
import com.zhliang.springboot.mockmvc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 * @Author: colin
 * @Date: 2019/8/16 10:58
 * @Description:
 * @Version: V1.0
 */
@RestController
@RequestMapping("/v1/user")
public class UserController {

    @Autowired
    private UserService service;

    @GetMapping("{id}/detail")
    public String getUserDetail(@PathVariable("id") Integer id) throws Exception{
        User user = service.findUserById(id);
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(user);
    }

    @PostMapping("save")
    public String saveUser(@RequestBody User user) throws Exception{
        return service.save(user);
    }

    @PutMapping("put")
    public String updateUser(@RequestBody User user) throws Exception{
        return service.updateUser(user);
    }
}
