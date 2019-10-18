package com.zhliang.springboot.oss.cloud;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: colin
 * @Date: 2019/8/20 20:18
 * @Description:
 * @Version: V1.0
 */
@Configuration
public class CloudOSSConfig {

    @Bean
    @ConfigurationProperties(prefix = "cloud")
    public CloudStorageConfig config(){
        return new CloudStorageConfig();
    }
}
