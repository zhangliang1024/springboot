package com.zhliang.springboot.runner.event;

import org.springframework.context.ApplicationEvent;

/**
 * @Author: colin
 * @Date: 2019/10/11 19:06
 * @Description: 构造一个事件
 * @Version: V1.0
 */
public class EventImpl extends ApplicationEvent {

    private static final long serialVersionUID = -376299954511699499L;

    public EventImpl(Object source) {
        super(source);
    }


    private String message;

    public void getMessage() {
        System.out.println(message);
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
