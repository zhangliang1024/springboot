# Spring Boot 自定义starter

> [Spring Boot 自定义starter](https://www.jianshu.com/p/74ffbafe8bef)
> [史上最全的Spring-Boot-Starter开发手册](https://blog.csdn.net/dupeng0811/article/details/89876444)
> [Spring-boot-starter大力出奇迹](https://www.cnblogs.com/hafiz/p/9163095.html)
> [SpringBoot各类扩展点详解](https://www.cnblogs.com/hafiz/p/9155017.html)


### JavaSPI 实际上是“基于接口的编程＋策略模式＋配置文件”组合实现的动态加载机制。
> [史上最全的Spring-Boot-Starter开发手册](https://blog.csdn.net/dupeng0811/article/details/89876444)
> [了解一下Java SPI的原理](https://www.jianshu.com/p/ae00c539c1af)

##### SPI 优缺点

优点
```properties
解耦：最大的优点也就是解耦了，通过SPI可以使第三方服务模块的逻辑与业务代码相分离，而不耦合在一起。应用程序可以根据实际业务进行扩展。
```

缺点
```properties
1. 需要遍历所有的实现，并实例化，然后我们在循环中才能找到我们需要的实现。
2. 配置文件中只是简单的列出了所有的扩展实现，而没有给他们命名。导致在程序中很难去准确的引用它们。
3. 扩展如果依赖其他的扩展，做不到自动注入和装配
4. 不提供类似于Spring的IOC和AOP功能
5. 扩展很难和其他的框架集成，比如扩展里面依赖了一个Spring bean，原生的Java SPI不支持

链接：https://www.jianshu.com/p/ae00c539c1af

```

### @EnableXXX 注解 注入stater
* [spring boot自定义@EnableXXX注解](https://blog.csdn.net/mapleleafforest/article/details/87903718)
* [通过@Enable*注解触发Spring Boot配置](https://www.jianshu.com/p/c67b29152180)
* [自定义spring boot-starter,实现自动配置,自定义注解扫描注入](https://blog.csdn.net/wolfishness/article/details/101170774)
* []()
* [自定义spring boot-starter,实现自动配置,自定义注解扫描注入(类似dubbo-starter)](https://blog.csdn.net/u010928589/article/details/90293703)
* []()
* [Spring-Boot之@Enable*注解的工作原理](https://www.jianshu.com/p/3da069bd865c)
* [自定义Spring-Boot @Enable注解](https://www.cnblogs.com/zhya/p/9847972.html)
* []()
* []()


```properties
官方命名规则
    前缀：spring-boot-starter-
    模式：spring-boot-starter-模块名
    举例：spring-boot-starter-web、spring-boot-starter-jdbc
自定义命名规则
    后缀：-spring-boot-starter
    模式：模块-spring-boot-starter
    举例：hello-spring-boot-starter

作者：吟风者
链接：https://www.jianshu.com/p/74ffbafe8bef
来源：简书

```

