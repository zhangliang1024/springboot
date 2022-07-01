package com.zhliang.springboot.actuator.strategy.annotation.more;

import com.zhliang.springboot.actuator.strategy.annotation.OrderHandler;
import com.zhliang.springboot.actuator.strategy.annotation.po.Order;
import org.springframework.stereotype.Service;

/**
 * @项目名称：springboot
 * @包名：com.zhliang.springboot.actuator.strategy.annotation
 * @类描述：
 * @创建人：zhiang
 * @创建时间：2020/9/14 19:23
 * @version：V1.0
 */
@Service
@OrderHandlerTypeI(source = "pc",payMethod = "wechat")
public class PCOrderHandlerI implements OrderHandler {

    @Override
    public void handle(Order order) {
        System.out.println("处理PC端订单");
    }
}
