# wechat-mall
A lightweight WeChat mall

## init

### Developer mailing list
name  | email
---|---
Pengfei Pei | <ppf@jiumao.org>
Xingkang Wang | <wxk@jiumao.org>

### 模块

#### auth - 授权访问
- [ ] 实现OAuth2.0认证协议
- [ ] request-key控制访问次数。

#### web - HTTP服务接口
- [ ] 实现需求所有模块的功能，数据模拟

#### service - 服务逻辑层
- [ ] 实现HTTP服务查询的缓存这里先不实现，透传。实现考虑nosql
- [ ] 消息队列堆积。
- [ ] 复杂逻辑

#### db - 服务缓冲层
- [ ] 实现数据持久化和更新。
- [ ] 为 ==service== 层提供接口，松耦合。


#### api - 服务API层
- [ ] 实现服务依赖的其它API服务。如：支付、短信。
- [ ] 实现跟业务无关的其它RPC服务。

#### common - 服务工具层
- [ ] 实现服务共用的工具模块，业务无关。如：json、cache工具、日志工具等


### skills
* development environment
* technology stack 
* Jersey 2
* Spring 4
* Mybatis
* MySql
* font-end vue html5
#### expand
- [ ] 1. 结构化数据用MySQL，详细信息用Mongodb做为cache。
  - 例如：用户基本信息用mysql，用户的详细信息用mongodb存储，通过用户唯一ID作为桥梁；
  - 订单信息、待收货、购物车都是用mongodb做存储。MySQL存储精简重要的结构化数据以及事务相关的结构。
- [ ] 2. 前后端分离：nodejs+mongo主要作为前端查询API，为前端提供接口。
  - 计算、更新等复杂逻辑由java来完成。
- [ ] 3. 拓展性考虑：每此请求都应该有唯一的request-id，用于追踪。
  - 如果请求过载，使用消息队列，如：订单等信息，可以根据唯一的request-id，异步回查处理状态（比如：sleep 50ms 用request-id回查处理状态）。
  - request-id应该由不同的业务逻辑区分并唯一。
  - 根据request-id记录日志，记录请求耗时、时间（戳）。
- [ ] 4. 图片服务器：大量的小图片会占用系统句柄，所以一个分布式、容灾、高可用的RESTful图片服务器十分必要。
  - 练手使用：seaweedfs，golang实现，效果不错。

> <html>
> <p>MySQL with structured data, detailed information with Mongodb cache. For example: user basic information using mysql, the user's detailed information stored in mongodb, through the user's unique ID as a bridge; order information, waiting for goods, shopping carts are stored using mongodb. Structured data and important matters related to MySQL storage structure.</p><p>
> Before and after the end of separation: nodejs+mongo as a front-end query API, provides the interface for the front end. Calculate and update the complex logic to complete by java.</p><p>
> Expand this request should be considered: each has a unique request-id for tracking. If the request is overloaded, using the message queue, such as orders and other information, you can asynchronously retrieve the state of processing according to the unique request-id (e.g.: sleep 50ms then with request-id to check the state). Request-id should be different from the business logic and the only.</p>
> </html>



### demo
* eg1:export as a war file, deploy for tomcat webapp
* eg2:run as run on server by tomcat that version than tomcat6
* then hello world : http://localhost:8080/wechatMall/admin/index 
* org.jiumao.wechatMall.admin.resource.IndexResource