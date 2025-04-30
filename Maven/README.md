# 移卡技术社区


## 启动

```shell
mvn spring-boot:run
```

## 登录

```shell
http://localhost:8080/foo/swagger-ui/index.html
```

## 访问 actuator

```shell
http://localhost:8080/foo/actuator
http://localhost:8080/foo/actuator/health
```

## API

```shell
curl localhost:8080/foo/api/articles
curl -X POST localhost:8080/foo/api/articles/1/pay
curl -X POST localhost:8080/foo/api/articles/1/fulfill

curl localhost:8080/foo/api/articles/2
curl -X POST localhost:8080/foo/api/articles/2/cancel
```
