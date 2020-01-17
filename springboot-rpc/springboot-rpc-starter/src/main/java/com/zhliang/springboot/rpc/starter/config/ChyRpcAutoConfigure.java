package com.zhliang.springboot.rpc.starter.config;

import chy.rpc.core.ChyRpcApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @项目名称：springboot
 * @包名：com.zhliang.springboot.rpc.starter.config
 * @类描述：
 * @创建人：colin
 * @创建时间：2020/1/2 17:17
 * @version：V1.0
 */

@Configuration
@ConditionalOnClass(ChyRpcApplication.class)//必须要引入了 我手写的 rpc框架才能生效
@EnableConfigurationProperties(RpcProperties.class)//能够读取我自定义的配置文件
public class ChyRpcAutoConfigure {

    @Autowired
    RpcProperties rpcProperties;

    @Bean
    //如果用户自定义了一个 ChyRpcApplication 就不创建,如果没有就在spring 容器中加入一个默认配置的rpc容器,ChyRpcApplication也是我RPC的核心类,类似 sessionFactory
    @ConditionalOnMissingBean(ChyRpcApplication.class)
    public ChyRpcApplication initChyRpcApplication(){
        ChyRpcApplication chyRpcApplication = new ChyRpcApplication(rpcProperties.getZookeepeer());
        chyRpcApplication.setPort(rpcProperties.getPort());
        chyRpcApplication.setIp(rpcProperties.getIp());
        return chyRpcApplication;
    }

}
