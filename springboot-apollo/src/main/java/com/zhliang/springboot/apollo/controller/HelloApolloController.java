package com.zhliang.springboot.apollo.controller;

import com.zhliang.springboot.apollo.config.PeopleConfig;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.RandomAccess;

/**
 * @创建人：zhiang
 * @创建时间：2020/12/22 22:18
 * @version：V1.0
 */
@Slf4j
@RestController
public class HelloApolloController {

    @Value( "${server.port}" )
    private String port;

    @Autowired
    private PeopleConfig peopleConfig;

    @GetMapping("port")
    public String port(String name) {

        log.debug( "debug log..." );
        log.info( "info log..." );
        log.warn( "warn log..." );

        return "hi " + name + " ,i am from port:" + port;

    }


    @GetMapping("hello")
    public void hello() throws Exception{

        while (true){
            log.debug( "debug log..." );
            log.info( "info log..." );
            log.warn( "warn log..." );
            Thread.sleep(2000);

            log.warn(" username is : {}",peopleConfig.getUsername());
        }
    }

}
