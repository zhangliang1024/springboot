package com.zhliang.springboot.dynamic.datasource;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Demo实现参考：
 * spring 动态切换、添加数据源实现以及源码浅析:
 *  - https://www.jianshu.com/p/0a485c965b8b
 *  - 代码地址：https://github.com/TavenYin/taven-springboot-learning
 *
 *  ---------------------------------------------------------------------------------------
 *  - 问题：
 *  - 1. 开启事务后，没办法实现动态切换数据源。开启事务后，数据库连接从事务中获取
 *  - 2. 通过切面的形式来动态切换数据源，某个业务调用结束后在切换回来。要考虑调用异常等情况，增大开发的易错性
 *  - 3.手动控制事务或采用分布式事务方式来保障数据的一致性
 *  ---------------------------------------------------------------------------------------
 *
 *  优秀实现：多
 *  数据源动态切换：
 *   - https://www.jianshu.com/p/432c839e0505
 */
@SpringBootApplication
@MapperScan("com.zhliang.springboot.dynamic.datasource.mapper")
public class SpringbootDynamicDatasourceApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootDynamicDatasourceApplication.class, args);
    }

}
