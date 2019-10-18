package com.zhliang.springboot.transaction.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;


/**
 * @Author: colin
 * @Date: 2019/7/31 20:16
 * @Description:
 * @Version: V1.0
 */
@Configuration
public class TransactionConfig {

    /**
     * 1. Springboot 会自动为我们做一些事务的注入及配置。
     *    但如果我们的项目使用的持久化依赖比较多，则需要人为的指定使用什么样的事务管理器
     * 2. 在Spring容器中，我们手工注解@Bean将被优先加载，框架不会重新实例化其他的PlatformTransactionManager实现类
     * 3. 在Service中，被@Transaction注解的方法，将支持事务。如果注解在类上，则整个类的方法都默认支持事务
     */

    @Bean(name = "txManagerDefault")  // dataSource 框架会为我们自动注入
    public PlatformTransactionManager txManager(DataSource dataSource){
        return  new DataSourceTransactionManager(dataSource);
    }



}
