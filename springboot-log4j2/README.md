# SpringBoot Log4j2日志使用



### 四、Log4j2的Policy触发策略与Strategy滚动策略
> Log4j2的Policy触发策略与Strategy滚动策略配置详解
```text
Policy：是用来控制日志文件何时(When)进行滚动的
Strategy: 是用来控制日志文件如何(How)进行滚动的
如果配置的是RollingFile或RollingRandomAccessFile，则必须配置一个Policy

1. Policy触发策略
     SizeBasedTriggeringPolicy
     CronTriggeringPolicy
     TimeBasedTriggeringPolicy
   
   SizeBasedTriggeringPolicy：基于日志文件大小的触发策略。单位：KB MB GB
     <SizeBasedTriggeringPolicy siez="10MB"/> 
    
   CronTriggeringPolicy: 基于Cron表达式的触发策略，很灵活
     <CronTriggeringPolicy schedule="0/5 * * * * ?"/> 
     
   TimeBasedTriggeringPolicy: 基于时间的触发策略。该策略主要是完成周期性的log文件封存工作。
     有两个参数：
     interval: Integer类型。指定两次封存动作之间的时间间隔。
               这个配置需要和filePattern结合使用，filePattern日期格式精确到哪一位，interval也精确到哪一个单位。
               filePattern中的配置文件命名规则：%d{yyyy-MM-dd HH-mm-ss}-%i，最小的时间粒度是ss，即秒。
               TimeBasedTriggerPolicy默认的size是1，就是每秒生产1个新文件。如果改成：%d{yyyy-MM-dd HH}则为每小时生产文件。
               
     modulate: boolean类型。说明是否对封存时间进行调制。若modelater为true，则封存时间将以0点为边界进行偏移计算。
               如：modulate为true,interval为4小时。则假设上次封存日志时间为03:00,下次封存日志的时间为04:00。之后的封存时间依次为：08:00 12:00
                 
2. Strategy滚动策略
     DefaultRolloverStrategy：默认滚动策略
       max: 保存日志文件最大个数，默认是7。大于此值会删除旧的日志文件
       <DefaultRolloverStrategy max="10"/>
     DirectWriteRolloverStrategy: 日志直接写入由文件模式表示的文件
```



