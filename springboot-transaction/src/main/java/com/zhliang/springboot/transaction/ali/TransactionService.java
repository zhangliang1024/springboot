package com.zhliang.springboot.transaction.ali;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Component;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.DefaultTransactionDefinition;

/**
 * @Author: colin
 * @Date: 2019/8/1 15:25
 * @Description:  阿里API规范推荐 事务场景中，抛出异常被catch住后，如需回滚一定要手动回滚
 * @Version: V1.0
 */
@Slf4j
@Component
public class TransactionService {

    @Autowired
    private DataSourceTransactionManager transactionManager;

    // 手动回滚
    @Transactional
    public void save(){
        DefaultTransactionDefinition def = new DefaultTransactionDefinition();
        def.setName("tx-name");
        def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
        TransactionStatus status = transactionManager.getTransaction(def);

        try{
            //execute your business logic here
            //db operation
        }catch (Exception ex){
            transactionManager.rollback(status);
            throw ex;
        }
    }

}
