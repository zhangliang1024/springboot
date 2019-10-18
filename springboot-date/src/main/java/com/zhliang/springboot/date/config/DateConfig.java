package com.zhliang.springboot.date.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zhliang.springboot.date.introspector.MyAnnotationIntrospector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;


/**
 * @Author: colin
 * @Date: 2019/9/3 14:16
 * @Description:
 * 【利用注解自定义日期格式化： https://blog.csdn.net/yiifaa/article/details/77972171】
 * @Version: V1.0
 */
@Configuration
public class DateConfig {

    @Autowired
    private MyAnnotationIntrospector annotationIntrospector;

    @Bean
    public MappingJackson2HttpMessageConverter messageConverter(){
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        converter.setObjectMapper(objectMapper());
        return converter;
    }

    @Bean
    public ObjectMapper objectMapper(){
        ObjectMapper mapper = new ObjectMapper();
        mapper.setAnnotationIntrospector(annotationIntrospector);
        return mapper;
    }


   /* @Bean
    public Jackson2ObjectMapperFactoryBean factoryBean(){
        Jackson2ObjectMapperFactoryBean factoryBean = new Jackson2ObjectMapperFactoryBean();
        factoryBean.setAnnotationIntrospector(annotationIntrospector);
        return factoryBean;
    }*/
}
