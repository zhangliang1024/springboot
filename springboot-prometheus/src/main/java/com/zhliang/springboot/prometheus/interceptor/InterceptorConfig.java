package com.zhliang.springboot.prometheus.interceptor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @类描述：注册拦截器
 * @创建时间：2021/8/13 15:45
 * @version：V1.0
 */
@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

    @Bean
    public PrometheusInterceptor prometheusInterceptor(){
        return new PrometheusInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(prometheusInterceptor()).addPathPatterns("/**");
    }
}
