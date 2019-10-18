package com.zhliang.springboot.configurationproperties.mail;

import com.zhliang.springboot.configurationproperties.SpringbootConfigurationPropertiesApplicationTests;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.*;

public class MailConfigTest extends SpringbootConfigurationPropertiesApplicationTests {

    @Autowired
    private MailConfig config;

    @Test
    public void mailProperties() {
        System.out.println(config.mailProperties().getUsername());
    }
}