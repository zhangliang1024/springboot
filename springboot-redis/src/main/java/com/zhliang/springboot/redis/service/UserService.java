package com.zhliang.springboot.redis.service;

import com.zhliang.springboot.redis.pojo.User;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @Author: colin
 * @Date: 2019/10/9 17:30
 * @Description:
 *  springboot+redis实现缓存数据
 *   -  https://www.cnblogs.com/hhhshct/p/9002648.html
 * @Version: V1.0
 */
@Service
public class UserService {

    // @Cacheable缓存key为name的数据到缓存usercache中
    @Cacheable(value = "usercache", key = "#p0")
    public User findUser(String name) {
        System.out.println("无缓存时执行下面代码，获取zhangsan,Time：" + new Date().getSeconds());
        return new User(1, "zhangsan", 13);// 模拟从持久层获取数据
    }

    // @CacheEvict从缓存usercache中删除key为name的数据
    @CacheEvict(value = "usercache", key = "#p0")
    public void removeUser(String name) {
        System.out.println("删除数据" + name + "，同时清除对应的缓存");
    }

    // @CachePut缓存新增的数据到缓存usercache中
    @CachePut(value = "usercache", key = "#p0")
    public User save(String name, int id) {
        System.out.println("添加lisi用户");
        return new User(2, "lisi", 13);
    }

    @Cacheable(value = "usercache", key = "#p0")
    public User findUser2(String name) {
        System.out.println("无缓存时执行下面代码，获取lisi,Time：" + new Date().getSeconds());
        return new User(2, "lisi", 13);// 模拟从持久层获取数据
    }


}
