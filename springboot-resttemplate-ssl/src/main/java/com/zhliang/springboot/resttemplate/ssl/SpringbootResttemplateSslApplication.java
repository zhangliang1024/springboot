package com.zhliang.springboot.resttemplate.ssl;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * SpringBoot RestTemplate使用自签名ssl证书:
 *  - https://blog.csdn.net/ihtczte/article/details/89006795#restTemplate_74
 *  - https://github.com/HYUANT/demo-resttemplate
 */
@SpringBootApplication
public class SpringbootResttemplateSslApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootResttemplateSslApplication.class, args);
    }

}
