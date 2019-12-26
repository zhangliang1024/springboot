# SpringBoot Validator 参数校验

### JSR303 [javax.validation]
JSR303 是一套JavaBean参数校验的标准，它定义了很多常用的校验注解

* [不这么写参数校验(validator)可能会被劝退](https://www.jianshu.com/p/f6d47e4cf9ec)
* [bolg作者GitHub](https://github.com/leaJone/mybot.git)
* []()

### 测试数据
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