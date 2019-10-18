# Springboot ConfigurationProperties
> 简介
> - @ConfigurationProperties注解用来把配置文件中的信息读取出来，自动封装成实体类;
> - 如果发现@ConfigurationPropertie不生效，有可能是项目的目录结构问题;可以通过@EnableConfigurationProperties(ConnectionSettings.class)来明确指定需要用哪个实体类来装载配置信息

### 示例一
```yaml
person:
  name: zhangsan
  age: 20
  sex: male
```
```java
/**
 * 将配置文件中配置的每一个属性的值，映射到这个组件中
 * @ConfigurationProperties：告诉SpringBoot将本类中的所有属性和配置文件中相关的配置进行绑定；
 *      prefix = "person"：配置文件中哪个下面的所有属性进行一一映射
 *
 * 只有这个组件是容器中的组件，才能容器提供的@ConfigurationProperties功能；
 * 所以配置@Component注解
 */
@Component
@ConfigurationProperties(prefix = "person")
public class Person {
   
    private String name;
    private int age;
    private String sex;
    
 }
```

### 示例二
```yaml
person:
  name: zhangsan
  age: 20
  sex: male
```

```java
public class Person {
   
    private String name;
    private int age;
    private String sex;
    
 }
 
 /**
  * 除了方式一,还可以直接把@ConfigurationProperties注解定义在@Bean上。
  * 这里Person实体就不用@Component和@ConfigurationProperties注解了
  */
 @Configuration
 public class PersonConfig{
    
    @Bean
    @ConfigurationProperties(prefix = "cloud")
    public Person person(){
        return new Person();
    }
 }
```
### 直接使用
```java
public class Controller{
    
    //直接注入使用
    @Autowired
    private Person person;
    //...
}
```
### 参考文档
> - [在Spring Boot中使用 @ConfigurationProperties 注解](https://blog.csdn.net/lafengwnagzi/article/details/55050677)
> - [spring boot 配置文件properties](https://blog.csdn.net/yu0_zhang0/article/details/83745056)
> - [SpringBoot的properties和yml两种配置方式, 配置注入参数, 以及配置文件读取失效的问题](https://blog.csdn.net/zzzgd_666/article/details/80316174)

