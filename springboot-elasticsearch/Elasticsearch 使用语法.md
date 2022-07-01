# Elasticsearch查询使用语法

GET _search
{
  "query": {
    "match_all": {}
  }
}


PUT /ad/phone/1
{
  "name":"phone 8",
  "price": 6000,
  "color":"white",
  "ad":"this is a white phone",
  "label":["white","nice"]
}

PUT /ad/_doc/2
{
  "name":"xiaomi 8",
  "price": 4000,
  "color":"red",
  "ad":"this is a red phone",
  "label":["white","xiaomi"]
}

PUT /ad/phone/3
{
  "name":"huawei p30",
  "price": 5000,
  "color":"white",
  "ad":"this is a white phone",
  "label":["white","huawei"]
}


PUT /ad/_create/5
{
  "name":"huawei p40",
  "price": 6000,
  "color":"bkack",
  "ad":"this is a bkack phone",
  "label":["bkack","huawei"]
}


GET /ad/phone/_search
{
  "query": {
    "match_all": {}
  }
}


GET /ad/_search
{
  "query": {
    "match_all": {}
  }
}


# 不使用from size做深度分页，会有性能问题
GET /ad/_search
{
  "query": {
    "match_all": {}
  },
  "from": 0, 
  "size": 2
}








## ElasticSearch实战入门


### 一、什么是Elasticsearch
> - 基于Apache Lucene构建的开源搜索引擎；
> - 采用JAVA编写，提供简单易用的RESTFUL API
> - 可支持PB级的结构化和非结构化数据存储，轻松横向扩展


### 二、应用场景
- 海量数据分析引擎
- 站内搜索引擎
- 数据仓库

一线公司实际应用场景

- 维基百科
- gitHub 站内实时搜索
- 百度搜索

### 三、 基础概念
- 集群、节点
集群：是由一个或多个ES节点组成的集合

> 索引：必须是英文字母小写且不含中划线
- 索引：含有相同属性的文档集合
- 类型：索引可以定义多个类型，文档必须属于一个类型
- 文档：是可以被索引的基本数据单位 
- 分片：每个索引都有多个分片，每个分片都是一个Luence索引
- 备份：分片的备份  

> 分片：可以将索引数据，分片存储于多台服务器，分担搜索压力

> 备份：当主分片出现错误，可以使用备份分片来提供服务。保证高可用

- 默认：创建5个分片，1个备份。可配置(分片只能在创建索引时指定，备份可以后期修改)


### 四、基本用法
#### 1. 创建索引
PUT /theme_task
```json
{
  "settings":{
    "number_of_shards": 3,
    "number_of_replicas": 1
  },
  "mappings": {
    "properties": {
        "area_code":{
           "type": "keyword"
        },
        "area_name":{
           "type": "keyword"
        }
      }
  }
}
```

PUT /people/_mappings
```json
{
"properties": {
    "name":{
       "type": "text"
    },
    "country":{
       "type": "keyword"
    },
    "age": {
        "type": "integer"
    },
    "date": {
         "type": "date",
         "format": "yyyy-MM-dd HH:mm:ss||yyyy-MM-dd||epoch_millis" 
    }
  }
}
```

#### 2. 插入数据
PUT /people/_doc/1
```json
{
  "name": "zhangsan",
  "country": "china",
  "age": 18,
  "date": "2020-12-31"
}
```

#### 3. 修改数据
POST /people/_doc/1/_update
> 直接修改，指定文档ID
```json
{
  "doc": {
    "name": "lisi",
     "country": "usa",
     "age": 18,
     "date": "2020-12-31"
  }
}
``` 

> 通过脚本修改
```json
{
  "script": {
     "lang": "painless",
     "inline": "ctx._source.age = params.age",
     "params": {
        "age": 100
     }
  }
}
```

#### 4. 删除操作
4-1. 删除索引
DELETE /people

4-2. 删除文档
DELETE /people/1


#### 5. 查询操作
5-1. 简单查询
GET /people/_doc/1

5-2. 条件查询
POST /people/_search
- 全匹配查询
```json
{
  "query": {
     "query_all": {}
  },
  "from": 1,
  "size": 10 
}
```
- 精确查询
```json
{
  "query": {
     "match": {
        "name": "zhangsan"
     }
  },
  "sort": [{
     "age": {
        "order": "desc"
     }
  }],
  "from": 1,
  "size": 10 
}
```

