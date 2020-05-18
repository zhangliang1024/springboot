package com.zhliang.springboot.oss.controller;

import com.zhliang.springboot.oss.cloud.config.CloudStorageConfig;
import com.zhliang.springboot.oss.cloud.OSSFactory;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @Author: colin
 * @Date: 2019/8/20 20:35
 * @Description:
 * @Version: V1.0
 */
@RestController
@Api("OSSController")
public class OSSController {

    @Autowired
    private CloudStorageConfig config;
    @Autowired
    private OSSFactory factory;

    @ApiOperation("文件上传")
    @PostMapping("upload")
    public String upload(@RequestBody MultipartFile file) throws Exception{
        if(file.isEmpty()){
            throw new RuntimeException("上传文件不能为空！");
        }
        //截取后缀
        String suffix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
        String path = factory.build().uploadSuffix(file.getBytes(), suffix);
        return path;
    }

    @ApiOperation("配置信息")
    @GetMapping("config")
    public CloudStorageConfig config(){
        System.out.println(config);
        return config;
    }
}
