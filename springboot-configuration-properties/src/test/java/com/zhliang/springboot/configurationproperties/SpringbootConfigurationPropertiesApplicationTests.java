package com.zhliang.springboot.configurationproperties;

import com.zhliang.springboot.configurationproperties.domain.Person;
import com.zhliang.springboot.configurationproperties.mail.MailProperties;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootConfigurationPropertiesApplicationTests {

    @Autowired
    private Person person;
    @Autowired
    private MailProperties mailProperties;


    @Test
    public void contextLoads() {
        System.out.println(person);
        System.out.println(mailProperties.getHost());
        System.out.println(mailProperties.getSmtp().isAuth());
    }

}
