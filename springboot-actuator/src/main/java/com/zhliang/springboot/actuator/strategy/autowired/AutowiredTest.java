package com.zhliang.springboot.actuator.strategy.autowired;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @创建人：zhiang
 * @创建时间：2020/5/20 11:03
 * @version：V1.0
 */
@RestController
public class AutowiredTest {

    @Autowired
    GetArticleStrategyContext context;

    @GetMapping("/strategy")
    public void strategy(){
        JSONObject jsonObject = context.getArticle("Video");
        JSONObject jsonObject2 = context.getArticle("Image");
        System.out.println(jsonObject);
        System.out.println(jsonObject2);


    }
}
