package com.zhliang.springboot.rest.api.exception;

import com.zhliang.springboot.rest.api.response.ResultCode;
import lombok.Data;

/**
 * @项目名称：springboot
 * @包名：com.zhliang.springboot.rest.api.exception
 * @类描述：
 * @创建人：colin
 * @创建时间：2020/1/6 12:53
 * @version：V1.0
 */
@Data
public class GlobalException extends RuntimeException {

    private static final long serialVersionUID = 1L;
    private ResultCode resultCode;

    public GlobalException(String msg) {
        super(msg);
    }

    public GlobalException(ResultCode resultCode) {
        super(resultCode.getMessage());
        this.resultCode = resultCode;
    }

}
