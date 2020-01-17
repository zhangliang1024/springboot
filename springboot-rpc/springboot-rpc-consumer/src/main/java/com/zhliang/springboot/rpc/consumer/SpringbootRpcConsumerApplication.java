package com.zhliang.springboot.rpc.consumer;

import chy.frame.spring.starter.rpc.Annotation.RpcScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@RpcScan(basePackage = {"com.zhliang.springboot.rpc.consumer.service"})
@SpringBootApplication
public class SpringbootRpcConsumerApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootRpcConsumerApplication.class, args);
    }

}
