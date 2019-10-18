package com.zhliang.springboot.oss.cloud;
import	java.util.Date;

import com.aliyun.oss.OSSClient;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

/**
 * @Author: colin
 * @Date: 2019/8/20 18:45
 * @Description:
 * @Version: V1.0
 */
public class AliyunCloudStorageService extends CloudStorageService {

    private OSSClient client;

    public AliyunCloudStorageService(CloudStorageConfig config){
        this.config = config;
        //初始化
        init();
    }

    private void init(){
        client = new OSSClient(config.getAliyunEndPoint(),config.getAliyunAccessKeyId(),config.getAliyunAccessKeySecret());
    }

    @Override
    public String upload(byte[] data, String path) {
        return upload(new ByteArrayInputStream(data),path);
    }

    @Override
    public String uploadSuffix(byte[] data, String suffix) {
        return upload(data, getPath(config.getAliyunPrefix(),suffix));
    }

    @Override
    public String upload(InputStream inputStream, String path) {
        try{
            client.putObject(config.getAliyunBucketName(),path,inputStream);
        }catch (Exception e){
            throw new RuntimeException("上传文件失败，请检查配置"+e.getMessage());
        }
        return config.getAliyunDomain() + "/" + path;
    }

    @Override
    public String uploadSuffix(InputStream inputStream, String suffix) {
        return upload(inputStream,getPath(config.getAliyunPrefix(),suffix));
    }
}
