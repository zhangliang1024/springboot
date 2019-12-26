package com.zhliang.springboot.custom.starter;

import com.zhliang.springboot.custom.starter.annotation.EnableCustomAuto;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * @项目名称：springboot
 * @包名：com.zhliang.springboot.custom.starter
 * @类描述：
 * @创建人：colin
 * @创建时间：2019/11/19 01:05
 * @version：V1.0
 */
@EnableCustomAuto
@SpringBootApplication
public class RgybSpringBootSampleApplication {



    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(RgybSpringBootSampleApplication.class, args);
    }



}
