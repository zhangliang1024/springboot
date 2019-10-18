package com.zhliang.springboot.transaction.ali;

import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Component;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

/**
 * @Author: colin
 * @Date: 2019/8/1 15:34
 * @Description: SpringBoot声明式事务的简单运用：https://blog.csdn.net/justry_deng/article/details/80828180
 * @Version: V1.0
 */
@Slf4j
@Component
public class SynchronizeDataService {

    /**
     * 自动装配  数据源事务管理器
     * 注： 前提是容器中有可装配的事务管理器，如果没有需要注入
     */
    private final DataSourceTransactionManager transactionManager;


    public SynchronizeDataService (DataSourceTransactionManager transactionManager){
        this.transactionManager = transactionManager;
    }

    public void synchronizeData(){
        //配置事务策略
        DefaultTransactionDefinition def = new DefaultTransactionDefinition();
        def.setName("planOne-transaction");
        def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
        //设置状态点
        TransactionStatus status = transactionManager.getTransaction(def);

        try{
            //execute your business logic here
            //db operation

            //手动提交
            transactionManager.commit(status);
        }catch (Exception ex){
            //手动回滚
            transactionManager.rollback(status);
            throw ex;
        }

    }
}
