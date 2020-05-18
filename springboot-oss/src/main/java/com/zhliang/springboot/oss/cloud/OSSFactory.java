package com.zhliang.springboot.oss.cloud;
import com.zhliang.springboot.oss.cloud.config.CloudStorageConfig;
import com.zhliang.springboot.oss.cloud.service.AliyunCloudStorageService;
import com.zhliang.springboot.oss.cloud.service.CloudStorageService;
import com.zhliang.springboot.oss.cloud.service.QcloudCloudStorageService;
import com.zhliang.springboot.oss.cloud.service.QiniuCloudStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


/**
 * @Author: colin
 * @Date: 2019/8/20 20:05
 * @Description: 文件上传工厂方法
 * @Version: V1.0
 */
@Component
public final class OSSFactory {

    //注入 云配置信息
    @Autowired
    private CloudStorageConfig config;

    public CloudStorageService build(){
        if(config.getType() == CloudService.QINIU.getValue()){
            return new QiniuCloudStorageService(config);
        }else if(config.getType() == CloudService.ALIYUN.getValue()){
            return new AliyunCloudStorageService(config);
        }else if(config.getType() == CloudService.QCLOUD.getValue()) {
            return new QcloudCloudStorageService(config);
        }
        return null;
    }

}
