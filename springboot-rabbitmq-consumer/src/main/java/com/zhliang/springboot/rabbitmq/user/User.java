package com.zhliang.springboot.rabbitmq.user;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author: colin
 * @Date: 2019/8/10 14:10
 * @Description:
 * @Version: V1.0
 */
@Data
public class User implements Serializable {


    private static final long serialVersionUID = 6354853958628450214L;

    private String username;
    private String password;

}
