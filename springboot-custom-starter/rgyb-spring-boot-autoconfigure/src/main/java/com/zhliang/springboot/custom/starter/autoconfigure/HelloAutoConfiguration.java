package com.zhliang.springboot.custom.starter.autoconfigure;

import com.zhliang.springboot.custom.starter.service.HelloService;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

/**
 * @项目名称：springboot
 * @包名：com.zhliang.springboot.custom.starter.autoconfigure
 * @类描述：
 * @创建人：colin
 * @创建时间：2019/12/20 14:41
 * @version：V1.0
 */
//@Configuration
@EnableConfigurationProperties(HelloProperties.class)
@Order(0)
public class HelloAutoConfiguration {

    @Bean
    public HelloService helloService(HelloProperties helloProperties) {
        HelloService helloService = new HelloService();
        helloService.setHelloProperties(helloProperties);
        return helloService;
    }

}
