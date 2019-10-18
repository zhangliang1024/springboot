package com.zhliang.springboot.oss;

import com.zhliang.springboot.oss.cloud.CloudStorageConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
//@EnableConfigurationProperties(CloudStorageConfig.class)
public class SpringbootOssApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootOssApplication.class, args);
    }

}
