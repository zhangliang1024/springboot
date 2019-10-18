# springboot启动时初始化数据
> 在我们搭建SpringBoot项目时，会碰到在项目启动时初始化一些操作的需求，SpringBoot为我们提供了以下几种方案：
> - 实现 ApplicationRunner 或 CommandLineRunner 接口，重写run()方法
> - InitializingBean 接口
> - Spring 的事件机制

### ApplicationRunner CommandLineRunner
> - 默认情况下：CommandLineRunner比ApplicationRunner 先执行
> - CommandLineRunner的run参数 是原始的参数,没有经过任何处理
> - ApplicationRunner的run参数 是ApplicationArguments,是对原始参数的进一步封装
> - 这两个接口可以通过@Order注解或者实现Ordered接口来指定调用顺序,@Order()中值越小,优先级越高

### 参考文档
* [springboot系列文章之启动时初始化数据](http://ju.outofmemory.cn/entry/366312)
* [springboot项目启动成功后执行一段代码的两种方式](https://www.bbsmax.com/A/kmzLkBYWdG/)


### Reference Documentation
For further reference, please consider the following sections:

* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/2.1.9.RELEASE/maven-plugin/)

