package com.zhliang.springboot.oss;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@EnableConfigurationProperties(CloudStorageConfig.class)
public class SpringbootOssApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootOssApplication.class, args);
    }

}
