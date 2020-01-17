package com.zhliang.springboot.mq.kafka;

import lombok.Data;

import java.util.Date;

/**
 * @项目名称：springboot
 * @包名：com.zhliang.springboot.mq.kafka
 * @类描述：
 * @创建人：colin
 * @创建时间：2020/1/15 15:15
 * @version：V1.0
 */
@Data
public class Message {

    private String id;
    private String msg;
    private Date sendTime;

}
