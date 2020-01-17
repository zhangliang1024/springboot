package com.zhliang.ons.spring.boot.starter.annotation;

/**
 * @Author: colin
 * @Date: 2020/1/2 10:22
 * @Description: ONS注解
 * @Version: V1.0
 */

import com.zhliang.ons.spring.boot.starter.client.MQClient;
import com.zhliang.ons.spring.boot.starter.property.AliyunOnsProperty;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME) // 注解会在class字节码文件中存在，在运行时可以通过反射获取到
@Target({ElementType.TYPE, ElementType.METHOD})
@Documented
@Import({AliyunOnsProperty.class, MQClient.class})
public @interface EnableONS {
}
