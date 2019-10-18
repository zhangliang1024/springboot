package com.zhliang.springboot.json.jackjson.config;
import	java.math.BigInteger;
import	java.math.BigDecimal;
import	java.text.SimpleDateFormat;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.text.DateFormat;
import java.util.List;

/**
 * @Author: colin
 * @Date: 2019/8/27 11:57
 * @Description:  jackson自定义  时间和Long类型 序列化格式
 * @Version: V1.0
 */
@Configuration
public class JacksonConfig  extends WebMvcConfigurerAdapter {

    private static DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Override
    public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
        for (HttpMessageConverter<?> converter : converters) {
            if (converter instanceof MappingJackson2HttpMessageConverter) {
                MappingJackson2HttpMessageConverter mm = (MappingJackson2HttpMessageConverter) converter;
                ObjectMapper objectMapper = mm.getObjectMapper();
                //Jackson 默认时间格式化格式 false
                objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS,false);
                //指定 自定义的序列化格式
                objectMapper.setDateFormat(format);

                //注册Long专用json转换器
                SimpleModule module = new SimpleModule();
                //修复Long类型太长，精度丢失问题
                module.addSerializer(Long.class, ToStringSerializer.instance);
                module.addSerializer(Long.TYPE, ToStringSerializer.instance);
                module.addSerializer(BigDecimal.class,ToStringSerializer.instance);
                module.addSerializer(BigInteger.class,ToStringSerializer.instance);

                objectMapper.registerModule(module);
            }
        }
    }
}
