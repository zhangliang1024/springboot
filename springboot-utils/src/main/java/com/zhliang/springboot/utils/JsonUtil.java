package com.zhliang.springboot.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * @创建人：zhiang
 * @创建时间：2020/5/12 15:03
 * @version：V1.0
 */
public class JsonUtil {

    private static final Logger logger = LoggerFactory.getLogger(JsonUtil.class);

    private static final ObjectMapper MAPPER = new ObjectMapper();

    /**
     * 将对象转换成json字符串
     */
    public static String objectToJson(Object data) {
        try {
            return MAPPER.writeValueAsString(data);
        } catch (JsonProcessingException e) {
            logger.error("json parse error : {}",e.getMessage());
            throw new RuntimeException("json parse erro");
        }
    }

    /**
     * 将json结果集转化为对象
     */
    public static <T> T jsonToPo(String json, Class<T> bean) {
        try {
            return MAPPER.readValue(json, bean);
        } catch (Exception e) {
            logger.error("json parse error : {}",e.getMessage());
            throw new RuntimeException("json parse erro");
        }
    }

    /**
     * 将json数据转换成pojo对象list
     */
    public static <T> List<T> jsonToList(String json, Class<T> bean) {
        JavaType javaType = MAPPER.getTypeFactory().constructParametricType(List.class, bean);
        try {
            return MAPPER.readValue(json, javaType);
        } catch (Exception e) {
            logger.error("json parse error : {}",e.getMessage());
            throw new RuntimeException("json parse erro");
        }
    }

}
