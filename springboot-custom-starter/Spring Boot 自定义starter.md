# Spring Boot 自定义starter

> [Spring Boot 自定义starter](https://www.jianshu.com/p/74ffbafe8bef)
> [史上最全的Spring-Boot-Starter开发手册](https://blog.csdn.net/dupeng0811/article/details/89876444)
> [Spring-boot-starter大力出奇迹](https://www.cnblogs.com/hafiz/p/9163095.html)
> [SpringBoot各类扩展点详解](https://www.cnblogs.com/hafiz/p/9155017.html)


### JavaSPI 实际上是“基于接口的编程＋策略模式＋配置文件”组合实现的动态加载机制。
> [史上最全的Spring-Boot-Starter开发手册](https://blog.csdn.net/dupeng0811/article/details/89876444)

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

