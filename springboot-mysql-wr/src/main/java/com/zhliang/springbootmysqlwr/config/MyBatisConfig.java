package com.zhliang.springbootmysqlwr.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.annotation.Resource;
import javax.sql.DataSource;

/**
 * @Author: colin
 * @Date: 2019/10/18 10:48
 * @Description:
 *    由于 Spring 容器中现在有4个数据源,所以需要我们手动为事务管理器和Mybatis指定一个明确数据源
 * @Version: V1.0
 */
@EnableTransactionManagement
@Configuration
public class MyBatisConfig {


    @Resource(name = "myRoutingDataSource")
    private DataSource myRoutingDataSource;


    @Bean
    public SqlSessionFactory SqlSessionFactory() throws Exception{
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(myRoutingDataSource);
        //sqlSessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath" +
        //        ":/mapping/*.xml"));
        return sqlSessionFactoryBean.getObject();
    }

    @Bean
    public PlatformTransactionManager platformTransactionManager(){
        return new DataSourceTransactionManager(myRoutingDataSource);
    }

}