5-3. 聚合查询
- 按年龄查总数
```json
{
  "aggs": {
     "group_by_age_count": {
        "terms": {
           "field": "age"
        }
     }
  }
}
```
- 按年龄查总数、和时间
```json
{
  "aggs": {
     "group_by_age_count": {
        "terms": {
           "field": "age"
        }
     },
     "group_by_date": {
        "terms": {
            "field": "date"
        }
     }
  }
}
```

- 对年龄进行统计计算
```json
{
  "aggs": {
     "grades_age_count": {
        "stats": {
           "field": "age"
        }
     }
  }
}
```

```json
{
  "aggs": {
     "grades_age_count": {
        "min": {
           "field": "age"
        }
     }
  }
}
```

#### 6. 高级查询操作

- 子条件查询  特定字段查询所指特定值
  - Query context: 查询中除了判断文档是否满足，还会计算_score来标识匹配度，旨在判断目标文档和查询条件匹配的有多好
  - Filter context: 查询过程中，只判断该文档是否满足条件，只有YES NO
  
- Query context
  - 全文本查询     针对文本类型数据
  - 字段级别的查询 针对结构化数据 如数字日期等

- (全文本查询)根据名字模糊查询(会查到包含 zhang和san的所有数据)
```json
{
  "query": {
     "match": {
        "name": "zhang san"
     }
  }
}
```

- (全文本查询)根据名字其余匹配查询(查到name为 zhang san的数据)
```json
{
  "query": {
     "match_phrase": {
        "name": "zhang san"
     }
  }
}
```

- (全文本查询)多个字段的模糊匹配查询(查询 name\country只要包含zhangsan就被查出来)
```json
{
  "query": {
     "multi_match": {
        "query": "zhangsan", 
        "fields": ["name","country"] 
     }
  }
}
```

- (全文本查询)语法查询(指定属性字段 可加可不加)
```json
{
  "query": {
     "query_string": {
        "query": "(zhangsan AND wangwu) OR lisi",
        "fields": ["name","country"]  
     }
  }
}
```

- (结构化数据查询)查询年龄10的数据
```json
{
  "query": {
     "term": {
        "age": 10
     }
  }
}
```

- (结构化数据查询)查询姓名zhangsan的数据
```json
{
  "query": {
     "term": {
        "name": "zhangsan"
     }
  }
}
```

- (结构化数据查询)查询年龄大于1小于20的数据
```json
{
  "query": {
     "range": {
        "age": {
            "gte": 1,
            "lte": 20
        }
     }
  }
}
```

- (结构化数据查询)查询范围日期的数据
```json
{
  "query": {
     "range": {
        "date": {
            "gte": "2020-12-13",
            "lte": "2020-12-15"
        }
     }
  }
}
```
```json
{
  "query": {
     "range": {
        "date": {
            "gte": "2020-12-13",
            "lte": "now"
        }
     }
  }
}
```


- 复合条件查询 以一定的逻辑组合子条件查询
  - 固定分数查询
  - bool查询

- 查询年龄等于10的数据，filter就是对数据进行过滤的。ES会对结果进行过缓存；filter结合bool一起使用
```json
{
  "query": {
     "bool": {
        "filter": {
            "term": {
               "age": 10
            }
        }
     }
  }
}
```

- (固定分数查询) 姓名是zhangsan的数据
```json
{
  "query": {
     "constant_score": {
        "filter": {
            "match": {
               "name": "zhangsan"
            },
            "boots": 2 //指定分数 固定值为2
        }
     }
  }
}
```

- (bool查询) 姓名是zhangsan或年龄10的数据(只要满足其中一个条件就可以)
```json
{
  "query": {
     "bool": {
        "should": [
            {
                "match": {
                   "name": "zhangsan"
                }
            },
            {
                "match": {
                  "age": 10
                 }
            }
        ]
     }
  }
}
```

- (bool查询) 姓名是zhangsan和年龄10的数据(且的关系,必须都满足)
```json
{
  "query": {
     "bool": {
        "must": [
            {
                "match": {
                   "name": "zhangsan"
                }
            },
            {
                "match": {
                  "age": 10
                 }
            }
        ]
     }
  }
}
```

- (bool查询) 姓名是zhangsan和年龄10的数据且country是usa的(且的关系,必须都满足)
```json
{
  "query": {
     "bool": {
        "must": [
            {
                "match": {
                   "name": "zhangsan"
                }
            },
            {
                "match": {
                  "age": 10
                 }
            }
        ],
        "filter": [
            {
              "term": {
                  "country": "usa"
              }
            }
        ]
     }
  }
}
```

- (bool查询) 姓名不是zhangsan的数据
```json
{
  "query": {
     "bool": {
        "must_not": {
                "match": {
                   "name": "zhangsan"
                }
         }
     }
  }
}
```
















































