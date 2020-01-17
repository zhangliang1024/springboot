# Springboot rpc 自定义starter

### 手写RPC框架 ，并自定义stater 实现自动扫描注入
For further reference, please consider the following sections:

* [rpc-starter-github](https://github.com/cao2068959/rpc-starter)
* [自定义-rpc-starter(类似dubbo-starter)](https://blog.csdn.net/u010928589/article/details/90293703)
* [手写实现RPC 框架](https://blog.csdn.net/u010928589/article/details/90291911)

### maven两种方式跳过编译test
> 1、mvn clean install -DskipTests  
> 2、mvn clean install -Dmaven.test.skip=true

### git 撤销commit
> 1. git reset --soft HEAD~1  撤回最近一次的commit(撤销commit，不撤销git add)
> 2. git reset --mixed HEAD~1 撤回最近一次的commit(撤销commit，撤销git add)
> 3. git reset --hard HEAD~1  撤回最近一次的commit(撤销commit，撤销git add,还原改动的代码)