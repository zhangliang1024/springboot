package com.zhliang.springboot.two.cache.again;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
public class SpringbootTwoCacheAgainApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootTwoCacheAgainApplication.class, args);
    }

}
