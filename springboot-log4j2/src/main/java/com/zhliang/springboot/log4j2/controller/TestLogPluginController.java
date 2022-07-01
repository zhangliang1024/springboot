package com.zhliang.springboot.log4j2.controller;

import com.zhliang.springboot.log4j2.listener.AutoPrintLogListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @项目名称：springboot
 * @包名：com.zhliang.springboot.log4j2.controller
 * @类描述：
 * @创建人：zhiang
 * @创建时间：2021/3/14 15:22
 * @version：V1.0
 */
@RestController
public class TestLogPluginController {

    private static final Logger logger = LoggerFactory.getLogger(TestLogPluginController.class);


    @GetMapping("plugin")
    public void testPlugin(){
        while (true){
            logger.info("test plugin number is : {}","123234820294434434");
        }
    }
}
