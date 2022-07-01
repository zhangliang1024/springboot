package com.zhliang.springboot.log4j2.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * @项目名称：springboot
 * @包名：com.zhliang.springboot.log4j2.listener
 * @类描述：
 * @创建人：zhiang
 * @创建时间：2021/3/12 15:59
 * @version：V1.0
 */
//@Component
public class AutoPrintLogListener implements ApplicationListener {

    private static final Logger logger = LoggerFactory.getLogger(AutoPrintLogListener.class);

    @Override
    public void onApplicationEvent(ApplicationEvent event) {
        while (true){
            logger.info("test loging ...");
        }
    }
}
