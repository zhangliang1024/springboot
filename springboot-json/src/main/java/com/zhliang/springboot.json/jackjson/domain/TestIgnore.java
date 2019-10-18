package com.zhliang.springboot.json.jackjson.domain;

import lombok.Data;

import java.util.List;

/**
 * @Author: colin
 * @Date: 2019/8/22 12:01
 * @Description:
 * @Version: V1.0
 */
@Data
public class TestIgnore {

    private String test;
    //@JsonIgnore
    private List<User> userList;

}
