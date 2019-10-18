package com.zhliang.springboot.restemplate.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

/**
 * @Author: colin
 * @Date: 2019/7/29 15:58
 * @Description: RestTemplate 配置模板类
 * @Version: V1.0
 */
@Configuration
public class RestTemplateConfig {

    /**
     * 通过配置文件注册 restTemplate 对象
     *
     * ★注意：注入factory时如果提示不能自动注入,应该是跟其他类有冲突。有多个：ClientHttpRequestFactory,
     *        这时需要把自己注入的工厂类改一下名称： 如  simpleClientHttpRequestFactory
     * @return
     */
    @Bean
    public RestTemplate restTemplate(ClientHttpRequestFactory factory){
        return new RestTemplate(factory);
    }

    /**
     * 配置 连接工厂设置连接参数
     * @return
     */
    @Bean
    public ClientHttpRequestFactory simpleClientHttpRequestFactory(){
        SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
        //连接超时时间 单位：ms
        factory.setConnectTimeout(15000);
        //响应超时时间
        factory.setReadTimeout(5000);
        return factory;
    }
}
