package com.zhliang.springboot.oss.cloud.service;

import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;
import com.zhliang.springboot.oss.cloud.config.CloudStorageConfig;
import org.apache.commons.io.IOUtils;

import java.io.InputStream;

/**
 * @Author: colin
 * @Date: 2019/8/20 20:15
 * @Description: 七牛云存储
 * @Version: V1.0
 */
public class QiniuCloudStorageService extends CloudStorageService {

    private UploadManager manager;
    private String token;

    public QiniuCloudStorageService(CloudStorageConfig config){
        this.config = config;
        //初始化
        init();
    }

    private void init(){
        manager = new UploadManager(new Configuration(Zone.autoZone()));
        token = Auth.create(config.getQiniuAccessKey(),config.getQiniuSecretKey()).uploadToken(config.getQiniuBucketName());
    }

    @Override
    public String upload(byte[] data, String path) {
        try{
            Response res = manager.put(data, path, token);
            if(!res.isOK()){
                throw new RuntimeException("上传七牛云出错" + res.toString());
            }
        }catch (Exception e){
            throw new RuntimeException("上传文件失败，请核对七牛配置信息！");
        }
        return config.getQiniuDomain() + "/" + path;
    }

    @Override
    public String uploadSuffix(byte[] data, String suffix) {
        return upload(data, getPath(config.getQiniuPrefix(),suffix));
    }

    @Override
    public String upload(InputStream inputStream, String path) {
        try{
            byte[] data = IOUtils.toByteArray(inputStream);
            return this.upload(data, path);
        }catch (Exception e){
            throw new RuntimeException("上传文件失败！");
        }
    }

    @Override
    public String uploadSuffix(InputStream inputStream, String suffix) {
        return upload(inputStream, getPath(config.getQiniuPrefix(), suffix));
    }
}
