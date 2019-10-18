package com.zhliang.springboot.configurationproperties.mail;

import com.sun.istack.internal.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: colin
 * @Date: 2019/8/21 15:49
 * @Description:
 * @Version: V1.0
 */
@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "mail",ignoreInvalidFields = false)
public class MailProperties {

    /**
     * 使用@ConfigurationProperties来绑定属性
     * ignoreInvalidFields用来告诉Springboot在有属性不能匹配到声明的域的时候抛出异常
     */

    @Getter
    @Setter
    public static class Smtp {
        private boolean auth;
        private boolean starttlsEnable;

    }

    private String host;
    private int port;
    private String from;
    private String username;
    private String password;
    @NotNull
    private Smtp smtp;

}
