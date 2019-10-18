package com.zhliang.springboot.produce.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author: colin
 * @Date: 2019/9/23 16:18
 * @Description:
 * @Version: V1.0
 */
@Data
public class Order implements Serializable {

    private String id;
    private String name;

    /*存储消息的唯一Id*/
    private String messageId;

    public Order(){
        super();
    }

    public Order(String id,String name,String messageId){
        super();
        this.id = id;
        this.name = name;
        this.messageId = messageId;
    }
}
