package com.zhliang.springboot.restemplate.https;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import javax.activation.MimeType;
import javax.print.attribute.standard.Media;
import java.util.Arrays;
import java.util.List;

/**
 * @Author: colin
 * @Date: 2019/8/29 16:49
 * @Description:
 *  1. 将HttpClient 作为RestTemplate 的实现，添加httpClient 依赖即可
 *  2. 设置响应类型和响应内容
 *
 *  [spring boot resttemplate 使用及支持https协议]
 *   - (https://blog.csdn.net/u013469944/article/details/84193792)
 *
 * @Version: V1.0
 */
@Configuration
public class RestConfiguration {

    @Autowired
    private RestTemplateBuilder builder;

    @Bean
    public RestTemplate restTemplate(){
        return builder.additionalMessageConverters(new WxMappingJackson2HttpMessageConverter()).build();
    }

    class WxMappingJackson2HttpMessageConverter extends MappingJackson2HttpMessageConverter{
        WxMappingJackson2HttpMessageConverter(){
            List<MediaType> mediaTypeList = Arrays.asList(MediaType.TEXT_PLAIN, MediaType.TEXT_HTML, MediaType.APPLICATION_JSON);
            setSupportedMediaTypes(mediaTypeList);
        }
    }


}
