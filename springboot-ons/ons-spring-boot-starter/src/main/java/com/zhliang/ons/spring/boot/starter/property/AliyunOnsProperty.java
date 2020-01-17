package com.zhliang.ons.spring.boot.starter.property;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @项目名称：springboot
 * @包名：com.zhliang.ons.spring.boot.starter.property
 * @类描述：阿里云 ons 配置参数
 * @创建人：colin
 * @创建时间：2020/1/2 10:25
 * @version：V1.0
 */
@Data
@ConfigurationProperties(prefix = "aliyun.ons")
public class AliyunOnsProperty {

    /**
     * AliyunOnsProperty 开关 【配合AliMQAutoConfiguration使用】
     */
    boolean enable = false;
    /**
     * 订阅的 topic
     */
    private String topic;
    /**
     * 在 MQ 控制台创建的 Producer ID
     */
    private String producerId;
    /**
     * 在 MQ 控制台创建的 Consumer ID
     */
    private String consumerId;
    /**
     * 鉴权用 AccessKey，在阿里云服务器管理控制台创建
     */
    private String accessKey;
    /**
     * 鉴权用 SecretKey，在阿里云服务器管理控制台创建
     */
    private String secretKey;
    /**
     * 设置 TCP 接入域名
     */
    private String onsAddr;


}
