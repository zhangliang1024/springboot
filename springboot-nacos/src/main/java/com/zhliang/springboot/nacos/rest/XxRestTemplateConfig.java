package com.zhliang.springboot.nacos.rest;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.BufferingClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.StandardCharsets;
import java.util.Collections;

/**
 * @类描述：
 * @创建人：zhiang
 * @创建时间：2021/11/16 15:52
 */
@Configuration
public class XxRestTemplateConfig {


    @Bean(name = "xxRestTemplate")
    public RestTemplate xxRestTemplate(XxConfigProperties xxConfigProperties) {

        RestTemplate restTemplate = new RestTemplate(new BufferingClientHttpRequestFactory(
                new SimpleClientHttpRequestFactory()
        ));
        //设置消息处理器
        for (HttpMessageConverter<?> converter : restTemplate.getMessageConverters()) {
            if (converter instanceof StringHttpMessageConverter) {
                ((StringHttpMessageConverter) converter).setDefaultCharset(StandardCharsets.UTF_8);
            }
        }
        //设置拦截器
        restTemplate.setInterceptors(Collections.singletonList(xxRestAuthInterceptor(xxConfigProperties)));
        return restTemplate;
    }


    @Bean
    public RestTemplateMetricsInterceptor xxRestAuthInterceptor(XxConfigProperties xxConfigProperties) {
        return new RestTemplateMetricsInterceptor(xxConfigProperties);
    }
}
