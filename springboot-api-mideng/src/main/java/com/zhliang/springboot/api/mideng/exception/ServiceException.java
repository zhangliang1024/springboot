package com.zhliang.springboot.api.mideng.exception;

import com.zhliang.springboot.api.mideng.enums.ResponseCode;

/**
 * @项目名称：springboot
 * @包名：com.zhliang.springboot.api.mideng.exception
 * @类描述：
 * @创建人：zhiang
 * @创建时间：2020/5/6 15:09
 * @version：V1.0
 */
public class ServiceException extends RuntimeException{

    private ResponseCode responseCode;

    public ServiceException(String message) {
        super(message);
    }

    public ServiceException(ResponseCode responseCode,int code){
        super(responseCode.getMsg());
    }

}
