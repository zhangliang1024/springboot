package com.zhliang.springboot.json.fastjson;

//import com.alibaba.fastjson.serializer.SerializerFeature;
//import com.alibaba.fastjson.support.config.FastJsonConfig;
//import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.List;

//import org.springframework.http.MediaType;
//import java.nio.charset.Charset;
//import java.util.ArrayList;

@SpringBootApplication
public class SpringbootJsonApplication extends WebMvcConfigurerAdapter {

    /**
     * 修改Springboot默认JSON序列化解析框架 方式一：
     *  启动类继承WebMvcConfigurerAdapter,覆盖方法configureMessageConverters
     */
    @Override
    public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
        //1. 初始化转换器
        //FastJsonHttpMessageConverter fastConvert = new FastJsonHttpMessageConverter();
        //
        ////2. 初始化一个转换器配置
        //FastJsonConfig fastJsonConfig = new FastJsonConfig();
        //fastJsonConfig.setSerializerFeatures(
        //        //json格式输出
        //        SerializerFeature.PrettyFormat,
        //        //保留map为空的字段
        //        SerializerFeature.WriteMapNullValue,
        //        //将String类型的null转成""形式
        //        SerializerFeature.WriteNullStringAsEmpty,
        //        //将Number类型的null转成0,也可以理解为Integer
        //        SerializerFeature.WriteNullNumberAsZero,
        //        //将List类型的null转成[]
        //        SerializerFeature.WriteNullListAsEmpty,
        //        //将Boolean类型的null转成false
        //        SerializerFeature.WriteNullBooleanAsFalse,
        //        //处理可能循环引用的问题
        //        SerializerFeature.DisableCircularReferenceDetect
        //        );
        //
        ////3. 将配置设置给转换器并添加到HttpMessageConverter转换器列表中
        //fastConvert.setFastJsonConfig(fastJsonConfig);
        ////设置默认的字符集
        //fastConvert.setDefaultCharset(Charset.forName("UTF-8"));
        ////设置媒体类型
        //List<MediaType> mediaTypeList = new ArrayList<>();
        //mediaTypeList.add(MediaType.APPLICATION_JSON);
        //fastConvert.setSupportedMediaTypes(mediaTypeList);

        //converters.add(fastConvert);
    }

    public static void main(String[] args) {
        SpringApplication.run(SpringbootJsonApplication.class, args);
    }

}
