package com.zhliang.springboot.rest.api.response;

import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * @项目名称：springboot
 * @包名：com.zhliang.springboot.rest.api.response
 * @类描述：
 * @创建人：colin
 * @创建时间：2020/1/6 11:26
 * @version：V1.0
 */
@Data
@NoArgsConstructor
public class ErrorResult {

    private Integer code;
    private String message;

    public ErrorResult(Integer code,String message){
        this.code = code;
        this.message = message;
    }

    public ErrorResult(ResultCode resultCode){
        this.code = resultCode.getCode();
        this.message = resultCode.getMessage();
    }


    public static ErrorResult failure(Integer code,String message) {
        ErrorResult result = new ErrorResult(code,message);
        return result;
    }
    public static ErrorResult failure(ResultCode resultCode) {
        ErrorResult result = new ErrorResult(resultCode);
        return result;
    }

    public static ErrorResult PARAM_TYPE_BIND_ERROR = new ErrorResult(ResultCode.PARAM_TYPE_BIND_ERROR.getCode(),
            "服务端绑定异常: %s");

    public ErrorResult fillArgs(Object ... args) {
        ErrorResult result = new ErrorResult();
        result.setCode(this.code);
        result.setMessage(String.format(message,args));
        return result;

    }
}
