package com.zhliang.springboot.rest.api.common.response;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 包装具体对象
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class CommonResponse<T> extends BaseResponse {

    private T data;

    public CommonResponse() {
        super();
    }

    public CommonResponse(T data) {
        super();
        this.data = data;
    }
}
