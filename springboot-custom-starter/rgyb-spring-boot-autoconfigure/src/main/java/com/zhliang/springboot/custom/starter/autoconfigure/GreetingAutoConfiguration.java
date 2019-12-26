package com.zhliang.springboot.custom.starter.autoconfigure;

import com.zhliang.springboot.custom.starter.service.DummyEmail;
import com.zhliang.springboot.custom.starter.service.GreetingService;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @项目名称：springboot
 * @包名：com.zhliang.springboot.custom.starter.autoconfigure
 * @类描述：问候自动配置类
 * @创建人：colin
 * @创建时间：2019/11/18 13:42
 * @version：V1.0
 */
//@Configuration
@ConditionalOnProperty(value = "rgyb.greeting.enable",havingValue = "true")
@ConditionalOnClass(DummyEmail.class)
@EnableConfigurationProperties(GreetingProperties.class)
public class GreetingAutoConfiguration {


    @Bean
    public GreetingService greetingService(GreetingProperties properties) {
        GreetingService service = new GreetingService();
        service.setProperties(properties);
        return service;
    }
}
