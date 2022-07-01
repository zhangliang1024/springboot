# FastJson 过滤/保留 NULL值问题



### 示例代码
- 方式一 局部解决
```java
String str = "{userId:1,nickName:null}";
str = str.replaceAll("null","''");
System.out.println(str);
JSONObject jsonObject = JSONObject.parseObject(str);
System.out.println(jsonObject);

```
- 方式二 全局配置
```java
/**
 * 替换springboot默认的jackjson解析工具，使用fastjson进行解析json对象到前台
 * 1. fastJson默认在进行序列化时，会过滤掉值null的属性。但实际项目上是需要为null的属性
 * 2. 通过设置HttpMessageConverters来统一配置，在使用fastJosn进行序列化时对各种为空情况的转换
 * 3. 在拿到josn(是json字符串，不是字符串)字符串时，使用json.replaceAll("null","''") 把null转为空值。则序列化不会丢失属性
 * FastJson 过滤/保留 NULL值问题：
 *
 * @return HttpMessageConverters
 */
@Bean
public HttpMessageConverters fastJsonHttpMessageConverters() {
    // 1.需要定义一个convert转换消息的对象;
    FastJsonHttpMessageConverter fastJsonHttpMessageConverter = new FastJsonHttpMessageConverter();
    // 2:添加fastJson的配置信息;
    FastJsonConfig fastJsonConfig = new FastJsonConfig();
    fastJsonConfig.setSerializerFeatures(SerializerFeature.PrettyFormat,
            SerializerFeature.DisableCircularReferenceDetect, SerializerFeature.WriteMapNullValue,
            SerializerFeature.WriteNullStringAsEmpty, SerializerFeature.WriteNullListAsEmpty);
    // 3处理中文乱码问题
    List<MediaType> fastMediaTypes = new ArrayList<>();
    fastMediaTypes.add(MediaType.APPLICATION_JSON_UTF8);
    // 4.在convert中添加配置信息.
    fastJsonHttpMessageConverter.setSupportedMediaTypes(fastMediaTypes);
    fastJsonHttpMessageConverter.setFastJsonConfig(fastJsonConfig);
    HttpMessageConverter<?> converter = fastJsonHttpMessageConverter;
    return new HttpMessageConverters(converter);

}
```


### 参考文档

★★★

[FastJson 过滤/保留 NULL值问题](https://blog.csdn.net/glei20/article/details/90896806)

[fastjson转换为json字符串时丢失null值字段](https://blog.csdn.net/qq1170993239/article/details/105378846)

[json过滤掉值为null的问题](https://blog.csdn.net/jialanshun/article/details/79078750)

[ str.replaceAll("null","''")](https://q.cnblogs.com/q/116347/)