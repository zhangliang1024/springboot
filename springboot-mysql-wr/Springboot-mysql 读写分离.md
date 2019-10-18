# SpringBoot Mysql 读写分离
> Mysql读写分离就是解决一条sql该选择哪个库去执行。一般有两种实现方式：
> - 依靠中间件(如：MyCat) 程序连接到中间件，中间件去判断到底在哪个库执行
> - 应用程序自己去做分离。主要利用Spring提供的路由数据源 及AOP

### 程序自己实现的不足
> 无法动态增加数据节点，因为所有的配置都写在配置文件中 

### 参考文档

* [SpringBoot+MyBatis+MySQL读写分离](https://www.cnblogs.com/cjsblog/p/9712457.html)

优秀文档：
* [mybatis通过自定义插件方式实现主从库读写分离](https://blog.csdn.net/LOVELONG8808/article/details/86625363)
* [springboot数据库读写分离AOP实现（二）](https://blog.csdn.net/LOVELONG8808/article/details/86635747)
* [GitHub] (https://github.com/1181888200?tab=repositories)
* [Blog] (https://blog.csdn.net/lovelong8808/article/category/7213098)

* [崛起于Springboot2.0.X之Mysql读写分离（6）](https://my.oschina.net/mdxlcj/blog/1835656)

### Guides


