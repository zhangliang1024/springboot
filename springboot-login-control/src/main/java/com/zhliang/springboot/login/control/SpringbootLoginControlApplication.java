package com.zhliang.springboot.login.control;

import com.zhliang.springboot.login.control.filter.CompareKickOutFilter;
import com.zhliang.springboot.login.control.filter.KickOutFilter;
import com.zhliang.springboot.login.control.filter.QueueKickOutFilter;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.codec.JsonJacksonCodec;
import org.redisson.config.Config;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

/**
 * SpringBoot 并发登录人数控制:
 *  - https://www.jianshu.com/p/b6f5ec98d790
 * Code
 *  - https://gitee.com/yintianwen7/taven-springboot-learning/tree/master/login-control
 */
@SpringBootApplication
public class SpringbootLoginControlApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootLoginControlApplication.class, args);
    }


    @Bean(destroyMethod="shutdown")
    public RedissonClient redisson() {
        Config config = new Config();
        config.setCodec(new JsonJacksonCodec())
                .useSingleServer()
                .setAddress("redis://localhost:6379");
        return Redisson.create(config);
    }

    @ConditionalOnProperty(value = {"queue-filter.enabled"})
    @Bean
    public KickOutFilter queueKickOutFilter() {
        return new QueueKickOutFilter();
    }

    @ConditionalOnMissingBean(KickOutFilter.class)
    @Bean
    public KickOutFilter compareKickOutFilter() {
        return new CompareKickOutFilter();
    }

    @Bean
    public FilterRegistrationBean testFilterRegistration(KickOutFilter kickOutFilter) {
        System.out.println(kickOutFilter);
        FilterRegistrationBean registration = new FilterRegistrationBean(kickOutFilter);
        registration.addUrlPatterns("/user/*");
        registration.setName("kickOutFilter");
        return registration;
    }
}
