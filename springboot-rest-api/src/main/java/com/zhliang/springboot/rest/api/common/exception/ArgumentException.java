package com.zhliang.springboot.rest.api.common.exception;

import com.zhliang.springboot.rest.api.common.enums.IResponseEnum;

/**
 * 参数异常
 *   在处理业务的过程中校验参数出现错误，可以抛该异常
 *   编写公共代码时，对传入参数校验不通过。也可抛该异常
 * @创建人：zhiang
 * @创建时间：2020/5/15 16:10
 * @version：V1.0
 */
public class ArgumentException extends BaseException {

    public ArgumentException(IResponseEnum responseEnum, Object[] args, String msg) {
        super(responseEnum, args, msg);
    }

    public ArgumentException(IResponseEnum responseEnum, Object[] args, String msg, Throwable cause) {
        super(responseEnum, args, msg, cause);
    }
}
