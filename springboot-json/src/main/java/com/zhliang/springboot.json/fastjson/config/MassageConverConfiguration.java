package com.zhliang.springboot.json.fastjson.config;

import com.alibaba.fastjson.serializer.SerializeConfig;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.serializer.ToStringSerializer;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;

import java.math.BigInteger;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: colin
 * @Date: 2019/8/19 21:07
 * @Description: 添加Bean到Spring容器中
 * @Version: V1.0
 */
@Configuration
public class MassageConverConfiguration {

    /**
     * 使用FastJson 做为 JSON MessageConverter
     * 修改Springboot默认JSON序列化解析框架 方式二：
     * 注入 HttpMessageConverters
     *
     */

    @Bean
    public HttpMessageConverters fastJsonHttpMessageConverters(){
        //1.定义一个 converters 转换消息的对象
        FastJsonHttpMessageConverter fastConverter = new FastJsonHttpMessageConverter();
        //2.添加fastJson的配置信息 如：是否需要格式化返回json数据
        FastJsonConfig config = new FastJsonConfig();
        config.setSerializerFeatures(
                //是否格式化返回的json数据
                SerializerFeature.PrettyFormat,
                SerializerFeature.IgnoreNonFieldGetter,
                SerializerFeature.WriteMapNullValue,
                SerializerFeature.WriteNullStringAsEmpty);

        /**
         * 自定义序列化方式 Long -> String
         * 解决Long类型 精度丢失问题
         */
        SerializeConfig serializeConfig = config.getSerializeConfig();
        serializeConfig.put(BigInteger.class, ToStringSerializer.instance);
        serializeConfig.put(Long.class,ToStringSerializer.instance);
        serializeConfig.put(Long.TYPE,ToStringSerializer.instance);
        config.setSerializeConfig(serializeConfig);

        //3.在converters中天机配置信息
        fastConverter.setFastJsonConfig(config);

        //支持的媒体类型
        List supportedMediaTypes = new ArrayList();
        supportedMediaTypes.add(new MediaType("text", "json", Charset.forName("utf-8")));
        supportedMediaTypes.add(new MediaType("application", "json", Charset.forName("utf-8")));

        fastConverter.setSupportedMediaTypes(supportedMediaTypes);
        //4.将converter赋值给HttpMessageConverter
        HttpMessageConverter<?> converter = fastConverter;
        // 5.返回HttpMessageConverters对象
        return new HttpMessageConverters(converter);
    }

}
