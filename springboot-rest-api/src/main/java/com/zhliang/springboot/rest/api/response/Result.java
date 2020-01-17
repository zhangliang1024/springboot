package com.zhliang.springboot.rest.api.response;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.io.Serializable;
import java.util.List;

/**
 * @项目名称：springboot
 * @包名：com.zhliang.springboot.rest.api
 * @类描述：
 * @创建人：colin
 * @创建时间：2020/1/6 10:04
 * @version：V1.0
 */
@Data
@NoArgsConstructor
public class Result implements Serializable {

    private Integer code;
    private String message;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Object data;

    public Result(ResultCode resultCode,Object data){
        this.code = resultCode.getCode();
        this.message = resultCode.getMessage();
        this.data = data;
    }
    public Result(ResultCode resultCode){
        this.code = resultCode.getCode();
        this.message = resultCode.getMessage();
    }
    public Result(Integer code,String message){
        this.code = code;
        this.message = message;
    }


    /**
     * 返回成功
     */
    public static Result success(){
        Result result = new Result(ResultCode.SUCCESS);
        return result;
    }

    /**
     * 返回成功，有返回数据
     */
    public static Result success(Object data){
        Result result = new Result(ResultCode.SUCCESS,data);
        return result;
    }


    /**
     * 返回失败
     */
    public static Result failure(ResultCode resultCode){
        Result result = new Result(resultCode);
        return result;
    }

    /**
     * 返回失败，有返回数据
     */
    public static Result failure(ResultCode resultCode,Object data){
        Result result = new Result(resultCode,data);
        return result;
    }

    public static Object failure(Integer code, String message) {
        Result result = new Result(code,message);
        return result;
    }
}
