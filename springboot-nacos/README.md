# `Springboot`整合`Nacos`


### 一、`Nacos`介绍
- 服务发现和服务健康监测
- 动态配置服务
- 动态DNS服务
- 服务及其元数据管理


### 二、整合
##### 1.依赖
```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-web</artifactId>
</dependency>
<dependency>
    <groupId>com.alibaba.boot</groupId>
    <artifactId>nacos-config-spring-boot-starter</artifactId>
    <version>0.2.1</version>
</dependency>
```
##### 2.配置
```properties
#配置nacos地址
nacos.config.server-addr=127.0.0.1:8848
```
##### 3.启动类
> `@NacosPropertySource`来标识配置文件和所属组
```java
@RestController
@NacosPropertySource(dataId = "thread-pool.properties",groupId = "DEFAULT_GROUP",autoRefreshed = true)
@SpringBootApplication
public class SpringbootNacosApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootNacosApplication.class, args);
    }

    @NacosValue(value = "${server.port:8081}", autoRefreshed = true)
    private String serverPort;

    @GetMapping("port")
    public String get() {
        return serverPort;
    }
}
```


### 三、参考文档
![SpringBoot使用Nacos作为配置中心服务和服务注册中心](https://blog.csdn.net/zjcjava/article/details/88316190)