### 第一步 安装MongoDB
#### 创建database，命名为springboot_test_db,  修改application.yml配置文件

### 第二步 安装maven
#### 安装maven，IDEA默认自带了maven，只需要安装项目依赖，详见pom文件的依赖项，下载速度慢，可以将maven仓库改为淘宝镜像

### 第三步 启动应用程序
#### POST协议访问 http://127.0.0.1:8091/user
#### 提交Body格式为json，内容如下：

```json
{
    "userName":"Kyle",
    "password":"KyleOnWay",
    "active":true
}
```

#### 根据需要，设置Content-Type为application/json，否则会报错。
错误为：
```log
Resolved [org.springframework.web.HttpMediaTypeNotSupportedException: Content type 'application/x-www-form-urlencoded;charset=utf-8' not supported]
```
