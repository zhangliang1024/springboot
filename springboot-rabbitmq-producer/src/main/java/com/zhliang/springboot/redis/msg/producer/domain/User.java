package com.zhliang.springboot.redis.msg.producer.domain;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author: colin
 * @Date: 2019/8/6 19:30
 * @Description:
 * @Version: V1.0
 */
@Data
public class User implements Serializable {

    private String username;
    private String password;

}
