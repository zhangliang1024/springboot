package com.zhliang.springboot.oss.cloud;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * @Author: colin
 * @Date: 2019/8/20 17:52
 * @Description: 云存储配置信息
 * @Version: V1.0
 */
@Data
@Component
//@ConfigurationProperties(prefix = "cloud")
public class CloudStorageConfig {

    /**
     * 类型 1：七牛  2：阿里云  3：腾讯云
     */
    private Integer type;


    /**七牛绑定的域名*/
    private String qiniuDomain;
    private String qiniuPrefix;
    /**七牛AccessKey*/
    private String qiniuAccessKey;
    /**七牛SecretKey*/
    private String qiniuSecretKey;
    /**七牛空间名*/
    private String qiniuBucketName;


    /**阿里云绑定的域名*/
    private String aliyunDomain;
    private String aliyunPrefix;
    /**阿里云EndPoint*/
    private String aliyunEndPoint;
    /**阿里云AccessKeyId*/
    private String aliyunAccessKeyId;
    /**阿里云AccessKeySecret*/
    private String aliyunAccessKeySecret;
    /**阿里云BucketName*/
    private String aliyunBucketName;


    /**腾讯云绑定的域名*/
    private String qcloudDomain;
    private String qcloudPrefix;
    /**腾讯云AppId*/
    private Integer qcloudAppId;
    /**腾讯云SecretId*/
    private String qcloudSecretId;
    /**腾讯云SecretKey*/
    private String qcloudSecretKey;
    /**腾讯云BucketName*/
    private String qcloudBucketName;
    /**所属地区*/
    private String qcloudRegion;

}
