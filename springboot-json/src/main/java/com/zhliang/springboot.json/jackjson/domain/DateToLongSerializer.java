package com.zhliang.springboot.json.jackjson.domain;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Author: colin
 * @Date: 2019/8/23 11:07
 * @Description: 自定义属性转换时的 格式化方式
 * @Version: V1.0
 */
public class DateToLongSerializer extends JsonSerializer<Date> {

    @Override
    public void serialize(Date date, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        //1375429228382
        jsonGenerator.writeNumber(date.getTime()/1000);

        //Sat Aug 24 10:50:02 CST 2019
        //jsonGenerator.writeString(date.toString());

        //时间格式化
        //SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //String time = format.format(date);
        //jsonGenerator.writeString(time);
    }
}
