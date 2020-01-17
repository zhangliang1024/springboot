package com.zhliang.springboot.rest.api.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Bean;

/**
 * @项目名称：springboot
 * @包名：com.zhliang.springboot.rest.api.entity
 * @类描述：
 * @创建人：colin
 * @创建时间：2020/1/6 10:09
 * @version：V1.0
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Order {

    private Integer id;
    private String orderName;

}
