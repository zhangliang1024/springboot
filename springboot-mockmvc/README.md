# SpringBoot MockMVC进行模块集成测试

## 概述
> - 对模块进行集成测试时，希望能够通过输入URL对Controller进行测试。如果通过启动服务器，建立http client连接进行测试
会使得测试变的很麻烦，比如：启动速度慢、测试验证不方便、依赖网络环境。
> - 引入MockMvc可以使得测试变的很简答

### 用到的注解
```properties
1.@RunWith(SpringJunit4ClassRunner.class) : 表示使用Spring Test组件进行单元测试 

2.@WebAppConfiguration : 使用这个Annotaion会在跑单元测试时真实的启动一个web服务,然后开始调用Controller的Restful API,待单元测试跑完之后再将web服务停掉.

3.@ContextConfiguration({ "classpath:spring-mvc.xml", "classpath:applicationContext.xml"}) : 指定Bean的配置文件信息,可以有多种方式,这个例子使用的文件路径形式,如果有多个配置文件,可以将括号中的信息配置为一个字符串数组来表示.

# SpringBoot使用的注解

@RunWith(SpringRunner.class) ： 表示使用Spring Test组件进行单元测试 

@SpringBootTest

```
### Reference Documentation


* [使用MockMvc测试Controller](https://blog.csdn.net/qq_37653556/article/details/82831462)

