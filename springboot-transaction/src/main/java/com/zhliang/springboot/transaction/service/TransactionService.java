package com.zhliang.springboot.transaction.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Author: colin
 * @Date: 2019/7/31 21:00
 * @Description: https://blog.csdn.net/u010963948/article/details/79208328
 * @Version: V1.0
 */
@Slf4j
@Component
public class TransactionService {

    // 使用value具体指定使用哪个事务管理器
    @Transactional(value = "txManagerDefault")
    public void send(){
        log.info("【使用指定的事务管理器】");
    }

    /**
     * 若存在多个事务管理器的情况下，如果使用value具体指定
     * 默认使用方法 annotationDrivenTransactionManager() 返回的事务管理器
     *
     * 注：
     *   1.如果Spring容器存在多个PlatformTransactionMnager实例，并且没有实现接口。即指定默认事务管理器
     *   2.在使用@Transaction注解，必须指定value值，否则会抛出异常
     */
    @Transactional
    public void sendSecond(){
        log.info("【使用默认的事务管理器】");
    }
}
