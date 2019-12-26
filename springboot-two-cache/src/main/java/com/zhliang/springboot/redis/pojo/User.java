package com.zhliang.springboot.redis.pojo;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author: colin
 * @Date: 2019/10/9 17:28
 * @Description:
 * @Version: V1.0
 */
@Data
public class User implements Serializable {

    private int id;
    private String name;
    private int age;


    public User(){}

    public User(int id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }
}
