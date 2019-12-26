# Springboot中 使用JSON序列化的比较

> [Spring Boot Json 之 Jackjson Fastjson](https://www.cnblogs.com/fishpro/p/spring-boot-study-jackjson.html)
> []()
> [SpringBoot中使用Jackson导致Long型数据精度丢失问题](https://www.cnblogs.com/hahahehexixihoho/p/10214156.html)







### JSON介绍
> - JSON的全称是”JavaScript Object Notation”，意思是JavaScript对象表示法，它是一种基于文本，独立于语言的轻量级数据交换格式。
> - JSON有两种表示结构，对象和数组。
>   - 对象结构：以"{"开始,以"}"结束。{ key1:value1,key2:value2 }
>   - 数组结构：以"["开始,以"]"结束。[{key1:value1,key2:value2},{key3:value3,key4:value4}]

### FastJson
```xml
<!-- https://mvnrepository.com/artifact/com.alibaba/fastjson -->
<dependency>
    <groupId>com.alibaba</groupId>
    <artifactId>fastjson</artifactId>
    <version>1.2.58</version>
</dependency>
```

### Jackson
```xml
<!-- https://mvnrepository.com/artifact/org.codehaus.jackson/jackson-core-asl -->
<dependency>
    <groupId>org.codehaus.jackson</groupId>
    <artifactId>jackson-core-asl</artifactId>
    <version>1.9.13</version>
</dependency>

```
### FastJson 与  Jackson的性能对比
> - 对象序列化为字符串：
>   - 在少量数据时,Jackson性能比FastJson要好。当数据量大时,FastJson性能要好于Jackson。
> - 字符串反序列化为对象：
>   - 在少量数据时,Jackson性能比FastJson要好。当数据量大时,FastJson性能要好于Jackson。
>   - 除在map转换有些不同


