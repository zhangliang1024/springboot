package com.zhliang.springboot.oss.cloud.service;

import com.zhliang.springboot.oss.cloud.config.CloudStorageConfig;
import com.zhliang.springboot.oss.utils.DateUtils;
import org.apache.commons.lang.StringUtils;

import java.io.InputStream;
import java.util.Date;
import java.util.UUID;

/**
 * @Author: colin
 * @Date: 2019/8/20 17:58
 * @Description:  云存储实现逻辑
 * @Version: V1.0
 */
public abstract class CloudStorageService {

    /**
     * 云存储配置信息
     */
    CloudStorageConfig config;


    /**
     * 文件路径
     * @param prefix 前缀
     * @param suffix 后缀
     * @return 返回上传路径
     */
    public String getPath(String prefix,String suffix){
        //生成UUID
        String uuid = UUID.randomUUID().toString().replaceAll("-","");
        //文件路径
        String path = DateUtils.format(new Date(),"yyyyMMdd") + "/" + uuid;

        if(StringUtils.isNotBlank(prefix)){
            path = prefix + "/" + path;
        }
        return path + suffix;
    }


    /**
     * 文件上传
     * @param data  文件字节数组
     * @param path  文件路径，包含文件名
     * @return      返回http地址
     */
    public abstract String upload(byte[] data,String path);


    /**
     * 文件上传
     * @param data    文件字节数组
     * @param suffix  后缀
     * @return        返回http地址
     */
    public abstract String uploadSuffix(byte[] data,String suffix);


    /**
     * 文件上传
     * @param inputStream 文件字节流
     * @param path        文件路径，包含文件名
     * @return            返回http地址
     */
    public abstract String upload(InputStream inputStream,String path);

    /**
     * 文件上传
     * @param inputStream 文件字节流
     * @param suffix      后缀
     * @return            返回http地址
     */
    public abstract String uploadSuffix(InputStream inputStream,String suffix);


}
