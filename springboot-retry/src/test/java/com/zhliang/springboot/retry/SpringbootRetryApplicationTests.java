package com.zhliang.springboot.retry;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootRetryApplicationTests {

    @Test
    public void contextLoads() {
    }

    @Autowired
    private RetryService retryService;

    private Logger logger = LoggerFactory.getLogger(SpringbootRetryApplicationTests.class);

    @Test
    public void retryTest() {
        //int count = retryService.retry(-1);
        retryService.devide(2,1);
        //retryService.retry(-1);
        //logger.info("库存为：" + count);
    }

}
