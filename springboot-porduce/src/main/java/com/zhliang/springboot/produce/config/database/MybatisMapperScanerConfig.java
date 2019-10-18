package com.zhliang.springboot.produce.config.database;

import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import tk.mybatis.spring.mapper.MapperScannerConfigurer;

/**
 * @Author: colin
 * @Date: 2019/9/23 14:38
 * @Description:
 * @Version: V1.0
 */
@Configuration
@AutoConfigureAfter(MybatisDataSourceConfig.class)
public class MybatisMapperScanerConfig {


    @Bean
    public MapperScannerConfigurer mapperScannerConfigurer(){
        MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
        mapperScannerConfigurer.setSqlSessionFactoryBeanName("sqlSessionFactory");
        mapperScannerConfigurer.setBasePackage("com.zhliang.springboot.produce.mapper");
        return mapperScannerConfigurer;
    }


}
