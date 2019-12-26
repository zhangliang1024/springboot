package com.zhliang.springboot.validator.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @项目名称：springboot
 * @包名：com.zhliang.springboot.validator.exception
 * @类描述：
 * @创建人：colin
 * @创建时间：2019/12/24 20:10
 * @version：V1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BizException extends RuntimeException {

    private int code;
    private String message;

    public BizException(String message) {
        super(message);
        this.message = message;
    }
}
