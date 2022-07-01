package com.zhliang.springboot.nacos;

import com.alibaba.nacos.api.config.annotation.NacosValue;
import com.alibaba.nacos.spring.context.annotation.config.NacosPropertySource;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@NacosPropertySource(dataId = "thread-pool.properties",groupId = "DEFAULT_GROUP",autoRefreshed = true)
@SpringBootApplication
public class SpringbootNacosApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootNacosApplication.class, args);
    }

    @NacosValue(value = "${server.port:8081}", autoRefreshed = true)
    private String serverPort;

    @GetMapping("port")
    public String get() {
        return serverPort;
    }
}
