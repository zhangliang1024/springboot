package com.zhliang.springboot.json.jackjson.domain;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;


import java.io.Serializable;
import java.util.Date;

/**
 * @Author: colin
 * @Date: 2019/8/22 10:32
 * @Description:
 * @Version: V1.0
 */
@Data
public class User implements Serializable {

    /**
     * 方式一 ：
     * 修改Spring Boot默认的JSON解析框架: https://www.jianshu.com/p/fca9cbe2453b
     *
     * 很多时候后台会把主键ID的类型设置成Long类型,这样做虽然有很多好处，但是也存在一个问题，如果Long类型的数据过长的话（比如{id:122000083049775104}）就会导致前端JavaScript在处理ID
     * 的时候丢失精度，它会将id处理成122000083049775100,很明显，原ID貌似被四舍五入了一样。
     *
     * 作者：WalterWong
     * 链接：https://www.jianshu.com/p/fca9cbe2453b
     * 方式二 ：
     * SpringBoot中使用Jackson导致Long型数据精度丢失问题： https://www.cnblogs.com/hahahehexixihoho/p/10214156.html
     */
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;

    @JsonProperty("name")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String username;

    @JsonProperty("nick")
    private String nickname;

    /**
     * 使用自定义的格式转换类
     */
    @JsonSerialize(using = DateToLongSerializer.class)
    private Date createTime;
}
