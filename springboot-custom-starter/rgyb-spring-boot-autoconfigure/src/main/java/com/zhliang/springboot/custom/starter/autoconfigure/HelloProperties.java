package com.zhliang.springboot.custom.starter.autoconfigure;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @项目名称：springboot
 * @包名：com.zhliang.springboot.custom.starter.autoconfigure
 * @类描述：
 * @创建人：colin
 * @创建时间：2019/12/20 14:38
 * @version：V1.0
 */
@Data
@ConfigurationProperties(prefix = HelloProperties.HELLO_PREFIX)
public class HelloProperties {

    public static final String HELLO_PREFIX = "project.hello";

    private String prefix;
    private String suffix;

}