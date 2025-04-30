# netty hello world

## 项目结构

本项目包含两个子模块：

- **Gradle**: 使用 Gradle 构建的 Ktor 项目，提供 RESTful API 和数据库操作。
- **Maven**: 使用 Maven 构建的 Spring Boot 项目，提供基于 HATEOAS 的 API 和用户管理功能。

## Gradle 模块

### 启动

```bash
./gradlew run
```

### 功能

- API 路由: 提供简单的 Hello World 路由。
- 序列化: 支持 JSON 序列化（Kotlinx<vscode_annotation details='%5B%7B%22title%22%3A%22hardcoded-credentials%22%2C%22description%22%3A%22Embedding%20credentials%20in%20source%20code%20risks%20unauthorized%20access%22%7D%5D'> 和</vscode_annotation> Jackson）。
- 数据库操作: 支持 PostgreSQL 和 H2 数据库，提供城市信息的增删改查接口。
- 监控: 集成 Micrometer 和 Prometheus，提供 /metrics-micrometer 端点。
- OpenAPI 支持: 提供 OpenAPI 文档生成。

### 示例 API

- GET /: 返回 Hello World!
- POST /cities: 创建城市
- GET /cities/{id}: 查询城市
- PUT /cities/{id}: 更新城市
- DELETE /cities/{id}: 删除城市

## Maven 模块

### 启动

```bash
./mvnw spring-boot:run
```

### 功能

- HATEOAS 支持: 提供基于 HATEOAS 的文章管理 API。
- 用户管理: 提供用户注册和查询功能。
- Swagger UI: 提供 Swagger UI 接口文档，访问路径为 /foo/swagger-ui/index.html。
- Actuator: 提供健康检查和监控端点，如 /foo/actuator/health。

### 示例 API

- GET /foo/api/articles: 查询所有文章
- POST /foo/api/articles: 创建文章
- GET /foo/api/articles/{id}: 查询单篇文章
- PUT /foo/api/articles/{id}: 更新文章
- POST /foo/api/articles/{id}/pay: 支付文章
- POST /foo/api/articles/{id}/cancel: 取消文章
- POST /foo/api/articles/{id}/fulfill: 完成文章

## 数据库配置

### Gradle 模块

- 默认使用嵌入式 H2 数据库。
- 可切换为 PostgreSQL，需配置 application.conf 文件。

### Maven 模块

- 默认使用 H2 数据库。
- 开发环境使用 PostgreSQL，配置文件为 application-dev.yml。

## 日志配置

- 使用 Logback 作为日志框架，日志格式和级别可通过 logback.xml 配置。

## 贡献

欢迎提交 Issue 和 Pull Request 来改进本项目。