### 五、Sprinboot集成Log4j2.yml配置
> pom配置
```xml
<dependencies>

  <!--spring-boot-starter-web依赖-->
  <dependency>
     <groupId>org.springframework.boot</groupId>
     <artifactId>spring-boot-starter-web</artifactId>
     <exclusions>
        <!-- 切换log4j2日志读取 -->
        <exclusion>
           <groupId>org.springframework.boot</groupId>
           <artifactId>spring-boot-starter-logging</artifactId>
        </exclusion>
     </exclusions>
  </dependency>

  <!--log4j2依赖-->
  <dependency>
     <groupId>org.springframework.boot</groupId>
     <artifactId>spring-boot-starter-log4j2</artifactId>
  </dependency>

  <!-- 支持识别yml配置 -->
  <dependency>
     <groupId>com.fasterxml.jackson.dataformat</groupId>
     <artifactId>jackson-dataformat-yaml</artifactId>
  </dependency>

</dependencies>
```
> log4j2.yml配置
```yml
Configuration:
 status: warn
 Properties: # 定义全局变量
   Property: # 缺省配置（用于开发环境）。其他环境需要在VM参数中指定，如下：
     - name: log.level.console
       value: debug
     - name: log.path
       value: /opt/logs
     - name: project.name
       value: test
 Appenders:
   Console:  # 输出到控制台
     name: CONSOLE
     target: SYSTEM_OUT
     ThresholdFilter:
       level: ${sys:log.level.console} # “sys:”表示：如果VM参数中没指定这个变量值，则使用本文件中定义的缺省全局变量值
       onMatch: ACCEPT
       onMismatch: DENY
     PatternLayout:
       pattern: "%d{yyyy-MM-dd HH:mm:ss}:%5p %10t [%15F:%3L] - %m%n"
   RollingFile: # 输出到文件，按照日期输出
     - name: ROLLING_FILE
       ignoreExceptions: false
       fileName: "${log.path}/${project.name}.log"
       filePattern: "${log.path}/${project.name}-%d{yyyy-MM-dd}.log"
       append: true
       PatternLayout:
         pattern: "%d{yyyy-MM-dd HH:mm:ss,SSS}:%5p %20t [%50F:%3L] - %m%n"
       Policies:
         TimeBasedTriggeringPolicy:
           modulate: true
           interval: 1
       DefaultRolloverStrategy:
         max: 100

 Loggers:
   Root:
     level: debug
     AppenderRef:
       - ref: CONSOLE
       - ref: ROLLING_FILE
```
### 六、日志脱敏方案
```text
1. 利用日志组件过滤特定的敏感数据。  
   缺点：对所有日志输出全部要正则匹配，耗时。
2. 在model层处理，重写get方法。在get方法中处理脱敏数据。在增加一个获取明文方法
   缺点：数据库写入和序列化传递都是密文
   
3. 处理方案：在model层定义方法，获取一个复制类。复制类里面的信息都是脱敏的，日志输出复制对象   
```
```java
//1. 定义接口
public interface NoSensitiveObj<T> {
    default T noSensitiveObj(){
        return (T) this;
    }
}
//2. 实现类 如果这个类没有敏感信息，只实现 NoSensitiveObj。不需要实现里面的方法
public class User implements NoSensitiveObj<User>{

    private String name;
    
    private String phone;
    
    private String email;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public User noSensitiveObj() {
            User t;
            t=new User();
            t.setEmail(SensitiveInfoUtils.email(email));
            t.setName(SensitiveInfoUtils.chineseName(name));
            t.setPhone(SensitiveInfoUtils.mobilePhone(phone));
        return t;
    }
    
}
//3. 工具类
public class SensitiveInfoUtils {

    /**
     * [中文姓名] 只显示第一个汉字，其他隐藏为2个星号<例子：李**>
     */
    public static String chineseName(final String fullName) {
        if (StringUtils.isBlank(fullName)) {
            return "";
        }
        final String name = StringUtils.left(fullName, 1);
        return StringUtils.rightPad(name, StringUtils.length(fullName), "*");
    }

    /**
     * [中文姓名] 只显示第一个汉字，其他隐藏为2个星号<例子：李**>
     */
    public static String chineseName(final String familyName, final String givenName) {
        if (StringUtils.isBlank(familyName) || StringUtils.isBlank(givenName)) {
            return "";
        }
        return chineseName(familyName + givenName);
    }

    /**
     * [身份证号] 显示最后四位，其他隐藏。共计18位或者15位。<例子：*************5762>
     */
    public static String idCardNum(final String id) {
        if (StringUtils.isBlank(id)) {
            return "";
        }

        return StringUtils.left(id, 3).concat(StringUtils
                .removeStart(StringUtils.leftPad(StringUtils.right(id, 3), StringUtils.length(id), "*"), "***"));
    }

    /**
     * [固定电话] 后四位，其他隐藏<例子：****1234>
     */
    public static String fixedPhone(final String num) {
        if (StringUtils.isBlank(num)) {
            return "";
        }
        return StringUtils.leftPad(StringUtils.right(num, 4), StringUtils.length(num), "*");
    }

    /**
     * [手机号码] 前三位，后四位，其他隐藏<例子:138******1234>
     */
    public static String mobilePhone(final String num) {
        if (StringUtils.isBlank(num)) {
            return "";
        }
        return StringUtils.left(num, 2).concat(StringUtils
                .removeStart(StringUtils.leftPad(StringUtils.right(num, 2), StringUtils.length(num), "*"), "***"));

    }

    /**
     * [地址] 只显示到地区，不显示详细地址；我们要对个人信息增强保护<例子：北京市海淀区****>
     *
     * @param sensitiveSize
     *            敏感信息长度
     */
    public static String address(final String address, final int sensitiveSize) {
        if (StringUtils.isBlank(address)) {
            return "";
        }
        final int length = StringUtils.length(address);
        return StringUtils.rightPad(StringUtils.left(address, length - sensitiveSize), length, "*");
    }

    /**
     * [电子邮箱] 邮箱前缀仅显示第一个字母，前缀其他隐藏，用星号代替，@及后面的地址显示<例子:g**@163.com>
     */
    public static String email(final String email) {
        if (StringUtils.isBlank(email)) {
            return "";
        }
        final int index = StringUtils.indexOf(email, "@");
        if (index <= 1) {
            return email;
        } else {
            return StringUtils.rightPad(StringUtils.left(email, 1), index, "*")
                    .concat(StringUtils.mid(email, index, StringUtils.length(email)));
        }
    }

    /**
     * [银行卡号] 前六位，后四位，其他用星号隐藏每位1个星号<例子:6222600**********1234>
     */
    public static String bankCard(final String cardNum) {
        if (StringUtils.isBlank(cardNum)) {
            return "";
        }
        return StringUtils.left(cardNum, 6).concat(StringUtils.removeStart(
                StringUtils.leftPad(StringUtils.right(cardNum, 4), StringUtils.length(cardNum), "*"), "******"));
    }

    /**
     * [公司开户银行联号] 公司开户银行联行号,显示前两位，其他用星号隐藏，每位1个星号<例子:12********>
     */
    public static String cnapsCode(final String code) {
        if (StringUtils.isBlank(code)) {
            return "";
        }
        return StringUtils.rightPad(StringUtils.left(code, 2), StringUtils.length(code), "*");
    }

}
```

### 七、参考文档
[Springboot整合log4j2日志全解](https://www.cnblogs.com/keeya/p/10101547.html)

[史上最简单的log4j2.yml整合教程](https://www.wolzq.com/%E5%8F%B2%E4%B8%8A%E6%9C%80%E7%AE%80%E5%8D%95%E7%9A%84log4j2.yml%E6%95%B4%E5%90%88%E6%95%99%E7%A8%8B/)

[springboot使用yml方式添加log4j2日志文件](https://www.pianshen.com/article/4132276193/)

[JAVA 日志的数据脱敏](https://www.cnblogs.com/z-test/p/9488367.html)

[一起进阶一起拿高工资！Java开发进阶-log4j2日志脱敏原理分析](https://blog.csdn.net/LNF568611/article/details/113083401)

[Log4j2日志脱敏](https://blog.csdn.net/DavinQi/article/details/108205622)
[log4j2 日志脱敏/特殊处理](https://blog.csdn.net/saytime/article/details/87282510)
[用log4j2.yml中的rewrite进行日志脱敏](https://vlambda.com/wz_14qUI3GkZ0.html)


[SpringBoot+log4j2.xml读取application.yml属性值](https://blog.csdn.net/justlpf/article/details/85616344)
[springboot log4j2.xml读取application.yml中的属性值](https://blog.csdn.net/ajdxwz/article/details/92101822)
[]()
[]()
[]()
[]()
[springboot集成log4j2 + logstash 异步输出日志](https://www.cnblogs.com/yuarvin/p/11801073.html)

[Log4j2的Policy触发策略与Strategy滚动策略配置详解](https://thinkwon.blog.csdn.net/article/details/101628222)
[]()
[]()
[]()
[]()
[]()