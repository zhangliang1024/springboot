package com.zhliang.springboot.transaction.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.TransactionManagementConfigurer;

import javax.persistence.EntityManagerFactory;

/**
 * @Author: colin
 * @Date: 2019/7/31 20:34
 * @Description:
 * @Version: V1.0
 */
@Slf4j
@Configuration
public class ProfiledemoApplication implements TransactionManagementConfigurer {


    @Autowired
    private PlatformTransactionManager txManagerSecond;

    // 创建事务管理器2
    @Bean(name = "txManagerSecond")
    public PlatformTransactionManager txManager2(EntityManagerFactory factory) {
        return new JpaTransactionManager(factory);
    }


    /**
     * 实现接口 TransactionManagementConfigurer方法，其返回值代表在拥有多个事务处理器的
     *         情况下默认使用的的事务管理器
     * @return
     */
    @Override
    public PlatformTransactionManager annotationDrivenTransactionManager() {

        return txManagerSecond;
    }
}
