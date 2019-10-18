package com.zhliang.springboot.date.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zhliang.springboot.date.introspector.MyAnnotationIntrospector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.List;

/**
 * @Author: colin
 * @Date: 2019/9/3 17:26
 * @Description:
 * @Version: V1.0
 */
//@Configuration
public class MyWebMvcConfigurerAdapter extends WebMvcConfigurerAdapter {

    @Autowired
    private MyAnnotationIntrospector annotationIntrospector;

    @Override
    public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
        for (HttpMessageConverter<?> converter : converters) {
            if (converter instanceof MappingJackson2HttpMessageConverter) {
                MappingJackson2HttpMessageConverter mm = (MappingJackson2HttpMessageConverter) converter;
                ObjectMapper objectMapper = mm.getObjectMapper();
                //指定 自定义的序列化格式，通过属性加注解的方式
                objectMapper.setAnnotationIntrospector(annotationIntrospector);
            }
        }
    }
}
