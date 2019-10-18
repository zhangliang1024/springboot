package com.zhliang.springboot.mongodb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: colin
 * @Date: 2019/10/9 16:57
 * @Description:
 * @Version: V1.0
 */
@RestController
public class MongoController {


    @Autowired
    private MongoTestDao mtdao;

    @GetMapping(value="/test1")
    public void saveTest() throws Exception {
        MongoTest mgtest=new MongoTest();
        mgtest.setId(11);
        mgtest.setAge(33);
        mgtest.setName("ceshi");
        mtdao.saveTest(mgtest);
    }

    @GetMapping(value="/test2")
    public MongoTest findTestByName(){
        MongoTest mgtest= mtdao.findTestByName("ceshi");
        System.out.println("mgtest is "+mgtest);
        return mgtest;
    }

    @GetMapping(value="/test3")
    public void updateTest(){
        MongoTest mgtest=new MongoTest();
        mgtest.setId(11);
        mgtest.setAge(44);
        mgtest.setName("ceshi2");
        mtdao.updateTest(mgtest);
    }

    @GetMapping(value="/test4")
    public void deleteTestById() {
        mtdao.deleteTestById(11);
    }

}
