package com.zhliang.springboot.configurationproperties.mail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;


/**
 * @Author: colin
 * @Date: 2019/8/21 16:02
 * @Description:
 * @Version: V1.0
 */
@Configuration
@EnableConfigurationProperties(MailProperties.class)
public class MailConfig {

    @Autowired
    private MailProperties mailProperties;

    public MailProperties mailProperties(){
        return mailProperties;
    }
}
