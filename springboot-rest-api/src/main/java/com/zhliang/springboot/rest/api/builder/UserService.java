package com.zhliang.springboot.rest.api.builder;

import org.springframework.stereotype.Service;

/**
 * @项目名称：springboot
 * @包名：com.zhliang.springboot.rest.api.builder
 * @类描述：
 * @创建人：colin
 * @创建时间：2020/1/6 16:31
 * @version：V1.0
 */
@Service
public class UserService {

    public User getUser(){
        User user = User.builder().username("admin").password("abcd1234").build();
        return user;
    }

}
