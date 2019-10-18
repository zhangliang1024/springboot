### 

#### FastJson注解详解
- @JSONField
```properties
1. 作用在属性上 : @JSONField(name = "key") key可以自定义属性输出的名称;但反序列化时,值不会赋值到属性上;
    @JSONField(name = "username")
    private String name;
2.  @JSONField(format = "yyyy-MM-dd HH:mm:ss") 用在Date类型的字段用来格式化时间
3. @JSONField(serialize = false)  在序列化的时候就不包含这个字段了
   @JSONField(deserialize = false)  在反序列化的时候就不包含这个字段了
   注意：当字段为final修饰时，放在属性上是不起作用的，这时候应该放在getter或setter方法上
4. fastjosn默认的序列化规则：当字段的值是null时，是不会序列化这个字段的
   @JSONField(serialzeFeatures = SerializerFeature.WriteMapNullValue) 当value值为null时，依然会把值序列化出来

```