package com.zhliang.springboot.mockmvc.service;

import com.zhliang.springboot.mockmvc.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @Author: colin
 * @Date: 2019/8/16 10:58
 * @Description:
 * @Version: V1.0
 */
@Slf4j
@Service
public class UserService {

    public User findUserById(Integer id) {
        User user = new User();
        user.setId(1);
        user.setUsername("zhangsan");
        user.setPassword("abcd1234");
        return user;
    }

    public String save(User user) {
      log.info("User is :{}",user);
      return "SUCCESS";
    }

    public String updateUser(User user) {
        log.info("User is :{}",user);
        return "SUCCESS";
    }
}
