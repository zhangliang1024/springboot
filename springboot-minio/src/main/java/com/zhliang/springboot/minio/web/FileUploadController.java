package com.zhliang.springboot.minio.web;

import com.zhliang.springboot.minio.service.FileUploadService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/minio/file")
public class FileUploadController {

    @Autowired
    private FileUploadService fileUploadService;

    /**
     * 图片上传minio
     *
     * @param file 图片文件
     * @return 返回
     */
    @PostMapping("/upload")
    public ResponseEntity uploadFileMinio(MultipartFile file) {
        String url = fileUploadService.uploadFileMinio(file);
        if (StringUtils.isNotEmpty(url)) {
            return ResponseEntity.ok(url);
        }
        return ResponseEntity.ok("上传失败！");
    }

}