package com.zhliang.springboot.custom.starter.autoconfigure;
import	java.util.ArrayList;
import java.util.List;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @项目名称：springboot
 * @包名：com.zhliang.springboot.custom.starter.autofigure
 * @类描述：问候属性配置
 * @创建人：colin
 * @创建时间：2019/11/18 13:39
 * @version：V1.0
 */
@Data
@ConfigurationProperties(prefix = "rgyb.greeting")
public class GreetingProperties {

    /**
     * GreetingProperties 开关
     */
    boolean enable = false;

    /**
     * 需要打招呼的成员列表
     */
    List<String> members = new ArrayList<> ();
}
