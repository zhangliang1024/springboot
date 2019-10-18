package com.zhliang.springboot.runner.init;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

/**
 * @Author: colin
 * @Date: 2019/10/11 19:03
 * @Description:
 * @Version: V1.0
 */
@Slf4j
@Component
public class InitializingBeanImpl implements InitializingBean {

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("InitializingBean..");
    }
}
