package com.zhliang.springboot.login.control.service;

import com.zhliang.springboot.login.control.pojo.UserBO;
import com.zhliang.springboot.login.control.utils.JWTUtil;
import org.redisson.api.RBucket;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.concurrent.TimeUnit;

/**
 * @Author: colin
 * @Date: 2019/10/21 10:15
 * @Description:
 * @Version: V1.0
 */
@Service
public class UserService {

    @Autowired
    private RedissonClient redissonClient;

    public String buildUserInfo(UserBO user) {
        String username = user.getUsername();
        String jwt = JWTUtil.sign(username, JWTUtil.SECRET);
        Assert.notNull(jwt, "jwt cannot null");
        RBucket rBucket = redissonClient.getBucket(jwt);
        rBucket.set(user, JWTUtil.EXPIRE_TIME_MS, TimeUnit.MILLISECONDS);
        return jwt;
    }

    public void logout(String jwt) {
        RBucket rBucket = redissonClient.getBucket(jwt);
        rBucket.delete();
    }


}
