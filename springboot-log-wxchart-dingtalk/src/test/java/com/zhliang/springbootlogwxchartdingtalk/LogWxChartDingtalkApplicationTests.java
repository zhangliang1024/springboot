package com.zhliang.springbootlogwxchartdingtalk;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest(classes = LogWxChartDingtalkApplicationTests.class)
class LogWxChartDingtalkApplicationTests {

    Logger logger = LoggerFactory.getLogger(getClass());

    @Test
    void contextLoads() {
        logger.error("test robot error log ");
    }

}
