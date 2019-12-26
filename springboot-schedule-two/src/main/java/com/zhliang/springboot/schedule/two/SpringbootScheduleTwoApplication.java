package com.zhliang.springboot.schedule.two;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


//@EnableScheduling
@SpringBootApplication
public class SpringbootScheduleTwoApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootScheduleTwoApplication.class, args);

        //加载配置
        //AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        //applicationContext.register(AsyncConfigOther.class);
        //applicationContext.refresh();
    }

}
