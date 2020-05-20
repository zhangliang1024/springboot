package com.zhliang.springboot.actuator.strategy.autowiredsms;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import javax.annotation.PostConstruct;

/**
 * @项目名称：springboot
 * @包名：com.zhliang.springboot.actuator.strategy.sms
 * @类描述：
 * @创建人：zhiang
 * @创建时间：2020/5/20 14:08
 * @version：V1.0
 */
@PropertySource("classpath:sms.properties")
@Configuration
@ConfigurationProperties(prefix = "spring.sms.send")
public class SmsAutowiredChannelConfig {

    @Value("${spring.sms.send.channelType}")
    private String channelType;

    public String getChannelType() {
        return channelType;
    }

    public void setChannelType(String channelType) {
        this.channelType = channelType;
    }

    @PostConstruct
    public void init(){
        System.out.println("SmsAutowiredChannelConfig init");
    }

}
