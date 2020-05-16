package com.zhliang.springboot.rest.api.common.response;

import com.zhliang.springboot.rest.api.common.enums.CommonResponseEnum;
import com.zhliang.springboot.rest.api.common.enums.IResponseEnum;
import lombok.Data;

/**
 * @类描述：
 *   基础返回类型
 * @创建人：zhiang
 * @创建时间：2020/5/15 16:31
 * @version：V1.0
 */
@Data
public class BaseResponse {

    private int code;

    private String message;


    public BaseResponse(){
        //默认创建成功响应
        this(CommonResponseEnum.SUCCESS);
    }

    public BaseResponse(IResponseEnum responseEnum){
        this(responseEnum.getCode(), responseEnum.getMessage());
    }

    public BaseResponse(int code,String message){
       this.code = code;
       this.message = message;
    }


}
