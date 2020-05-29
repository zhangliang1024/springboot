# SpringBoot 使用自定义两级缓存

### 三、参考文档
★★★ `springboot-two-cache` 实现的此文档
[SpringBoot用200行代码完成一个一二级分布式缓存](https://www.jianshu.com/p/f14750f1f80a)
[springboot中使用自定义两级缓存](https://yq.aliyun.com/articles/620182?spm=a2c4e.11153940.0.0.710940c0tyB2lN)
[Java Web现代化开发：Spring Boot+Mybatis+Redis二级缓存](https://yq.aliyun.com/articles/688465)



★★★
[为监控而生的多级缓存框架 layering-cache](https://my.oschina.net/xiaolyuh/blog/2245782)
[layering-cache GitHub地址](https://github.com/xiaolyuh/layering-cache)

> 最近好像很多类似于这样的开源组件，例如阿里的 jetcache, autoload_cache等等



★★★ `Redis` 和`Ehcache` 做一二级缓存, `zookeeper` 分布式锁更新`redis ` 缓存
[Spring Boot 使用Redis和Ehcache做拥有二级缓存的系统](https://blog.csdn.net/u014104286/article/details/79149578)



##### 其它
[史上最全的Spring Boot Cache使用与整合](https://blog.csdn.net/qq_32448349/article/details/101696892)
[springboot使用cache缓存](https://www.jianshu.com/p/e9b40acb2993)
> 自定义缓存key生成策略
[基于Cache注解模式，管理Redis缓存](https://yq.aliyun.com/articles/715047)


##### `Springboot Cache` 的使用
[springboot使用cache缓存](https://www.jianshu.com/p/e9b40acb2993)
[springboot使用ehcache缓存](https://www.jianshu.com/p/9e4eb5e5bc1b)
[springboot使用redis缓存](https://www.jianshu.com/p/cde7a812743c)


##### `Springboot Mybatis` 缓存

[SpringBoot集成Mybatis以及对Mybatis一级二级缓存的理解](https://www.jianshu.com/p/556b70e8ec9f)




### 四、Guava Cache 使用
★★★
[Guava Cache用法介绍](https://segmentfault.com/a/1190000011105644)
★★★
[GuavaCache内存缓存](https://baijiahao.baidu.com/s?id=1665363055480244255&wfr=spider&for=pc)
★★★
[Guava Cache在实际项目中的应用](https://www.cnblogs.com/csonezp/p/10011031.html)


### Tomat 防重限流开源组件
> 这是一个专门为SpringBoot项目设计的幂等组件,让天下没有难写的防重代码。 基于控制时间两种防重策略。既可以做表单重复提交校验，也可以做限流。基于Redis实现
[Tomat官网](https://tomato.springlearn.cn/)