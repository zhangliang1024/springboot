# 线上日志告警-推送钉钉\企业微信
> 基于logback的线上钉钉\企业微信日志告警推送


### 二、使用方式
> - 下载代码到本地，执行：mvn clean 
> - 在logback-spring.xml中增加配置
```xml
<!--从配置文件中取属性值-->
<springProperty scope="context" name="profile" source="spring.profiles.active"/>
<springProperty scope="context" name="appName" source="spring.application.name"/>

<appender name="DING-TALK" class="com.meeruu.dingtalk.DingTalkAppender">
    <!--输出格式-->
    <pattern>%d{yyyy-MM-dd HH:mm:ss.SSS} [%c%L] -%-5level [%thread] %msg%n</pattern>
    <!--钉钉机器人token-->
    <dingdingToken>0f52674155c92f649xxxxxxxxd17d4d3bb0a</dingdingToken>
    <appName>${appName}</appName>
    <env>${profile}</env>
</appender>

<appender name="WXCHART-TALK" class="com.open.WxAppender">
    <!--输出格式-->
    <pattern>%d{yyyy-MM-dd HH:mm:ss.SSS} [%c%L] -%-5level [%thread] %msg%n</pattern>
    <!--企业微信机器人token-->
    <wxKey>a53529c8-xxxxx-255462fb5f22</wxKey>
    <appName>${appName}</appName>
    <env>${profile}</env>
</appender>

 <!--建立一个默认的root的logger-->
<root level="info">
    <appender-ref ref="CONSOLE"/>
    <appender-ref ref="DING-TALK"/>
    <appender-ref ref="WXCHART-TALK"/>
</root>
```
> - 代码中打印error日志
```java
@SpringBootTest(classes = LogWxChartDingtalkApplicationTests.class)
class LogWxChartDingtalkApplicationTests {

    Logger logger = LoggerFactory.getLogger(getClass());

    @Test
    void contextLoads() {
        logger.error("test robot error log ");
    }

}
```
> - 推送结果 images目录

### 三、开源组件地址
[dingtalk-robot](https://github.com/gaoqinibm/dingtalk-robot)

[wx-robot](https://github.com/gaoqinibm/wx-robot)