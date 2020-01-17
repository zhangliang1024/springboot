package com.zhliang.springboot.rest.api.service;

import com.zhliang.springboot.rest.api.entity.Order;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

/**
 * @项目名称：springboot
 * @包名：com.zhliang.springboot.rest.api.service
 * @类描述：
 * @创建人：colin
 * @创建时间：2020/1/6 10:07
 * @version：V1.0
 */
@Service
public class OrderService {

    private static Map<Integer,Order> data = new HashMap<Integer, Order>();

    @PostConstruct
    public void init(){
        Order order1 = new Order();
        order1.setId(1);
        order1.setOrderName("order1");
        data.put(1,order1);

        Order order2 = new Order();
        order2.setId(2);
        order2.setOrderName("order2");
        data.put(2,order2);
    }

    public Order getOrderById(Integer id) {
        return data.get(id);
    }
}
