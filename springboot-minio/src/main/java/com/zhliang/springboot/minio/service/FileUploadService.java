package com.zhliang.springboot.minio.service;

import com.zhliang.springboot.minio.client.MinioConfig;
import com.zhliang.springboot.minio.utils.MinioUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileUploadService {
 
    @Autowired
    private MinioConfig minioConfig;
 
    @Autowired
    private MinioUtils minioUtils;
 
    public String uploadFileMinio(MultipartFile file) {
        if (file.isEmpty()) {
            throw new RuntimeException("文件不存在！");
        }
        // 判断存储桶是否存在
        if (!minioUtils.bucketExists(minioConfig.getBucketName())) {
            minioUtils.makeBucket(minioConfig.getBucketName());
        }
        // 生成文件名
        String fineName = null;//FileUploadUtils.extractFilename(file);
        try {
            // 上传文件
            minioUtils.upload(file, fineName, minioConfig.getBucketName());
        } catch (Exception e) {
            return null;
        }
 
        String url = minioConfig.getUrl() + "/" + minioConfig.getBucketName() + "/" + fineName;
 
        return url;
    }
}