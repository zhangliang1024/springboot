package com.zhliang.springboot.produce.task;
import java.util.Date;
import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.zhliang.springboot.produce.constant.Constants;
import com.zhliang.springboot.produce.entity.BrokerMessageLog;
import com.zhliang.springboot.produce.entity.Order;
import com.zhliang.springboot.produce.mapper.BrokerMessageLogMapper;
import com.zhliang.springboot.produce.produce.RabbitOrderSender;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @Author: colin
 * @Date: 2019/9/24 08:47
 * @Description:
 * @Version: V1.0
 */
@Slf4j
@Component
public class RetryMessageTasker {

    @Autowired
    private RabbitOrderSender rabbitOrderSender;

    @Autowired
    private BrokerMessageLogMapper brokerMessageLogMapper;


    @Scheduled(initialDelay = 5000,fixedRate = 10000)
    public void reSend(){
        log.info("定时任务开始...");
        List<BrokerMessageLog> list = brokerMessageLogMapper.query4StatusAndTimeoutMessage();
        list.forEach(brokerMessageLog -> {
            //超过3次 记录发送失败
            if(brokerMessageLog.getTryCount() >=3){
                brokerMessageLogMapper.changeBrokerMessageLogStatus(brokerMessageLog.getMessageId(), Constants.ORDER_SEND_FAILURE,
                        new Date());
            }else {
                //记录发送次数  重新发送
                brokerMessageLogMapper.update4ReSend(brokerMessageLog.getMessageId(), new Date());
                Order order = JSONObject.parseObject(brokerMessageLog.getMessage(), Order.class);
                //发送消息
                rabbitOrderSender.sendOrder(order);
            }
        });

    }


}
