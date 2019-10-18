package com.zhliang.springboot.redisson.base;

import com.zhliang.springboot.redisson.SpringbootRedissonApplicationTests;
import org.junit.Assert;
import org.junit.Test;
import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * @Author: colin
 * @Date: 2019/9/27 10:07
 * @Description:
 * @Version: V1.0
 */
public class MockServiceTest extends SpringbootRedissonApplicationTests {


    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Resource
    private Redisson redisson;
    /**
     * 设置一个key，aaa商品的库存数量为100
     */
    @Test
    public void test() throws Exception {
        stringRedisTemplate.opsForValue().set("aaa","100");
        Assert.assertEquals("100", stringRedisTemplate.opsForValue().get("aaa"));
        System.out.println("redis save do");
        Thread.sleep(2000);
    }


    private String lockKey = "testRedisson";//分布式锁的key


    @Test
    public void testDistributed() throws Exception{
        //执行的业务代码
        for(int i=0; i < 55; i++){
            RLock lock = redisson.getLock(lockKey);
            lock.lock(60, TimeUnit.SECONDS); //设置60秒自动释放锁  （默认是30秒自动过期）
            int stock = Integer.parseInt(stringRedisTemplate.opsForValue().get("aaa").toString());
            if(stock > 0){
                stringRedisTemplate.opsForValue().set("aaa",(stock-1)+"");
                System.out.println("test2_:lockkey:"+lockKey+",stock:"+(stock-1)+"");
            }
            lock.unlock(); //释放锁
        }

        Thread.sleep(2000);
    }


}
