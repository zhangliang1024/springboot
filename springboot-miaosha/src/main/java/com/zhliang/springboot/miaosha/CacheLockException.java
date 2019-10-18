package com.zhliang.springboot.miaosha;

import lombok.Data;

/**
 * @Author: colin
 * @Date: 2019/9/5 16:24
 * @Description:
 * @Version: V1.0
 */
@Data
public class CacheLockException extends Throwable {

    private String msg;

    public CacheLockException(){}

    public CacheLockException(String msg){
        this.msg = msg;
    }
}
