# Getting Started

### 用户目录新建
```markdown
1. Linux下创建用户及组
a. 创建组 
groupadd 组名
示例：groupadd dev 

b. 创建用户，并将用户添加到组
useradd 用户名 -g 组名
示例：useradd es -g dev

c. 更改用户密码
password 用户名
示例：password es

d. 修改目录的所有权(所属用户和组)
chown -R 组名:用户名 文件名
示例：chown -R es:dev elastic



```


### 二、Linux安装rzse
```markdown
 yum -y install lrzsz
```


### 三、Elasticsearch基础环境配置
> elasticsearch用户没有该文件夹的权限，执行命令
```bash
Exception in thread "main" java.nio.file.AccessDeniedException: /usr/local/elasticsearch/elasticsearch-6.2.2-1/config/jvm.options
```
```markdown
ES不能使用root用户启动，所以需要新建用户es。并授予用户执行目录的权限
chown -R es:dev elastic
```
---
> 
```bash
max virtual memory areas vm.max_map_count [65530] is too low, increase to at least [262144]
```
```markdown
修改/etc/sysctl.conf文件，增加配置vm.max_map_count=262144
vim /etc/sysctl.conf 
vm.max_map_count=262144
sysctl -p
```
---
> 每个进程最大同时打开文件数太小
```bash
max file descriptors [4096] for elasticsearch process is too low, increase to at least [65536]
```
```markdown
1. 想每个进程最大同时打开文件数太小，可通过下面2个命令查看当前数量
    ulimit -Hn
    ulimit -Sn

2. 修改/etc/security/limits.conf文件，增加配置，用户退出后重新登录生效
*     soft    nofile          65536
*     hard    nofile          65536
```
---
> 最大线程个数太低
```bash
max number of threads [3818] for user [es] is too low, increase to at least [4096]
```
```markdown
1. 可通过命令查看
    ulimit -Hu
    ulimit -Su

2. 修改配置文件/etc/security/limits.conf（和问题1是一个文件），增加配置
*    soft    nproc           4096
*    hard    nproc           4096

```
---