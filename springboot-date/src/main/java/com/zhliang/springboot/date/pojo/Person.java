package com.zhliang.springboot.date.pojo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.zhliang.springboot.date.annotation.DateFormat;
import com.zhliang.springboot.date.serializer.DateTimeSerializer;
import lombok.Data;

import java.util.Date;

/**
 * @Author: colin
 * @Date: 2019/9/3 14:21
 * @Description:
 * @Version: V1.0
 */
@Data
public class Person {

    private Long id;

    private String name;

    private Date createTime;

    //@JsonSerialize(using = DateTimeSerializer.class)
    private Date updateTime;

    @DateFormat
    public Date getCreateTime() {
        return this.createTime;
    }

    @DateFormat("yyyy-MM-dd HH:mm:ss")
    public Date getUpdateTime() {
        return this.updateTime;
    }
}
