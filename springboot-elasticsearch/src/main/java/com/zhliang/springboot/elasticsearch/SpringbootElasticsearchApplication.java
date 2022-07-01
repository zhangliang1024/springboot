package com.zhliang.springboot.elasticsearch;

import com.alibaba.fastjson.serializer.SerializeConfig;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

@MapperScan(basePackages = {"com.zhliang.springboot.elasticsearch.mapper"})
@SpringBootApplication
public class SpringbootElasticsearchApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootElasticsearchApplication.class, args);
    }

    /*@Bean
    public Jackson2ObjectMapperBuilderCustomizer jacksonObjectMapperCustomization() {
        return jacksonObjectMapperBuilder ->
                jacksonObjectMapperBuilder.timeZone(TimeZone.getTimeZone("GMT+8"));
    }*/

    /**
     * 替换springboot默认的jackjson解析工具，使用fastjson进行解析json对象到前台
     * 1. fastJson默认在进行序列化时，会过滤掉值null的属性。但实际项目上是需要为null的属性
     * 2. 通过设置HttpMessageConverters来统一配置，在使用fastJosn进行序列化时对各种为空情况的转换
     * 3. 在拿到josn(是json字符串，不是字符串)字符串时，使用json.replaceAll("null","''") 把null转为空值。则序列化不会丢失属性
     * FastJson 过滤/保留 NULL值问题：
     * 4. 参数可以直接传递过来(Postman 格式 2020-12-19T00:00:00.000Z)
     *
     * @return HttpMessageConverters
     */
    @Bean
    public HttpMessageConverters fastJsonHttpMessageConverters() {
        // 1.需要定义一个convert转换消息的对象;
        FastJsonHttpMessageConverter fastJsonHttpMessageConverter = new FastJsonHttpMessageConverter();
        // 2:添加fastJson的配置信息;
        FastJsonConfig fastJsonConfig = new FastJsonConfig();

        //JSON.defaultTimeZone = TimeZone.getTimeZone("Asia/Shanghai");
        //JSON.DEFFAULT_DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSSZ";
        fastJsonConfig.setSerializerFeatures(
                SerializerFeature.PrettyFormat,
                SerializerFeature.DisableCircularReferenceDetect,
                SerializerFeature.WriteMapNullValue,
                SerializerFeature.WriteNullStringAsEmpty,
                SerializerFeature.WriteDateUseDateFormat,
                SerializerFeature.WriteNullNumberAsZero,
                SerializerFeature.WriteNullListAsEmpty);

        //fastJsonConfig.setDateFormat("yyyy-MM-dd HH:mm:ss");
        //FIXME 处理序列化时区问题 从es拿到数据 反序列化会增加8小时 https://github.com/alibaba/fastjson/issues/2470
        SerializeConfig config = new SerializeConfig();
        config.put(Date.class, (serializer, object, fieldName, fieldType, features) -> {
            if (object == null) {
                serializer.out.writeNull();
                return;
            }
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
            serializer.out.writeString(dateFormat.format(object));
        });
        fastJsonConfig.setSerializeConfig(config);

        // 3处理中文乱码问题
        List<MediaType> fastMediaTypes = new ArrayList<>();
        fastMediaTypes.add(MediaType.APPLICATION_JSON_UTF8);
        // 4.在convert中添加配置信息.
        fastJsonHttpMessageConverter.setSupportedMediaTypes(fastMediaTypes);
        fastJsonHttpMessageConverter.setFastJsonConfig(fastJsonConfig);
        HttpMessageConverter<?> converter = fastJsonHttpMessageConverter;
        return new HttpMessageConverters(converter);

        //FastJsonHttpMessageConverter fastConvert = new FastJsonHttpMessageConverter();
        //
        //FastJsonConfig fastJsonConfig = new FastJsonConfig();
        //JSON.defaultTimeZone = TimeZone.getTimeZone("Asia/Shanghai");
        //JSON.DEFFAULT_DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSSZ";
        //fastJsonConfig.setSerializerFeatures(SerializerFeature.BrowserCompatible,
        //        SerializerFeature.BrowserSecure,
        //        SerializerFeature.PrettyFormat,
        //        SerializerFeature.WriteDateUseDateFormat,
        //        SerializerFeature.WriteMapNullValue,
        //        SerializerFeature.DisableCircularReferenceDetect);
        //*
        // //* 解决Long转json精度丢失的问题
        //
        //SerializeConfig serializeConfig = SerializeConfig.globalInstance;
        //serializeConfig.put(BigInteger.class, ToStringSerializer.instance);
        //serializeConfig.put(Long.class, ToStringSerializer.instance);
        //serializeConfig.put(Long.TYPE, ToStringSerializer.instance);
        //fastJsonConfig.setSerializeConfig(serializeConfig);
        //fastConvert.setFastJsonConfig(fastJsonConfig);
        //converters.add(fastConvert);

    }
}
