package com.zhliang.springboot.rest.api.common.enums;

import com.zhliang.springboot.rest.api.common.assertion.CommonExceptionAssert;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @项目名称：springboot
 * @包名：com.zhliang.springboot.rest.api.common.enums
 * @类描述：
 * @创建人：zhiang
 * @创建时间：2020/5/16 11:02
 * @version：V1.0
 */
@Getter
@AllArgsConstructor
public enum  ArgumentResponseEnum implements CommonExceptionAssert {

    VALID_ERROR(6000, "参数校验异常"),
    ;

    private int code;

    private String message;

}
