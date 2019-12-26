package com.zhliang.springboot.validator.exception;

/**
 * @项目名称：springboot
 * @包名：com.zhliang.springboot.validator.exception
 * @类描述：
 * @创建人：colin
 * @创建时间：2019/12/24 20:11
 * @version：V1.0
 */
public class DuplicateKeyException extends RuntimeException{

    private String message;

    public DuplicateKeyException(String message) {
        super(message);
        this.message = message;
    }
}
