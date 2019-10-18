package com.zhliang.springboot.runner;

import com.zhliang.springboot.runner.event.EventImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootRunnerApplicationTests {

    @Autowired
    private ApplicationContext applicationContext;


    @Test
    public void contextLoads() {
        EventImpl testEvent = new EventImpl("");
        testEvent.setMessage("hello world");
        applicationContext.publishEvent(testEvent);
    }

}
