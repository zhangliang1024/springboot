## Springboot-JackJson使用学习

### Springboot默认的JSON框架
> 默认的JSON框架的jackson; Jackson默认具有比较高的：序列化和反序列化效率; 

#### JackJson依赖
```xml
<dependency> 
    <groupId>com.fasterxml.jackson.core</groupId> 
        <artifactId>jackson-databind</artifactId> 
    <version>2.5.3</version>
</dependency> 
```

#### 常用注解说明
```properties
1. @JsonProperty: 此注解用于属性上。把该属性的名称序列化为另一个名称，对属性名重命名。
2. @JsonIgnore: 此注解用于属性或方法上，最好是属性上。用于完全忽略被注解的字段和方法对应的属性上。
3. @JsonIgnoreProperties: 此注解是类注解,作用是json序列化时将java bean中的一些属性忽略掉，序列化和反序列化都受影响。\
4. @JsonFormat: 此注解用于属性或方法上,可以方便的把Date类型直接转换为我们想要的模式。\
   如：@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss") 
5. @JsonSerialize: 此注解用于属性或者getter方法上,用于在序列化时嵌入我们自定义的序列化方式。
6. @JsonDeserialize: 此注解用于属性或者setter方法上,用于在反序列化的时可以嵌入我们自定义的反序列化方式。
7. @JsonInclude: 属性值为null的不参与序列化
   如：@JsonInclude(JsonInclude.Include.NON_NULL)


```

### 常用方法
1. objectMapper.writeValueAsString();
```java
User user = new User();
user.setId(122000083049775104L);
user.setUsername("zhangsan");
user.setNickname("wo");
user.setCreateTime(new Date());
ObjectMapper mapper = new ObjectMapper();
String response = mapper.writeValueAsString(user);

```

2. 对时间格式的定义
> Jackson有自己的默认时间格式：timestamps形式, 其结果如：1375429228382
```java
 //设置时间格式无效, 时间格式会是：["createTime": "2019-08-24T02:35:32.052+0000"]
 objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
 //使用自定义时间格式
  objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
```

3. objectMapper.readValue();
```java
 String request = "{\"id\":\"122000083049775104\",\"createTime\":1566619753,\"name\":\"zhangsan\"," +
                "\"nick\":\"wo\"}";
ObjectMapper mapper = new ObjectMapper();
User user = mapper.readValue(request, User.class);
``` 

### 参考文档
> [SpringBoot中使用Jackson导致Long型数据精度丢失问题](https://www.cnblogs.com/hahahehexixihoho/p/10214156.html)
















