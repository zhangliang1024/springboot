package com.zhliang.springboot.rest.api.common.exception;


import com.zhliang.springboot.rest.api.common.enums.IResponseEnum;

/**
 * 校验异常
 * 调用接口时，参数格式不合法可以抛出该异常
 * @创建人：zhiang
 * @创建时间：2020/5/15 16:21
 * @version：V1.0
 */
public class ValidationException extends BaseException{

    public ValidationException(IResponseEnum responseEnum, Object[] args, String message) {
        super(responseEnum, args, message);
    }

    public ValidationException(IResponseEnum responseEnum, Object[] args, String message, Throwable cause) {
        super(responseEnum, args, message, cause);
    }
}
