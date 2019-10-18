package com.zhliang.springboot.rabbitmq.user;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author: colin
 * @Date: 2019/8/10 14:12
 * @Description:
 * @Version: V1.0
 */
@Data
public class User implements Serializable {

    /**
     * 对象传输：
     * 1. 可以使用序列化，反序列化的方式进行传输
     * 2. 实体类在项目中的位置必须一致，即类路径一致。序列号一致
     * 3. 目前RabbitMQ支持 直接传输对象的方式，前提是必须实现序列化接口
     */


    private static final long serialVersionUID = 6354853958628450214L;

    private String username;
    private String password;

}
