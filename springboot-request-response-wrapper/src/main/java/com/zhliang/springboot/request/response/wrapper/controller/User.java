package com.zhliang.springboot.request.response.wrapper.controller;

/**
 * @创建人：zhiang
 * @创建时间：2020/5/28 18:56
 * @version：V1.0
 */

public class User {

    private String name;

    public User(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
