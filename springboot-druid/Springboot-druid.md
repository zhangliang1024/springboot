# Getting Started

### Druid参数配置说明
```properties
1. spring.datasource.druid.max-active       最大连接数
2. spring.datasource.druid.initial-size     初始化大小
3. spring.datasource.druid.min-idle         最小连接数
4. spring.datasource.druid.max-wait         获取连接等待超时时间
5. spring.datasource.druid.time-between-eviction-runs-millis   间隔多久才进行一次检测，检测需要关闭的空闲连接，单位是毫秒
6. spring.datasource.druid.min-evictable-idle-time-millis      一个连接在池中最小生存的时间，单位是毫秒
7. spring.datasource.druid.filters=config,stat,wall,log4j      配置监控统计拦截的filters，去掉后监控界面SQL无法进行统计，'wall'用于防火墙


参考文档：
 [DRUID连接池的实用 配置详解] (https://www.cnblogs.com/wuyun-blog/p/5679073.html)
```


### SpringBoot2.x Druid
> [SpringBoot 2.x添加Druid作为数据库连接池](https://www.jianshu.com/p/4a8e56f557ea)

> [spring boot配置durid数据源](https://my.oschina.net/u/1011854/blog/1790490)

> [DataSource连接配置说明](https://blog.csdn.net/zouhuixing/article/details/80448952)

> [Druid连接池一个设置引发的血案 ](https://my.oschina.net/haogrgr/blog/224010)
- 连接池是为了防止程序从池里取连接后忘记归还，而提供一些参数来设置一个租期，这样可以一定程度上防止连接泄露
```java
public int removeAbandoned() {
        int removeCount = 0;

        long currrentNanos = System.nanoTime();

        List<DruidPooledConnection> abandonedList = new ArrayList<DruidPooledConnection>();

        synchronized (activeConnections) {
            Iterator<DruidPooledConnection> iter = activeConnections.keySet().iterator();

            for (; iter.hasNext();) {
                DruidPooledConnection pooledConnection = iter.next();

                if (pooledConnection.isRunning()) {
                    continue;
                }

                long timeMillis = (currrentNanos - pooledConnection.getConnectedTimeNano()) / (1000 * 1000);

                if (timeMillis >= removeAbandonedTimeoutMillis) {
                    iter.remove();
                    pooledConnection.setTraceEnable(false);
                    abandonedList.add(pooledConnection);
                }
            }
        } ....略
    }
    
 # timeMillis >= removeAbandonedTimeoutMillis  timeMillis 这个是getConnection()被调用时的时间
 # 意思就是一个连接被get后, 超过了 removeAbandonedTimeoutMillis这么久我就弄死你.
 # 通过datasource.getConnontion() 取得的连接必须在removeAbandonedTimeout这么多秒内调用close(),要不我就弄死你.(就是conn不能超过指定的租期)
```


### 部分源码说明
```java
/**
	 * Tomcat Pool DataSource configuration.
	 */
	 //只要导入了这个org.apache.tomcat.jdbc.pool.DataSource.class，就会生效
	@ConditionalOnClass(org.apache.tomcat.jdbc.pool.DataSource.class)
	//没有DataSource的实例
	@ConditionalOnMissingBean(DataSource.class)
	//配置了spring.datasource.type 并且值为org.apache.tomcat.jdbc.pool.DataSource，才会生效；
	//matchIfMissing=true表示，就算没有配置spring.datasource.type，也会生效
	@ConditionalOnProperty(name = "spring.datasource.type", havingValue = "org.apache.tomcat.jdbc.pool.DataSource", matchIfMissing = true)
	static class Tomcat {
 
		@Bean
		@ConfigurationProperties(prefix = "spring.datasource.tomcat")
		public org.apache.tomcat.jdbc.pool.DataSource dataSource(
				DataSourceProperties properties) {
			org.apache.tomcat.jdbc.pool.DataSource dataSource = createDataSource(
					properties, org.apache.tomcat.jdbc.pool.DataSource.class);
			DatabaseDriver databaseDriver = DatabaseDriver
					.fromJdbcUrl(properties.determineUrl());
			String validationQuery = databaseDriver.getValidationQuery();
			if (validationQuery != null) {
				dataSource.setTestOnBorrow(true);
				dataSource.setValidationQuery(validationQuery);
			}
			return dataSource;
		}
 
	}
```

### mysql出现： 
> The server time zone value '�й���׼ʱ��' is unrecognized or represents more than one time zone
解决方法：
1. 在数据库连接url后增加：jdbc.url=jdbc:mysql:///:3306/test?useUnicode=true&characterEncoding=utf8&serverTimezone=UTC
   无效
2. 直接修改数据库时区
  a. cmd mysql -uroot -p123456
  b. show global variables like 'time_zone';
  c. set global time_zone = '+8:00'; ##修改mysql全局时区为北京时间，即我们所在的东8区
  d. set time_zone = '+8:00'; ##修改当前会话时区
  e. flush privileges; #立即生效
