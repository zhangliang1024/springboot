package com.zhliang.springboot.custom.starter.service;

import com.zhliang.springboot.custom.starter.autoconfigure.HelloProperties;

/**
 * @项目名称：springboot
 * @包名：com.zhliang.springboot.custom.starter.service
 * @类描述：
 * @创建人：colin
 * @创建时间：2019/12/20 14:39
 * @version：V1.0
 */
public class HelloService {

    HelloProperties helloProperties;

    HelloProperties getHelloProperties() {
        return helloProperties;
    }

    public void setHelloProperties(HelloProperties helloProperties) {
        this.helloProperties = helloProperties;
    }

    public String sayHello(String name) {
        return helloProperties.getPrefix() + "  " + name+"  " + helloProperties.getSuffix();
    }

}
