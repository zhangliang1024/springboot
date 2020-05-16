package com.zhliang.springboot.rest.api.common.response;

/**
 * @项目名称：springboot
 * @包名：com.zhliang.springboot.rest.api.common.response
 * @类描述：
 * @创建人：zhiang
 * @创建时间：2020/5/15 17:26
 * @version：V1.0
 */
public class ErrorResponse extends BaseResponse {

    public ErrorResponse() {
    }

    public ErrorResponse(int code, String message) {
        super(code, message);
    }
}
