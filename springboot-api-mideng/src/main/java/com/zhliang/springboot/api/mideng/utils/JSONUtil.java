package com.zhliang.springboot.api.mideng.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zhliang.springboot.api.mideng.result.ResultVo;

/**
 * @项目名称：springboot
 * @包名：com.zhliang.springboot.api.mideng.utils
 * @类描述：
 * @创建人：zhiang
 * @创建时间：2020/5/6 19:33
 * @version：V1.0
 */
public class JSONUtil {

    public static String toJsonStr(ResultVo result) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.writeValueAsString(result);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }
}
