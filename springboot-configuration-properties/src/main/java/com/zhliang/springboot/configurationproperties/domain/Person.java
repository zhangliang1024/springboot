package com.zhliang.springboot.configurationproperties.domain;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * @Author: colin
 * @Date: 2019/8/21 14:39
 * @Description:
 * @Version: V1.0
 */
@Data
@Component
@ConfigurationProperties(prefix = "person")
public class Person {

    private String name;
    private int age;
    private String sex;

    private Map<String , Object> maps;
    private List<Dog> list;

    private Dog dog;
}
