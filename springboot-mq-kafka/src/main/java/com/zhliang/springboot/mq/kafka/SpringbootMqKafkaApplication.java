package com.zhliang.springboot.mq.kafka;

import com.github.xiaour.api_scanner.annotation.Sapi;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Sapi(controllers = {"com.zhliang.springboot"})
@SpringBootApplication
public class SpringbootMqKafkaApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootMqKafkaApplication.class, args);
    }

}
