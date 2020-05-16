package com.zhliang.springboot.rest.api.common.enums;

import com.zhliang.springboot.rest.api.common.assertion.CommonExceptionAssert;
import com.zhliang.springboot.rest.api.common.exception.BaseException;
import com.zhliang.springboot.rest.api.common.response.BaseResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @项目名称：springboot
 * @包名：com.zhliang.springboot.rest.api.common.enums
 * @类描述：
 * @创建人：zhiang
 * @创建时间：2020/5/15 16:36
 * @version：V1.0
 */
@Getter
@AllArgsConstructor
public enum  CommonResponseEnum implements CommonExceptionAssert {

    /**
     * 成功
     */
    SUCCESS(200, "SUCCESS"),
    /**
     * 服务器繁忙
     */
    SERVER_BUSY(9998, "服务器繁忙"),

    /**
     * 服务器异常
     */
    SERVER_ERROR(9999, "服务器异常"),
    ;

    private int code;

    private String message;

    public static void assertSuccess(BaseResponse response){
        SERVER_ERROR.assertNotNnull(response);
        int code = response.getCode();
        if(SUCCESS.getCode() != code){
            throw new BaseException(code, response.getMessage());
        }
    }
}
