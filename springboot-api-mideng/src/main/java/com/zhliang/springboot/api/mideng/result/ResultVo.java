package com.zhliang.springboot.api.mideng.result;

import lombok.*;

/**
 * @项目名称：springboot
 * @包名：com.zhliang.springboot.api.mideng.result
 * @类描述：
 * @创建人：zhiang
 * @创建时间：2020/5/6 19:33
 * @version：V1.0
 */
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ResultVo {

    private int code ;
    private String message;
    private Object data;

    public static ResultVo getFailedResult(int code, String message) {
        return new ResultVo(code,message,null);
    }

    public static ResultVo getSuccessResult(String message) {
        return new ResultVo(200,message,null);
    }
}
