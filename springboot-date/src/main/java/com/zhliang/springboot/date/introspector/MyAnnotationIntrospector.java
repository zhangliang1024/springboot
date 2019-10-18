package com.zhliang.springboot.date.introspector;

import com.fasterxml.jackson.databind.introspect.Annotated;
import com.fasterxml.jackson.databind.introspect.AnnotatedMethod;
import com.fasterxml.jackson.databind.introspect.JacksonAnnotationIntrospector;
import com.zhliang.springboot.date.annotation.DateFormat;
import com.zhliang.springboot.date.serializer.DateTimeSerializer;
import org.springframework.stereotype.Component;


/**
 * @Author: colin
 * @Date: 2019/9/3 11:43
 * @Description:
 * 【Jackson 通过自定义注解来控制json key的格式：https://yq.aliyun.com/articles/39478 】
 * @Version: V1.0
 */
@Component
public class MyAnnotationIntrospector extends JacksonAnnotationIntrospector {


    @Override
    public Object findSerializer(Annotated annotated) {
        if(annotated instanceof AnnotatedMethod){
            DateFormat format = annotated.getAnnotated().getAnnotation(DateFormat.class);
            if(format != null){
                return new DateTimeSerializer(format.value());
            }
        }
        return super.findSerializer(annotated);
    }

}
