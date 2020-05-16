package com.zhliang.springbootapiui.exception;

/**
 * @项目名称：springboot
 * @包名：com.zhliang.springbootapiui.exception
 * @类描述：
 * @创建人：zhiang
 * @创建时间：2020/5/6 09:50
 * @version：V1.0
 */
public class BusinessException extends BaseException {

    private static final long serialVersionUID = 1L;

    public BusinessException(IResponseEnum responseEnum, Object[] args, String message) {
        super(responseEnum, args, message);
    }

    public BusinessException(IResponseEnum responseEnum, Object[] args, String message, Throwable cause) {
        super(responseEnum, args, message, cause);
    }
}
