package com.zhliang.springboot.date.serializer;


import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Author: colin
 * @Date: 2019/9/3 10:20
 * @Description:
 * @Version: V1.0
 */
public class DateTimeSerializer extends JsonSerializer<Date> {

    /**
     * 用于 存储日期串行化格式
     */
    private final String key;

    /**
     * init 初始化key值
     */
    public DateTimeSerializer(String key) {
        super();
        this.key = key;
    }

    @Override
    public void serialize(Date date, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        String output = StringUtils.EMPTY;
        if(date != null){
            //将日期格式 转换为 字符串格式
            output = new SimpleDateFormat(key).format(date);
        }
        //输出转换结果
        jsonGenerator.writeString(output);
    }
}
