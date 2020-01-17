package com.zhliang.springboot.rest.api.advice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @项目名称：springboot
 * @包名：com.zhliang.springboot.rest.api.advice
 * @类描述：
 * @创建人：colin
 * @创建时间：2020/1/6 12:22
 * @version：V1.0
 */
@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter {

    @Autowired
    private ResponseResultInterceptor interceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(interceptor).addPathPatterns("/**");
        super.addInterceptors(registry);
    }
}
