# `SpringBoot Validator` 参数校验



### 一、`JSR303 `  [ `javax.validation` ]

> `JSR303` 是一套 `JavaBean` 参数校验的标准，它定义了很多常用的校验注解



### 二、常用注解

|       **限制注解**        |                         **注解说明**                         |
| :-----------------------: | :----------------------------------------------------------: |
|           @Null           |                        限制只能为null                        |
|         @NotNull          |              限制必须不为null，但可以为空字符串              |
|         @NotEmpty         | 验证注解的元素值不为null且不为空（字符串长度不为0、集合大小不为0） |
|         @NotBlank         | 验证注解的元素值不为空（不为null、去除首位空格后长度为0），不同于@NotEmpty，@NotBlank只应用于字符串且在比较时会去除字符串的空格 |
|       @AssertFalse        |                       限制必须为false                        |
|        @AssertTrue        |                        限制必须为true                        |
|    @DecimalMax(value)     |               限制必须为一个不大于指定值的数字               |
|    @DecimalMin(value)     |               限制必须为一个不小于指定值的数字               |
| @Digits(integer,fraction) | 限制必须为一个小数，且整数部分的位数不能超过integer，小数部分的位数不能超过fraction |
|          @Future          |                   限制必须是一个将来的日期                   |
|           @Past           |           验证注解的元素值（日期类型）比当前时间早           |
|          @Email           | 验证注解的元素值是Email，也可以通过正则表达式和flag指定自定义的email格式 |
|      @Size(max,min)       |                限制字符长度必须在min到max之间                |
|      @Pattern(value)      |                 限制必须符合指定的正则表达式                 |
|                           |                                                              |



### 三、使用说明

##### 1. 实体加注解

```java
@Data
public class UserDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    /*** 用户ID*/
    @NotNull(message = "用户id不能为空", groups = Update.class)
    private Long userId;

    /**
     * 用户名
     */
    @NotBlank(message = "用户名不能为空")
    @Length(max = 20, message = "用户名不能超过20个字符", groups = {Create.class, Update.class})
    @Pattern(regexp = "^[\\u4E00-\\u9FA5A-Za-z0-9\\*]*$", message = "用户昵称限制：最多20字符，包含文字、字母和数字")
    private String username;

    /**
     * 手机号
     */
    @NotBlank(message = "手机号不能为空")
    @Pattern(regexp = "^[1][3,4,5,6,7,8,9][0-9]{9}$", message = "手机号格式有误", groups = {Create.class, Update.class})
    private String mobile;

    /**
     * 性别
     */
    private String sex;

    /**
     * 邮箱
     */
    @NotBlank(message = "联系邮箱不能为空")
    @Email(message = "邮箱格式不对")
    private String email;

    /**
     * 密码
     */
    private String password;

    /*** 创建时间 */
    @Future(message = "时间必须是将来时间", groups = {Create.class})
    private Date createTime;

}
```


#### 2. 自定义参数校验注解 ,需要配合实现对应的校验逻辑

```java\
@Documented
@Target({ElementType.PARAMETER, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = IdentityCardNumberValidator.class)
public @interface IdentityCardNumber {

    String message() default "身份证号码不合法";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
```

### 3. `@Validated` 和 `@Valid` 的使用

> 在校验`Controller`入参时，`@Validated` 和 `@Valid` 基本没什么区别，都可以配合`BindingResult`来使用
>
> 在 分组、注解的地方、支持嵌套校验等功能上不同
>
> `@Validated` 和 `@Valid` 加在方法参数前，都不会自动对参数进行嵌套验证

`@Validated` ：可以作用于 `类`、`方法` 、`方法入参前`

`@Valid` ：可以作用于 `方法` 、`方法入参前` 、`属性` 、`构造函数`

**示例 ：**

```java
/**
* 在ItemController的addItem函数上再使用@Validated或者@Valid，就能对Item的入参进行嵌套验证。此时   * Item里面的props如果含有Prop的相应字段为空的情况，Spring Validation框架就会检测出来
*/
@RestController
public class ItemController {

    @RequestMapping("/item/add")
    public void addItem(@Validated Item item, BindingResult bindingResult) {
        doSomething();
    }
}

public class Item {

    @NotNull(message = "id不能为空")
    @Min(value = 1, message = "id必须为正整数")
    private Long id;

    @Valid // 嵌套验证必须用@Valid
    @NotNull(message = "props不能为空")
    @Size(min = 1, message = "props至少要有一个自定义属性")
    private List<Prop> props;
}

public class Prop {

    @NotNull(message = "pid不能为空")
    @Min(value = 1, message = "pid必须为正整数")
    private Long pid;

    @NotNull(message = "vid不能为空")
    @Min(value = 1, message = "vid必须为正整数")
    private Long vid;
}
原文链接：https://blog.csdn.net/qq_27680317/article/details/79970590
```

#### 4. 使用groups 分组 校验

> 分组校验的需求是： 同一个属性，可能在不同的接口业务中是否需要校验不一定。
>
> 例如：主键ID 在创建是不需要校验，但在修改时是必校验字段

