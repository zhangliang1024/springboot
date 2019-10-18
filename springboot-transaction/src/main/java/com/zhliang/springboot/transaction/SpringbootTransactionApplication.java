package com.zhliang.springboot.transaction;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 *
 */
@EnableTransactionManagement  //开启事务支持
@SpringBootApplication
public class SpringbootTransactionApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootTransactionApplication.class, args);
    }


    /**
     * mybatis-spring-boot-starter ： org.springframework.jdbc.datasource.DataSourceTransactionManager
     * spring-boot-starter-data-jpa ：org.springframework.orm.jpa.JpaTransactionManager
     * @param platformTransactionManager
     * @return
     */
    @Bean
    public Object getBean(PlatformTransactionManager platformTransactionManager){
        System.out.println(" =======>>>>>> " + platformTransactionManager.getClass().getName());
        return new Object();
    }



}
