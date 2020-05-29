# Strategy 策略模式

### 二、埋点
[micrometer埋点（Spring boot 2.X metrics）](https://www.jianshu.com/p/082571330190)

### 三、Strategy 策略模式
> 策略+工厂 优化if-else

[策略模式、工厂模式代替If-else](https://blog.csdn.net/AssassinX99/article/details/102736252)
[如何优雅的替换掉代码中的ifelse](https://blog.csdn.net/iteye_19045/article/details/103300731)
[在Springboot中 如何干掉 if-else](https://www.jianshu.com/p/1e70918d4840)


### 四、诡异的事情
```markdown
1. 在通过@ConfigurationProperteis实现配置自动注入过程中发现，application.yml中的属性无法注入
2. 同样的配置，修改yml为porperties配置文件后就可以注入了
3. 后期优化：通过@PropertySource注解 指定注入使用配置文件

```