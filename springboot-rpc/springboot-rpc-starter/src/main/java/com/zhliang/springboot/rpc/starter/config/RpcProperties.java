package com.zhliang.springboot.rpc.starter.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @项目名称：springboot
 * @包名：com.zhliang.springboot.rpc.starter.config
 * @类描述：
 * @创建人：colin
 * @创建时间：2020/1/2 17:16
 * @version：V1.0
 */
@Data
@ConfigurationProperties(prefix="chyrpc")
public class RpcProperties {

    private String zookeepeer;
    private int port;
    private String ip;

}
