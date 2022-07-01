package com.zhliang.springboot.log4j2;

import com.zhliang.springboot.log4j2.listener.ApplicationStartedEventListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.util.Set;

@SpringBootApplication(scanBasePackages = {"com.zhliang.springboot.log4j2"})
public class SpringbootLog4j2Application {

    private String bootstrap;

    public String getBootstrap() {
        return bootstrap;
    }

    public void setBootstrap(String bootstrap) {
        this.bootstrap = bootstrap;
    }

    public static void main(String[] args) {
        //启动类中，添加自定义监听器
        SpringApplication app = new SpringApplication(SpringbootLog4j2Application.class);
        Set<ApplicationListener<?>> ls = app.getListeners();
        ApplicationStartedEventListener asel = new ApplicationStartedEventListener();
        app.addListeners(asel);
        app.run(args);

        //SpringApplication.run(SpringbootLog4j2Application.class, args);
    }

}
