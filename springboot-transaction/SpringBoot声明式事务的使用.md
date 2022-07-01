# SpringBoot声明式事务的简单使用
> Spring声明式事务的实现有两种方式：
- 第一种是基于xml配置方式
- 第二种是使用相关注解的方式

### Springboot默认使用第二种方式，所以Springboot可以直接使用注解即可



### Springboot事务注解：[@EnableTransactionManagement原理](https://www.cnblogs.com/moxiaotao/p/9435764.html)：
> @EnableXXX原理：注解上有个XXXRegistrar，或通过XXXSelector引入XXXRegistrar，XXXRegistrar实现了 
  ImportBeanDefinitionRegistrar的registerBeanDefinitions方法，给容器注册XXXCreator。这个Creator实现了后置处理器， 
  后置处理器在对象创建以后，包装对象，返回一个代理对象，代理对象执行方法利用拦截器链进行调用
  
```properties
* 1）、@EnableTransactionManagement
*        利用TransactionManagementConfigurationSelector给容器中会导入组件
*        导入两个组件
*        AutoProxyRegistrar
*        ProxyTransactionManagementConfiguration
* 2）、AutoProxyRegistrar：
*        给容器中注册一个 InfrastructureAdvisorAutoProxyCreator 组件；
*        利用后置处理器机制在对象创建以后，包装对象，返回一个代理对象（增强器），代理对象执行方法利用拦截器链进行调用；
* 3）、ProxyTransactionManagementConfiguration是个@Configuration
*        1、给容器中注册事务增强器transactionAdvisor；
*           1）、事务增强器要用事务注解的信息，AnnotationTransactionAttributeSource解析事务注解
*           2）、事务拦截器transactionInterceptor：
*              TransactionInterceptor；保存了事务属性信息，事务管理器；
*              TransactionInterceptor是一个 MethodInterceptor；
*              在目标方法执行的时候；
*                 执行拦截器链；
*                 只有事务拦截器：
*                    1）、先获取事务相关的属性
*                    2）、再获取PlatformTransactionManager，如果事先没有添加指定任何transactionmanger
*                       最终会从容器中按照类型获取一个PlatformTransactionManager；
*                    3）、执行目标方法
*                       如果异常，获取到事务管理器，利用事务管理回滚操作；
*                       如果正常，利用事务管理器，提交事务
```

### [Spring Boot的事务管理注解@EnableTransactionManagement的使用](https://blog.csdn.net/u010963948/article/details/79208328)
```properties
1. Sprinboot使用事务非常简单：
    a. 使用注解@EnableTransactionManagement开启事务支持
    b. 在访问数据库的Service方法上添加 注解@Transaction

2. Springboot事务管理器
    a. 不管是JPA还是JDBC 都实现自接口 PlatformTransactionManager
    b. 如果添加的是 spring-boot-starter-jdbc依赖，框架会默认注入 DataSourceTransactionManager实例
    c. 若果添加的是 spring-boot-starter-jpa依赖，框架会默认注入 JpaTransactionManager实例 

```

### [SpringBoot声明式事务的简单运用](https://blog.csdn.net/justry_deng/article/details/80828180)

