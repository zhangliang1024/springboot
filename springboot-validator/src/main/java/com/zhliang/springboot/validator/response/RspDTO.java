package com.zhliang.springboot.validator.response;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * @项目名称：springboot
 * @包名：com.zhliang.springboot.validator.response
 * @类描述：
 * @创建人：colin
 * @创建时间：2019/12/24 20:10
 * @version：V1.0
 */
@Data
@NoArgsConstructor
public class RspDTO<T> {

    private int code;
    private String message;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private T data;

    public RspDTO(int code,String message){
        this.code = code;
        this.message = message;
    }

    public RspDTO(int code,String message,T data){
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public static RspDTO success() {
        return new RspDTO(200,"success");
    }


    public RspDTO success(T data) {
        return new RspDTO(200,"success",data);
    }

    public RspDTO nonAbsent(String message) {
        return new RspDTO(500,message);
    }
}
