package com.zhliang.springbootmysqlwr.aspect;

import com.zhliang.springbootmysqlwr.key.DBContextHolder;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * @Author: colin
 * @Date: 2019/10/18 11:33
 * @Description:
 * @Version: V1.0
 */
@Aspect
@Component
public class DataSourceAspect {

    @Pointcut("!@annotation(com.zhliang.springbootmysqlwr.annotation.Master) " +
            "&& (execution(* com.zhliang.springbootmysqlwr.service..*.select*(..)) " +
            "|| execution(* com.zhliang.springbootmysqlwr.service..*.get*(..)))")
    public void readPointcut() {

    }

    @Pointcut("@annotation(com.zhliang.springbootmysqlwr.annotation.Master) " +
            "|| execution(* com.zhliang.springbootmysqlwr.service..*.insert*(..)) " +
            "|| execution(* com.zhliang.springbootmysqlwr.service..*.add*(..)) " +
            "|| execution(* com.zhliang.springbootmysqlwr.service..*.update*(..)) " +
            "|| execution(* com.zhliang.springbootmysqlwr.service..*.edit*(..)) " +
            "|| execution(* com.zhliang.springbootmysqlwr.service..*.delete*(..)) " +
            "|| execution(* com.zhliang.springbootmysqlwr.service..*.remove*(..))")
    public void writePointcut() {

    }

    @Before("readPointcut()")
    public void read() {
        DBContextHolder.slave();
    }

    @Before("writePointcut()")
    public void write() {
        DBContextHolder.master();
    }


    /**
     * 另一种写法：if...else...  判断哪些需要读从数据库，其余的走主数据库
     */
//    @Before("execution(* com.zhliang.springbootmysqlwr.service.impl.*.*(..))")
//    public void before(JoinPoint jp) {
//        String methodName = jp.getSignature().getName();
//
//        if (StringUtils.startsWithAny(methodName, "get", "select", "find")) {
//            DBContextHolder.slave();
//        }else {
//            DBContextHolder.master();
//        }
//    }

}
