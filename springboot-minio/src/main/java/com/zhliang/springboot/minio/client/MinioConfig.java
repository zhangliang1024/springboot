package com.zhliang.springboot.minio.client;

import io.minio.MinioClient;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "minio")
public class MinioConfig {
    /**
     * 服务地址
     */
    public String url;
 
    /**
     * 用户名
     */
    public String accessKey;
 
    /**
     * 密码
     */
    public String secretKey;
 
    /**
     * 存储桶名称
     */
    public String bucketName;
 
    // "如果是true，则用的是https而不是http,默认值是true"
    public static Boolean secure = false;
 
    public static void setSecure(Boolean secure) {
        MinioConfig.secure = secure;
    }
 
    @Bean
    public MinioClient getMinioClient() {
        return MinioClient.builder().endpoint(url).credentials(accessKey, secretKey).build();
    }
}