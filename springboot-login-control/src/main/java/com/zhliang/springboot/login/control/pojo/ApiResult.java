package com.zhliang.springboot.login.control.pojo;

import lombok.Data;

/**
 * @Author: colin
 * @Date: 2019/10/21 10:13
 * @Description:
 * @Version: V1.0
 */
@Data
public class ApiResult {

    private Integer code;

    private String message;

    private Object data;

    public ApiResult(Integer code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

}
