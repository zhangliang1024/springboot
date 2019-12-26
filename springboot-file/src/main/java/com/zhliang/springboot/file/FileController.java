package com.zhliang.springboot.file;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * @项目名称：springboot
 * @包名：com.zhliang.springboot.file
 * @类描述：
 * @创建人：colin
 * @创建时间：2019/12/10 16:48
 * @version：V1.0
 */
@Controller
public class FileController {

    @Value("${fishpro.uploadPath}")
    private String uploadPath;

    @GetMapping("/uploadfile")
    public String uploadfile(){
        return "uploadfile";
    }


    /**
     * 单文件
     * */
    @PostMapping("/upload")
    @ResponseBody
    Object upload(@RequestParam("file") MultipartFile file, HttpServletRequest request) {
        Map<String,Object> map=new HashMap();
        map.put("status",0);
        String fileName = file.getOriginalFilename();
        fileName = UUID.randomUUID().toString(); //对文件名称重命名

        try {
            FileUtil.uploadFile(file.getBytes(), uploadPath, fileName);
            map.put("filename",fileName);
        } catch (Exception e) {
            map.put("status",-1);
            map.put("message",e.getMessage());
        }

        return map;
    }

    /**
     * 多文件
     * */
    @PostMapping("/uploads")
    @ResponseBody
    Object uploads(@RequestParam("files") MultipartFile[] files, HttpServletRequest request) {
        Map<String,Object> map=new HashMap();
        map.put("status",0);
        List<String> filenames=new ArrayList<>();
        for (MultipartFile file: files) {
            String ext = file.getOriginalFilename().split("\\.")[1];
            String fileName = file.getOriginalFilename();
            //fileName = UUID.randomUUID().toString()+"."+ext; //对文件名称重命名

            try {
                FileUtil.uploadFile(file.getBytes(), uploadPath, fileName);
                filenames.add(fileName);
            } catch (Exception e) {
                map.put("status",-1);
                map.put("message",e.getMessage());
                return  map;
            }
        }

        map.put("filename",filenames);
        return map;
    }
}
