package com.zhliang.springboot.druid;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

import javax.sql.DataSource;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootDruidApplicationTests {


    @Autowired
    ApplicationContext applicationContext;


    @Test
    public void contextLoads() {
        //1. 获取配置的数据源
        DataSource source = applicationContext.getBean(DataSource.class);
        //2. 查看配置数据源信息
        System.out.println(source.getClass().getName());

    }

}
