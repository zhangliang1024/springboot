# Spring 事务失效及嵌套事务



### 一、Spring 事务不生效的原因

> - Spring团队建议 在具体的实现类或者方法上使用@Transactional注解，而不要使用在接口上。使用于接口上，只有基于接口的代理时才生效。而实际开发中都是基于类的代理，那事务将不能被基于类的代理识别，而对象也不能被事务代理所包装。
> 
> - 注解是不能继承的。

```markdown
1. 数据库引擎是否支持事务：Mysql的InnoDB引擎支持事务，MyISAM引擎不支持。
2. 必须是public方法。这是因为Spring的AOP特性决定，不切入private修饰的方法。
3. private\final\static 修饰的方法事务都不生效。
4. Spring事务只能对RuntimeException运行期异常及其子类进行回滚。Spring认为Checked的异常属于业务，开发需要给出解决方案而不是直接扔给框架
5. @EnableTransactionManagement注解：springboot1.4后可以不写。框架在初始化时就默认注入了两个事务管理器的Bean `JDBC`的`DataSourceTransactionManager` 和 `JPA`的 `JPATransactionManager`。
   如果是自己自定的，不是AutoConfig的，则要启动该注解来开启事务。
6. 请确认你的类是否被代理了。spring的事务实现原理是AOP，只有通过代理对象调用方法才能被拦截，事务才能生效。
7. 请确保业务和事务的入口在同一个线程里，否则事务也不生效。
8. service方法中：调用本类中的另一个方法，事务不生效。
```

### 二、 示例

> 事务不生效：外部调用addInfo方法，因为addInfo没有事务，因此create的事务不会生效，也不回回滚。这里Spring的AOP是失效的
```java
public void addInfo(final Period entity){
    create(entity);
}

@Transactional
public int create(Period entity){
    super.create(entity);
    int i = 1/0;
    return 0;
}
```
> 事务生效
```java
@Transactional
public void addInfo(final Period entity){
    create(entity);
}

@Transactional
public int create(Period entity){
    super.create(entity);
    int i = 1/0;
    return 0;
}
```
> 事务生效
```java
@Transactional
public void addInfo(final Period entity){
    create(entity);
     int i = 1/0;
}

@Transactional
public int create(Period entity){
    super.create(entity);
    return 0;
}
```
> 事务生效
```java
@Transactional
public void addInfo(final Period entity){
    create(entity);
     int i = 1/0;
}

public int create(Period entity){
    super.create(entity);
    return 0;
}
```
> 事务生效: 事务切入了addInfo方法
```java
@Transactional
public void addInfo(final Period entity){
    create(entity);
}

public int create(Period entity){
    super.create(entity);
    int i = 1/0;
    return 0;
}
```
> 事务不生效： 没有事务
```java
public void addInfo(final Period entity){
    create(entity);
}

public int create(Period entity){
    super.create(entity);
    int i = 1/0;
    return 0;
}
```
> 事务生效: 这是我们解决方法内部调用事务不生效的最常用方法之一。内部维护一个自己的Bean，然后使用这个属性来调用方法。
```java
public void addInfo(final Period entity){
    periodService.create(entity);
}

@Transactional
public int create(Period entity){
    super.create(entity);
    int i = 1/0;
    return 0;
}
```

### 三、`Transaction rolled back because it has been marked as rollback-only`
> - `Transaction rolled back because it has been marked as rollback-only` 事务已回滚，因为它被标记成了只回滚
>
> - 在Spring中事务的管理变的很简单。在需要事务的地方加注解 @Transactional 即可，剩下的spring会帮我们实现。
```java
@Transactional
public boolean create(User user){
    int i = userMapper.insert(user);
    System.out.println(1/0);
    return i == 1;
}
```
> 事务可以回滚
```java
@Transactional
public boolean create(User user){
    int i = userMapper.insert(user);
    presonService.addPersion(user);
    return i == 1;
}
@Transactional
public boolean addPerson(User user){
     System.out.println(1/0);
    return false;
}
```
> 事务不可以回滚：Transaction rolled back because it has been marked as rollback-only
```java
@Transactional
public boolean create(User user){
    int i = userMapper.insert(user);
    try{
        presonService.addPersion(user);
    }catch(Exception e){
        System.out.println("不中断程序，用来输出日志");
    }
    
    return i == 1;
}
@Transactional
public boolean addPerson(User user){
     System.out.println(1/0);
    return false;
}
```

#### `ITPS`
```markdown
1. Spring AOP捕获异常原理：被拦截的方法需要显示抛出异常，并不能经任何处理。这样AOP代理才能捕获到方法的异常，进行回滚。默认情况下AIP只能捕获RuntimeException异常
2. 在service上的事务方法不要自己try-catch，这样程序异常才能被AOP捕获进行回滚。
3. 若要进行try-catch后，throw new RuntimeException()也可以。AOP可以捕获异常
4. 若在行try-catch后，在catch中通过：TransactionAspectSupport.currentTransactionStatus().setRollbackOnly() 手动进行回滚，也是可行的。
5. 通过 @Transactional(propagation = Propagation.REQUIRES_NEW) 通过控制事务的传播属性，来开启新的事务。
```

### 四、参考文档
[Spring事务不生效的原因大解读](https://blog.csdn.net/f641385712/article/details/80445933)
> `Transaction rolled back because it has been marked as rollback-only` 事务已回滚，因为它被标记成了只回滚
[Spring事务嵌套引发的血案](https://blog.csdn.net/f641385712/article/details/80445912)

