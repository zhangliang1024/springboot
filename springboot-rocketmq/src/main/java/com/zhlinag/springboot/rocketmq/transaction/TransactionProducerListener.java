package com.zhlinag.springboot.rocketmq.transaction;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.RocketMQTransactionListener;
import org.apache.rocketmq.spring.core.RocketMQLocalTransactionListener;
import org.apache.rocketmq.spring.core.RocketMQLocalTransactionState;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

/**
 * ClassName: TransactionConsumer
 * Function:
 * Date: 2022年04月19 18:01:30
 *
 * @author 张良 E-mail:zhangliang01@jingyougroup.com
 * @version V1.0.0
 *
 * 1. 生成者实现 RocketMQLocalTransactionListener接口，重写执行本地事务的方法和检查本地事务方法
 * 2. RocketMQTransactionListener注解 是一个生产端的消息监听器，徐娅萍配置监听事务消息的生产者组
 */
@Slf4j
//@Component
//@RocketMQTransactionListener
public class TransactionProducerListener implements RocketMQLocalTransactionListener {


    /**
     * 1. 每次推送消息会执行 executeLocalTransaction方法，首先会发送半消息，到这里的时候执行具体本地业务
     *    执行成功后。手动返回：RocketMQLocalTransactionState.COMMIT 状态
     * 2. 这里是保证本地事务执行成功，如果本地事务执行失败则可以返回：Rollback 进行消息回滚。
     *    此时消息只保存在broker中，并没有发送到topic中，broker会根据本地返回状态来决定消息的处理方式
     */
    @Override
    public RocketMQLocalTransactionState executeLocalTransaction(Message message, Object o) {
        log.info("开始执行本地事务方法");
        Integer num = (Integer) o;
        if(num % 2 == 0){
            return RocketMQLocalTransactionState.COMMIT;
        }
        return RocketMQLocalTransactionState.ROLLBACK;
    }

    /**
     * MQ 回调方法，确认消息执行逻辑
     */
    @Override
    public RocketMQLocalTransactionState checkLocalTransaction(Message message) {
        log.info("开始执行回查方法");
        // 判断具体业务逻辑，来决定是否回滚还是提交
        boolean flag = false;
        if ( flag ) {
            log.info("回滚半消息");
            return RocketMQLocalTransactionState.ROLLBACK;
        }
        log.info("提交半消息");
        return RocketMQLocalTransactionState.COMMIT;
    }
}
