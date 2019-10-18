package com.zhliang.springboot.miaosha.service;
import	java.util.HashMap;

import java.util.Map;

/**
 * @Author: colin
 * @Date: 2019/9/5 17:06
 * @Description:
 * @Version: V1.0
 */
public class SeckillInterfaceImpl implements SeckillService {

    static Map<Long,Long> inventory;

    static{
        inventory = new HashMap<Long, Long> ();
        inventory.put(1000001L, 10001L);
        inventory.put(1000002L, 10002L);
    }

    @Override
    public void seckill(Long userId, Long productId) {
        //模拟最简单的秒杀，库存减一
        reduceInventory(productId);
    }

    private Long reduceInventory(Long productId) {
        inventory.put(productId,inventory.get(productId) - 1);
        return inventory.get(productId);
    }
}
