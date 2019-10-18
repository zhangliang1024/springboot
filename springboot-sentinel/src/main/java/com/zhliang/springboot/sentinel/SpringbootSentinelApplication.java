package com.zhliang.springboot.sentinel;

import com.alibaba.csp.sentinel.Entry;
import com.alibaba.csp.sentinel.SphU;
import com.alibaba.csp.sentinel.adapter.servlet.CommonFilter;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
public class SpringbootSentinelApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootSentinelApplication.class, args);


        // 不断进行资源调用.
        while (true) {
            Entry entry = null;
            try {
                entry = SphU.entry("HelloWorld");
                // 资源中的逻辑.
                System.out.println("hello world");
            } catch (BlockException e1) {
                System.out.println("blocked!");
            } finally {
                if (entry != null) {
                    entry.exit();
                }
            }
        }
    }
    @Configuration
    public class WebConfig {
        @Bean
        public FilterRegistrationBean sentinelFilterRegistration() {
            FilterRegistrationBean registration = new FilterRegistrationBean();
            registration.setFilter(new CommonFilter());
            registration.addUrlPatterns("/*");
            registration.setName("sentinelCommonFilter");
            registration.setOrder(1);
            return registration;
        }
    }


}
