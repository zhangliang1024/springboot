package com.zhliang.springboot;

import cn.gjing.cache.core.EnableSecondCache;
import cn.gjing.lock.core.EnableRedisLock;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableRedisLock   //注解启动分布式锁
@EnableSecondCache //开启分布式缓存
@SpringBootApplication
public class SpringbootLockApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootLockApplication.class, args);
    }

}