定义`Create` `Update` 两个分组】

```java
public interface Create extends Default {
}
public interface Update extends Default{
}
```

再需要校验的地方通过 `@Validated` 来声明哪些校验组生效 

```java
/**
     * 走参数校验注解的 groups 组合校验
     */
@PostMapping("/update/groups")
public RspDTO update(@RequestBody @Validated(Update.class) UserDTO userDTO) {
    userService.updateById(userDTO);
    return RspDTO.success();
}
```

#### 5. RESTFUL风格的接口

> 在`多个参数`校验 ,或者 `@RequestParam` 形式时候, 需要在`controller` 上加注 `@Validated`

```java
// 亲测：在方法入参前加 @Validated 校验不生效，会直接进入方法体。在controller加注解后，可以进行校验
@Validated
@RestController
@RequestMapping("user/")
public class HelloController {

@GetMapping("/get")
public RspDTO getUser(@RequestParam("userId") @NotNull(message = "用户id不能为空") Long userId) {
    ...
}
```



##### ★项目开发中为什么定义：自定义异常

```markdown
1.自定义异常可以携带更多的信息，不像这样只能携带一个字符串。
2.项目开发中经常是很多人负责不同的模块，使用自定义异常可以统一了对外异常展示的方式。
3.自定义异常语义更加清晰明了，一看就知道是项目中手动抛出的异常。
```

```java
@Getter //只要getter方法，无需setter
public class ApiException extends RuntimeException {
    private int code;
    private String msg;

    public ApiException() {
        this(1001, "接口错误");
    }

    public ApiException(String msg) {
        this(1001, msg);
    }

    public ApiException(int code, String msg) {
        super(msg);
        this.code = code;
        this.msg = msg;
    }
}
```

#### 6. 全局统一异常处理 校验返回

```java
//basePackageClasses 可以指定统一异常处理作用于 某个类
@RestControllerAdvice(basePackageClasses = { EipConfigProductionScheduleController.class })
public class ValidExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(ValidExceptionHandler.class);

    @ExceptionHandler({MethodArgumentNotValidException.class})
    public ApiResult<?> handleValidException(MethodArgumentNotValidException e) {
        log.warn(e.getMessage(), e);
        List<String> messageList = (List)e.getBindingResult().getAllErrors().stream().map((it) -> {
            log.warn(it.getDefaultMessage());
            return it.getDefaultMessage();
        }).collect(Collectors.toList());
        String message = StringUtils.join(messageList, ",");
        return ApiResult.error(BaseErrorMessage.BAD_REQUEST, message);
    }
}
```



### 四、参考文档

[不这么写参数校验(validator)可能会被劝退](https://www.jianshu.com/p/f6d47e4cf9ec)
[bolg作者GitHub](https://github.com/leaJone/mybot.git)



[如何设计API接口，实现统一格式返回？](https://www.toutiao.com/i6694404645827117572/)

参考：
[D:\User\Learn\MyLearn\springboot\springboot-rest-api\Springboot 优雅处理返回和异常.md]



[@Validated和@Valid区别](https://blog.csdn.net/qq_27680317/article/details/79970590)

[Springboot项目统一异常处理-404](https://blog.csdn.net/u014229347/article/details/93183143)



### 五、成体系的优雅的后端接口

★★★  **`牛逼`**
项目实践 `SpringBoot`三招组合拳
从思路到实现，可以说是很全的一套服务端开发规范模板
[项目实践 SpringBoot三招组合拳，手把手教你打出优雅的后端接口](https://www.jianshu.com/p/b5b8613769db)
[项目实践 后端接口统一规范的同时，如何优雅得扩展规范](https://www.jianshu.com/p/ecc41e873fe3)
[GitHub 仓库地址](https://github.com/RudeCrab/rude-java)




### 六、测试数据
1. 保存用户接口：localhost:8080/user/save/valid
```properties
{
 "username":"zhangsan2",
 "mobile":"13453003986",
 "clientCardNo":"142234199111027117",
 "sex":"man",
 "email":"man@126.com",
 "password":"abcd1234",
 "createTime":"2019-12-24T12:48:26.366Z"
}
```

返回数据：
```properties
{
  "code": 200,
  "message": "success"
}
```

2. 查询用户接口：localhost:8080/user/get?userId=2
返回数据：
```properties
{
  "code": 200,
  "message": "success",
  "data": {
    "userId": 2,
    "username": "zhangsan3",
    "mobile": "13453003986",
    "clientCardNo": "142234199111027117",
    "sex": "man",
    "email": "man@126.com",
    "password": "abcd1234",
    "createTime": "2019-12-24T12:48:26.366+0000"
  }
}
```

3. 修改用户数据：localhost:8080/user/update/groups
```properties
{
 "userId":2,
 "username":"zhangsan3",
 "mobile":"13453003986",
 "clientCardNo":"142234199111027117",
 "sex":"man",
 "email":"man@126.com",
 "password":"abcd1234",
 "createTime":"2019-12-24T12:48:26.366Z"
}
```

返回数据：
```properties
{
  "code": 200,
  "message": "success"
}
```