package com.zhliang.springboot.api.mideng.config;

import com.zhliang.springboot.api.mideng.interceptor.AutoIdempotentInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.annotation.Resource;

/**
 * @项目名称：springboot
 * @包名：com.zhliang.springboot.api.mideng.config
 * @类描述：
 * @创建人：zhiang
 * @创建时间：2020/5/6 19:31
 * @version：V1.0
 */
@Configuration
public class WebConfiguration extends WebMvcConfigurerAdapter {

    @Resource
    private AutoIdempotentInterceptor autoIdempotentInterceptor;

    /**
     * 添加拦截器
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(autoIdempotentInterceptor);
        super.addInterceptors(registry);
    }

}
