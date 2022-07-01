package com.zhliang.springboot.prometheus.config;

import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.actuate.autoconfigure.metrics.MeterRegistryCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @类描述：
 * @创建时间：2021/8/13 15:48
 * @version：V1.0
 */
@Configuration
public class PrometheusConfiguration {

    @Value("${spring.application.name}")
    private String applicationName;


    @Bean
    MeterRegistryCustomizer<MeterRegistry> metersCommonTags(){
        return registry -> registry.config().commonTags("applicationName",applicationName);
    }
}
