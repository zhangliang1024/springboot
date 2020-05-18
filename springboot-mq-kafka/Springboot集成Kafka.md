# Springboot集成 Kafka

### Reference Documentation
For further reference, please consider the following sections:

### SpringBoot 集成 KAFKA
* [Springboot集成Kafka](https://segmentfault.com/a/1190000014991209)

### Windows安装 KAFKA
* [WINDOWS上KAFKA运行环境安装](https://www.cnblogs.com/lnice/p/9668750.html)
* [Kafka在window上安装部署](https://www.cnblogs.com/coloz/p/10487679.html)


### KAFKA 原理
* [kafka极简入门(一)--简介](https://segmentfault.com/a/1190000021583205)
* [分布式消息Kafka的原理、基础架构、使用场景](https://segmentfault.com/a/1190000021273334)
* [Kafka幂等性原理及实现剖析](https://segmentfault.com/a/1190000021293704)


### KAFKA启动报错：
> 输入行太长
> 命令语法不正确

```properties
最后查到了微软的官方文档：
https://support.microsoft.com/zh-cn/help/830473/command-prompt-cmd-exe-command-line-string-limitation

使用较短的文件夹和文件的名称
减少文件夹树的深度
这时我反应过来，我的目录深度很大，干脆把kafka目录移到了最简单的D盘，别忘了修改config/server.properties里面的log.dirs到相应的位置。

原文链接：https://blog.csdn.net/qq28289047/article/details/96488010
```