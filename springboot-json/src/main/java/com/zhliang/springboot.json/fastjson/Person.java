package com.zhliang.springboot.json.fastjson;

import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.serializer.SerializerFeature;
import lombok.Data;

import java.util.Date;

/**
 * @Author: colin
 * @Date: 2019/8/21 18:46
 * @Description:
 * @Version: V1.0
 */
@Data
public class Person {

    private Long id;

    @JSONField(name = "username")
    private String name;


    @JSONField(serialzeFeatures = SerializerFeature.WriteMapNullValue)
    private String email;

    /**
     * @JSONField(serialize = false) 此字段在序列化时，不返回
     */
    @JSONField(serialize = false)
    private String password;

    /**
     * 默认：毫秒格式 1566871652668
     * 指定时间格式：@JSONField(format = "yyyy-MM-dd HH:mm:ss")
     */
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;




    public Person(){
    }

    public Person(String name){
        this.name = name;
    }
    public Person(Long id,String name){
        this.id = id;
        this.name = name;
    }

    public Person(Long id,String name,String email,String password){
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
    }
}
