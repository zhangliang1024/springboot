package com.zhliang.springboot.date.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.zhliang.springboot.date.SpringbootDateApplicationTests;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.*;

public class PersonServiceTest extends SpringbootDateApplicationTests {

    @Autowired
    PersonService personService;

    @Test
    public void person() throws JsonProcessingException {
        personService.person();
    }
}