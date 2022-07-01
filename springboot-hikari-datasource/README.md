# Getting Started


### 一、`Springboot`整合`Druid`
##### 1.依赖整合
```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-data-jdbc</artifactId>
</dependency>
<dependency>
    <groupId>mysql</groupId>
    <artifactId>mysql-connector-java</artifactId>
</dependency>
<dependency>
    <groupId>com.alibaba</groupId>
    <artifactId>druid-spring-boot-starter</artifactId>
    <version>1.1.10</version>
</dependency>
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-web</artifactId>
</dependency>
```
##### 2.`yml`配置
```yml
spring:
  application:
    name: springboot-datasource
  datasource:
    driver-class-name: com.mysql.cj.jdbc.Driver
    url: jdbc:mysql://localhost:3306/test?serverTimezone=GMT&useUnicode=true&characterEncoding=utf-8&useSSL=true
    username: root
    password: 123456
    type: com.alibaba.druid.pool.DruidDataSource
    druid:
      maxActive: 20   #最大活跃数
      initialSize: 10 #初始化数量
      maxWait: 60000  #最大连接等待超时时间
      poolPreparedStatements: true   #打开PSCache，并且指定每个连接PSCache的大小
      maxPoolPreparedStatementPerConnectionSize: 20
      connectionProperties: druid.stat.mergeSql=true;druid.stat.slowSqlMillis=5000   #通过connectionProperties属性来打开mergeSql功能；慢SQL记录
      minIdle: 1
      timeBetweenEvictionRunsMillis: 60000
      minEvictableIdleTimeMillis: 300000
      validationQuery: select 1 from dual
      testWhileIdle: true
      testOnBorrow: false
      testOnReturn: false
      filters: stat,wall,log4j  #配置监控统计拦截的filters，去掉后监控界面sql将无法统计,'wall'用于防火墙

```
##### 3.启动示例图
![Druid启动示例图](https://ae01.alicdn.com/kf/H685fcb650259453c8af36dcd625f1a4cI.png)

##### 4.`druid`连接池监控
> http://localhost:8080/druid/index.html
> - 默认没有用户名密码(可配)


### 二、`Springboot`整合`Hikari`

##### 1.依赖
> `springboot`在2.0之后，采用的默认数据库连接池就是`Hikari`,已经整合到`spring-boot-starter-data-jdbc`中
```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-data-jdbc</artifactId>
</dependency>
<dependency>
    <groupId>mysql</groupId>
    <artifactId>mysql-connector-java</artifactId>
</dependency>
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-web</artifactId>
</dependency>
```

##### 2.`yml`配置
```yml
spring:
  application:
    name: springboot-datasource
  datasource:
    type: com.zaxxer.hikari.HikariDataSource
    driver-class-name: com.mysql.cj.jdbc.Driver
    url: jdbc:mysql:///test?useUnicode=true&characterEncoding=utf-8&useSSL=false
    username: root
    password: 123456
    hikari:  #Hikari连接池配置
      minimum-idle: 4 #最小空闲连接数
      maximum-pool-size: 10 #最大连接数，默认10
      idle-timeout: 180000 #空闲连接最大存活时间，默认600000(10分钟)
      auto-commit: true #此属性控制从池返回的连接的默认自动提交行为，默认值:true
      pool-name: MyHikariCP #连接池名称
      max-lifetime: 1800000 #此属性控制池中连接的最长生命周期，值0表示无限生命周期，默认1800000即30分钟
      connection-timeout: 30000 # 数据库连接超时时间,默认30秒，即30000
      connection-test-query: SELECT 1
```

##### 3.启动示例图

![Hikari启动示例图](https://ae02.alicdn.com/kf/Hac5298d1a61844359a35d29191f8f403h.png)



### 三、参考文档
![SpringBoot系列十八：整合Hikari](https://blog.csdn.net/lizhiqiang1217/article/details/90573759)
![SpringBoot系列十七：整合Druid](https://blog.csdn.net/lizhiqiang1217/article/details/90573534)