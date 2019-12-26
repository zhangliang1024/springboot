package com.zhliang.springboot.custom.starter.result;

import lombok.Data;

/**
 * @Author: colin
 * @Date: 2019/9/25 16:43
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
