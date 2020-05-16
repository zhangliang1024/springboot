package com.zhliang.springboot.api.mideng.controller;

import com.zhliang.springboot.api.mideng.annotation.AutoIdempotent;
import com.zhliang.springboot.api.mideng.constant.Constant;
import com.zhliang.springboot.api.mideng.result.ResultVo;
import com.zhliang.springboot.api.mideng.token.TokenService;
import com.zhliang.springboot.api.mideng.utils.JSONUtil;
import com.zhliang.springboot.api.mideng.utils.StrUtil;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @项目名称：springboot
 * @包名：com.zhliang.springboot.api.mideng.controller
 * @类描述：
 * @创建人：zhiang
 * @创建时间：2020/5/6 19:44
 * @version：V1.0
 */
@RestController
public class BusinessController {


    @Resource
    private TokenService tokenService;

    @PostMapping("/get/token")
    public String  getToken(){
        String token = tokenService.createToken();
        if (StrUtil.isNotEmpty(token)) {
            ResultVo resultVo = new ResultVo();
            resultVo.setCode(Constant.code_success);
            resultVo.setMessage(Constant.SUCCESS);
            resultVo.setData(token);
            return JSONUtil.toJsonStr(resultVo);
        }
        return StrUtil.EMPTY;
    }


    @AutoIdempotent
    @PostMapping("/test/Idempotence")
    public String testIdempotence() {
        String businessResult = "hello i am success";
        if (StrUtil.isNotEmpty(businessResult)) {
            ResultVo successResult = ResultVo.getSuccessResult(businessResult);
            return JSONUtil.toJsonStr(successResult);
        }

        return StrUtil.EMPTY;
    }
}
