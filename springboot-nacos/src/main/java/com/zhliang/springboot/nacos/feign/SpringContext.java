package com.zhliang.springboot.nacos.feign;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Configuration;

import java.util.Map;


@Configuration
public class SpringContext implements ApplicationContextAware {

    private static ApplicationContext ctx; // Spring应用上下文环境

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        SpringContext.ctx = applicationContext;
    }

    public static ApplicationContext getSpringContext() {
        return ctx;
    }

    public static <T> Map<String, T> getBeansOfType(Class<T> clz) {
        return ctx.getBeansOfType(clz);
    }

    public static <T> T getBean(String name) {
        return (T) SpringContext.ctx.getBean(name);
    }

    public static <T> T getBean(Class<T> clz) {
        return SpringContext.ctx.getBean(clz);
    }

}
