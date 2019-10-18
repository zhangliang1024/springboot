package com.zhliang.springboot.dynamic.datasource.config;

import com.alibaba.druid.pool.DruidDataSourceFactory;
import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import com.zhliang.springboot.dynamic.datasource.config.prop.Db0Properties;
import com.zhliang.springboot.dynamic.datasource.config.prop.Db1Properties;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: colin
 * @Date: 2019/9/25 19:33
 * @Description: 多数据源配置
 * @Version: V1.0
 */
@Slf4j
@Configuration
public class DataSourceConfigurer {

    @Autowired
    private Db0Properties db0Properties;

    @Autowired
    private Db1Properties db1Properties;


    // ★ 另一种自动装配方式
    /*@Bean("db0")
    @Primary
    @ConfigurationProperties(prefix = "datasource.db0")
    public DataSource dataSource0() {
        return DruidDataSourceBuilder.create().build();
    }
    @Bean("db1")
    @ConfigurationProperties(prefix = "datasource.db1")
    public DataSource dataSource1() {
        return DruidDataSourceBuilder.create().build();
    }*/


    /**
     * DataSource 自动配置并注册
     */
    @Bean("db0")
    @Primary
    public DataSource dataSource0() {
        DataSource dataSource = null;
        try {
            dataSource = DruidDataSourceFactory.createDataSource(db0Properties.getProperties());
        } catch (Exception e) {
            throw new RuntimeException();
        }
        return dataSource;
    }

    /**
     * DataSource 自动配置并注册
     */
    @Bean("db1")
    public DataSource dataSource1() {
        DataSource dataSource = null;
        try {
            dataSource = DruidDataSourceFactory.createDataSource(db1Properties.getProperties());
        } catch (Exception e) {
            throw new RuntimeException();
        }
        return dataSource;
    }

    /**
     * 注册动态数据源
     */
    @Bean("dynamicDataSource")
    public DataSource dynamicDataSource() {
        DynamicRoutingDataSource dynamicRoutingDataSource = new DynamicRoutingDataSource();
        Map<Object, Object> dataSourceMap = new HashMap<>();
        dataSourceMap.put("dynamic_db0", dataSource0());
        dataSourceMap.put("dynamic_db1", dataSource1());
        // 设置默认数据源
        dynamicRoutingDataSource.setDefaultTargetDataSource(dataSource0());
        dynamicRoutingDataSource.setTargetDataSources(dataSourceMap);
        return dynamicRoutingDataSource;
    }

    /**
     * Sql session factory bean.
     * Here to config datasource for SqlSessionFactory
     * <p>
     * You need to add @{@code @ConfigurationProperties(prefix = "mybatis")}, if you are using *.xml file,
     * the {@code 'mybatis.type-aliases-package'} and {@code 'mybatis.mapper-locations'} should be set in
     * {@code 'application.properties'} file, or there will appear invalid bond statement exception
     *
     * @return the sql session factory bean
     */
    @Bean
    @ConfigurationProperties(prefix = "mybatis")
    public SqlSessionFactoryBean sqlSessionFactoryBean() {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        // 必须将动态数据源添加到 sqlSessionFactoryBean
        sqlSessionFactoryBean.setDataSource(dynamicDataSource());
        return sqlSessionFactoryBean;
    }

    /**
     * 事务管理器
     */
    @Bean
    public PlatformTransactionManager transactionManager() {
        return new DataSourceTransactionManager(dynamicDataSource());
    }
}
