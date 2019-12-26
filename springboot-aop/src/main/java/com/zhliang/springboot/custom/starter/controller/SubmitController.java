package com.zhliang.springboot.custom.starter.controller;

import com.zhliang.springboot.custom.starter.annotation.NoRepeatSubmit;
import com.zhliang.springboot.custom.starter.result.ApiResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: colin
 * @Date: 2019/9/25 16:37
 * @Description:
 * @Version: V1.0
 */
@RestController
public class SubmitController {


    @PostMapping("submit")
    @NoRepeatSubmit(lockTime = 30)
    public Object sumbit(@RequestBody UserBean userBean){
        try{
            //模拟业务操作
            Thread.sleep(2000);
        }catch (Exception e){

        }
        return new ApiResult(200,"成功",userBean.getUserId());
    }


    public static class UserBean{
        private String userId;

        public String getUserId(){return userId;}
        public void setUserId(String userId){
            this.userId = userId == null ? null : userId.trim();
        }
    }
}
