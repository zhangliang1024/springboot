package com.zhliang.ons.spring.boot.starter.config;

import com.zhliang.ons.spring.boot.starter.client.MQClient;
import com.zhliang.ons.spring.boot.starter.property.AliyunOnsProperty;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: colin
 * @Date: 2020/1/2 10:57
 * @Description:
 * @Version: V1.0
 *
 *  采用spring.factories 和 @EnableXXX 注解是两种不同的方式
 *    1. spring.factories 需要配合 AutoConfigurationXXX 及相关依赖
 *       Bean的注入 需要自己手动来完成
 *    2. @EnableXXX 需要配合 @Import 注解注入需要的Bean示例
 */
@Configuration
@ConditionalOnProperty(value = "aliyun.ons.enable",havingValue = "true")
@ConditionalOnClass(MQClient.class)
@EnableConfigurationProperties(AliyunOnsProperty.class)
public class AliMQAutoConfiguration {

    @Bean
    public MQClient mqClient() {
        MQClient service = new MQClient();
        System.out.println("mqClient init ...");
        return service;
    }
}
