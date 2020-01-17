package com.zhliang.springboot.rpc.consumer.service;

import chy.rpc.annotation.ChyRPCServiceFind;

/**
 * @项目名称：springboot
 * @包名：com.zhliang.springboot.rpc.consumer.service
 * @类描述：
 * @创建人：colin
 * @创建时间：2020/1/2 17:57
 * @version：V1.0
 */
@ChyRPCServiceFind(serviceName = "service01")
public interface ServiceApi {

    String test() ;
}
