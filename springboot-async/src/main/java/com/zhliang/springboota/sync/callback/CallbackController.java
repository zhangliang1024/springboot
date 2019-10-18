package com.zhliang.springboota.sync.callback;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Future;

/**
 * @Author: colin
 * @Date: 2019/7/29 20:23
 * @Description:
 *  Springboot 异步处理中有返回值：https://blog.csdn.net/qq_29215513/article/details/85264542
 * @Version: V1.0
 */
@Slf4j
@RestController
public class CallbackController {

    @Autowired
    private AsyncTask task;

    @GetMapping("async/callback")
    public String testAsync() throws Exception{
        List<Future<String>> taskFutures = new ArrayList<>();

        for(int i = 0 ;i < 10 ;i++) {
            taskFutures.add(task.doTask(" ===== >>>>>  " + i + "  Thread" )) ;
        }

        task.taskReturnValueMange(taskFutures);
        return "ok";
    }

    private String randomName(){
        Random random = new Random();
        int len = random.nextInt(15) + 5;
        String ret = "";
        for(int i = 0;i < len;i++){
            ret += (char)(random.nextInt(26) + 'A');
        }
        return ret;
    }
}
