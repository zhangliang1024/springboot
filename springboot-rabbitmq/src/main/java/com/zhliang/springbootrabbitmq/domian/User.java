package com.zhliang.springbootrabbitmq.domian;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author: colin
 * @Date: 2019/8/6 14:21
 * @Description:
 * @Version: V1.0
 */
@Data
public class User implements Serializable {

    private String name; 
    private String pass;

}
