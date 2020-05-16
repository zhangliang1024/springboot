package com.zhliang.springboot.rest.api.common.exception;

import com.zhliang.springboot.rest.api.common.enums.IResponseEnum;

/**
 * @类描述：
 * 业务异常
 *    业务处理出现异常时，可以抛出该异常
 * @创建人：zhiang
 * @创建时间：2020/5/15 16:21
 * @version：V1.0
 */
public class BusinessException extends BaseException {

    public BusinessException(IResponseEnum responseEnum, Object[] args, String msg) {
        super(responseEnum, args, msg);
    }

    public BusinessException(IResponseEnum responseEnum, Object[] args, String msg, Throwable cause) {
        super(responseEnum, args, msg, cause);
    }
}
