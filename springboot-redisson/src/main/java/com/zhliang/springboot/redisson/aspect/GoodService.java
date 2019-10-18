package com.zhliang.springboot.redisson.aspect;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: colin
 * @Date: 2019/9/10 17:00
 * @Description:
 * @Version: V1.0
 */
@Slf4j
//@Service
public class GoodService {


    private static Map<Long,Long> goodsMap = new HashMap<>();

    static {
        goodsMap.put(10001L,5000L);
        goodsMap.put(10002L,5000L);
    }

    @RedissonLock(lockIndex = 0)
    public static void multi(Long goodsId){
        Long value = goodsMap.get(goodsId);
        log.info("goodsId : {} -> value :{}",goodsId,value);
        long longValue = value.longValue();
        longValue++;
        goodsMap.put(goodsId,Long.valueOf(longValue));
    }

}
