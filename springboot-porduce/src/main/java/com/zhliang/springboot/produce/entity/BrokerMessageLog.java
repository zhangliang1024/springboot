package com.zhliang.springboot.produce.entity;

import lombok.Data;

import java.util.Date;

/**
 * @Author: colin
 * @Date: 2019/9/23 16:22
 * @Description:
 * @Version: V1.0
 */
@Data
public class BrokerMessageLog {

    private String messageId;
    private String message;
    private Integer tryCount;
    private String status;
    private Date nextRetry;
    private Date createTime;
    private Date updateTime;


}
