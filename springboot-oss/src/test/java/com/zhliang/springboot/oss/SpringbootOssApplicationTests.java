package com.zhliang.springboot.oss;

import com.zhliang.springboot.oss.cloud.CloudStorageConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootOssApplicationTests {

    @Autowired
    private CloudStorageConfig config;

    @Test
    public void contextLoads() {
        System.out.println(config);
    }

}
