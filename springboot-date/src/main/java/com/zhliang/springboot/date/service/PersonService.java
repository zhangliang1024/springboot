package com.zhliang.springboot.date.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zhliang.springboot.date.pojo.Person;
import com.zhliang.springboot.date.introspector.MyAnnotationIntrospector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @Author: colin
 * @Date: 2019/9/3 14:24
 * @Description:
 * @Version: V1.0
 */
@Service
public class PersonService {

    @Autowired
    MyAnnotationIntrospector myAnnotationIntrospector;

    public void person() throws JsonProcessingException {
        Person p = new Person();
        p.setId(1L);
        p.setName("pp");
        p.setCreateTime(new Date());
        p.setUpdateTime(new Date());


        System.out.println(p);
        ObjectMapper mapper = new ObjectMapper();
        mapper.setAnnotationIntrospector(myAnnotationIntrospector);
        String json = mapper.writeValueAsString(p);
        System.out.println(json);
    }

    public Person getPerson()  {
        Person p = new Person();
        p.setId(1L);
        p.setName("pp");
        p.setCreateTime(new Date());
        p.setUpdateTime(new Date());
        return p;
    }
}
