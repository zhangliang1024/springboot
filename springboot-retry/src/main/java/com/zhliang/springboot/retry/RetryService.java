package com.zhliang.springboot.retry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;

/**
 * @项目名称：springboot
 * @包名：com.zhliang.springboot.retry
 * @类描述：
 * @创建人：colin
 * @创建时间：2019/12/31 17:33
 * @version：V1.0
 */
@Service
public class RetryService {

    private Logger logger = LoggerFactory.getLogger(RetryService.class);

    @Retryable(value = Exception.class, maxAttempts = 3, backoff = @Backoff(delay = 2000L, multiplier = 2))
    public int devide(double a, double b){
        logger.info("开始进行除法运算");
        if (b == 0) {
            throw new RuntimeException();
        }
        logger.info("{} / {} = {}", a, b, a / b);
        return 0;
    }

    //@Recover
    //public void recover() {
    //    logger.error("被除数不能为0");
    //}

}
