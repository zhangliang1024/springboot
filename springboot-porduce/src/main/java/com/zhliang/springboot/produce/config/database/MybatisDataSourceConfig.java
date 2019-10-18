package com.zhliang.springboot.produce.config.database;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;

/**
 * @Author: colin
 * @Date: 2019/9/23 14:38
 * @Description:
 * @Version: V1.0
 */
@Configuration
public class MybatisDataSourceConfig {

    @Autowired
    private DataSource dataSource;

    /**
     * 注入 sqlSessionFactory
     * @return
     */
    @Bean("sqlSessionFactory")
    public SqlSessionFactory sqlSessionFactoryBean(){
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        factoryBean.setDataSource(dataSource);
        //添加xml目录
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        try {
            factoryBean.setMapperLocations(resolver.getResources("classpath:com/zhliang/springboot/produce/mapping/*.xml"));
            SqlSessionFactory sqlSessionFactory = factoryBean.getObject();
            sqlSessionFactory.getConfiguration().setCacheEnabled(Boolean.TRUE);
            return sqlSessionFactory;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Bean
    public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory sqlSessionFactory){
        return new SqlSessionTemplate(sqlSessionFactory);
    }

}
