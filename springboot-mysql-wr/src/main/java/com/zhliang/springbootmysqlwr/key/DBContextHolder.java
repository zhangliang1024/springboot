package com.zhliang.springbootmysqlwr.key;

import com.zhliang.springbootmysqlwr.eum.DBTypeEnum;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author: colin
 * @Date: 2019/10/18 11:18
 * @Description:
 *   - 通过ThreadLocal 将数据源设置到每个线程上下文
 * @Version: V1.0
 */
@Slf4j
public class DBContextHolder {


    private static final ThreadLocal<DBTypeEnum> contextHolder = new ThreadLocal();

    private static final AtomicInteger counter = new AtomicInteger(-1);


    public static void set(DBTypeEnum dbType){
        contextHolder.set(dbType);
    }

    public static DBTypeEnum get() {
        return contextHolder.get();
    }

    public static void master(){
        set(DBTypeEnum.MASTER);
        log.info(" 切换到 master 数据源");
    }

    public static void slave(){
        //轮询
        int index = counter.getAndIncrement() % 2;
        if(counter.get() > 9999) {
            counter.set(-1);
        }
        if(index == 0){
            set(DBTypeEnum.SLAVE1);
            log.info(" 切换到 slave1 数据源");
        }else {
            set(DBTypeEnum.SLAVE2);
            log.info(" 切换到 slave2 数据源");
        }
    }

}
