package com.zhliang.springboot.miaosha.service;

import com.zhliang.springboot.miaosha.CacheLock;
import com.zhliang.springboot.miaosha.LockedComplexObject;
import com.zhliang.springboot.miaosha.LockedObject;

/**
 * @Author: colin
 * @Date: 2019/9/5 17:04
 * @Description:
 * @Version: V1.0
 */
public interface SeckillService {

    @CacheLock(lockedPrefix = "product_prefix")
    public void seckill(Long userId,@LockedObject Long productId);

}
