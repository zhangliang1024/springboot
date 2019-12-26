package com.zhliang.springboot.custom.starter.service;

import com.zhliang.springboot.custom.starter.autoconfigure.GreetingProperties;
import com.zhliang.springboot.custom.starter.autoconfigure.HelloProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @项目名称：springboot
 * @包名：com.zhliang.springboot.custom.starter.service
 * @类描述：问候service
 * @创建人：colin
 * @创建时间：2019/11/18 13:47
 * @version：V1.0
 */
public class GreetingService {

    GreetingProperties properties;

    GreetingProperties getProperties() {
        return properties;
    }

    public void setProperties(GreetingProperties properties) {
        this.properties = properties;
    }


    public void sayHello(){
        properties.getMembers().forEach(s -> System.out.println("hello " + s));
    }
}
