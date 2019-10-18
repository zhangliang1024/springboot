package com.zhliang.springboota.sync;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * SpringBoot异步任务-及带返回值的异步任务: https://blog.csdn.net/weixin_38399962/article/details/82146480
 *
 */
@EnableAsync
@SpringBootApplication
public class SpringbootAsyncApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootAsyncApplication.class, args);
    }

}
