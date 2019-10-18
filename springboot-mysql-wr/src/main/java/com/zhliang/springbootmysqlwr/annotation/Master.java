package com.zhliang.springbootmysqlwr.annotation;

/**
 * @Author: colin
 * @Date: 2019/10/18 11:37
 * @Description:
 *  - 有些特殊情况，需要我们强制读主库。
 *    针对这些情况，我们自定义一个主键，用该注解标注的就读主库
 * @Version: V1.0
 */
public @interface Master {
}
