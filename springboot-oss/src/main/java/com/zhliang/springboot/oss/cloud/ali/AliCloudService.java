package com.zhliang.springboot.oss.cloud.ali;
import java.io.*;
import java.util.List;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.BucketInfo;
import com.aliyun.oss.model.OSSObject;
import com.aliyun.oss.model.OSSObjectSummary;
import com.aliyun.oss.model.ObjectListing;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @Author: colin
 * @Date: 2019/8/21 10:29
 * @Description:
 * @Version: V1.0
 */
@Slf4j
@Service
public class AliCloudService {

    private static String endpoint = "http://oss-cn-beijing.aliyuncs.com";
    private static String accessKeyId = "LTAIHZdBPBkkbArT";
    private static String accessKeySecret = "C4nRidPK9cN06XgaJsPQfmY7sXgYaz";
    private static String bucketName = "zhliang1024";
    private static String firstKey = "my-first-key";

    private static OSSClient client;

    static{
        client = new OSSClient(endpoint,accessKeyId, accessKeySecret);
    }

    /**
     * 是否创建Bucket
     */
    public static void create(){
        //判断bucket是否创建
        if(client.doesBucketExist(bucketName)){
            log.info("此Bucket已创建{}",bucketName);
        }else {
            //创建bucket
            client.createBucket(bucketName);
        }
    }

    /**
     * 查看Bucket信息
     */
    public static void detail(){
        BucketInfo info = client.getBucketInfo(bucketName);
        log.info("Bucket{}的信息如下：",bucketName);
        log.info("数据中心：{}",info.getBucket().getLocation());
        log.info("创建时间：{}",info.getBucket().getCreationDate());
        log.info("用户标志：{}",info.getBucket().getOwner());
    }

    /**
     * 存入字符串
     * @param message
     */
    public static void saveString(String message){
        InputStream is = new ByteArrayInputStream(message.getBytes());
        client.putObject(bucketName, firstKey,is);
        log.info("Object:{}存入OSS成功！",firstKey);
    }

    /**
     * 下载文件
     * @throws IOException
     */
    public static void download() throws IOException {
        OSSObject ossObject = client.getObject(bucketName, firstKey);
        InputStream in = ossObject.getObjectContent();
        StringBuilder sb = new StringBuilder();
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
        while (true){
            String line = reader.readLine();
            if(line == null)
                break;
            sb.append(line);
        }
        in.close();
        log.info("Object:{}的内容是：{}",firstKey,sb);
    }

    /**
     * 上传文件 存储入OSS
     * @param path 文件地址
     */
    public static void uploadFile(String path){
        log.info("存入文件路径：{}",path);
        client.putObject(bucketName,firstKey,new File(path));
        log.info("Object:{}存入OSS成功！",firstKey);
    }

    /**
     * 查看Bucket中的Object
     */
    public static void listObject(){
        ObjectListing objectListing = client.listObjects(bucketName);
        List<OSSObjectSummary> list = objectListing.getObjectSummaries();
        log.info("Bucket:{}中有以下Object:",bucketName);
        for (OSSObjectSummary summary : list) {
            log.info("\t {}",summary.getKey());
        }
    }

    /**
     * 删除Object
     */
    public static void deleteObject(){
        client.deleteObject(bucketName, firstKey);
        log.info("删除Object：{}成功！",firstKey);
    }

    public static void main(String[] args) {
        create();
        listObject();
    }
}
