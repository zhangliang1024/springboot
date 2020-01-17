package com.zhliang.springboot.rpc.consumer.controller;

import chy.rpc.core.ChyRpcApplication;
import com.zhliang.springboot.rpc.consumer.service.MyService;
import com.zhliang.springboot.rpc.consumer.service.ServiceApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @项目名称：springboot
 * @包名：com.zhliang.springboot.rpc.consumer.controller
 * @类描述：
 * @创建人：colin
 * @创建时间：2020/1/2 17:52
 * @version：V1.0
 */
@RestController
public class Mycontroller {

    @Autowired
    public MyService myService;
    @Resource(name = "myServiceB")
    @Autowired
    public ServiceApi serviceApi;

    @Autowired
    public ChyRpcApplication chyRpcApplication;


    @GetMapping("/regist")
    public String regist() throws Exception {
        chyRpcApplication.register("nameSB",myService);
        return "注册成功";
    }

    @GetMapping("/test2")
    public String test2() throws Exception {
        ServiceApi serviceApi = chyRpcApplication.getService("nameSB",ServiceApi.class);
        return serviceApi.test();
    }

    @GetMapping("/test3")
    public String test3() {
        return serviceApi.test();
    }

}
