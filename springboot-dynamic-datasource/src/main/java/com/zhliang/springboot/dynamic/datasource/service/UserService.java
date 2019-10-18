package com.zhliang.springboot.dynamic.datasource.service;

import com.zhliang.springboot.dynamic.datasource.entity.User;
import com.zhliang.springboot.dynamic.datasource.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: colin
 * @Date: 2019/9/25 19:22
 * @Description:
 * @Version: V1.0
 */
@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    public User get(String targetSource) {
        return userMapper.selectByPrimaryKey(1);
    }
}
