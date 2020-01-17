package com.zhliang.springboot.rpc.consumer.service;

import chy.rpc.annotation.ChyRPCRegist;
import org.springframework.stereotype.Service;

/**
 * @项目名称：springboot
 * @包名：com.zhliang.springboot.rpc.consumer.service
 * @类描述：
 * @创建人：colin
 * @创建时间：2020/1/2 17:57
 * @version：V1.0
 *
 * 因为bean对象 还是要交给 spring管理,我们的注解是在 spring扫描完 对象后才处理,
 * 没有管理bean的能力,所以必须要加上 @Service @Controller 等注解
 *
 */

@Service
@ChyRPCRegist(name = "serviceB")
public class MyServiceB implements ServiceApi{

    @Override
    public String test() {
        System.out.println("service b rpc ...");
        return "service api b";
    }
}
