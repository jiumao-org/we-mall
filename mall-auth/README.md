### 模块

## auth - 授权访问
- [ ] 实现OAuth2.0认证协议
- [ ] App-key控制访问次数。
- [ ] 周期性后台线程要在晚上4点钟进行。

### access 访问控制
> 两种访问控制通过注解实现，一个控制是否是验证用户；另一个控制用户的访问次数和权限，比如：次数，部分接口的权限控制。
* 验证用户无状态，通过自定义实现OAuth2.0协议
   * 需要验证才能访问的接口，加@Authentication注解
* 通过Appkey访问接口控制。
   * 需要验证Appkey的接口，加@Appkey注解
   * 实现需要毫秒级判断Appkey是否有权限或者次数是否用完。
   * 性能要求10ms返回Appkey是否有效
   * 设计要求：Appkey存两张表，一张表存Appkey<->访问次数，用于快速判断；另外一张表存Appkey的详细验证信息。定期根据详细表更新快速表的次数信息，次数<1无效，没有Appkey也无效。Appkey是int类型即可，不重复。
### AUTH Store
- [ ] 存储使用mongodb
- [ ] 过期时间使用当前时间戳+过期时间ms。当下次判断过期时，判断当前时间戳>要过期时间戳。


#### mongo中存储 
==++token表++==
> 这个表只存两个字段，用于验证token是否可用。默认过期时间是1天。启动线程，周期1天删除过期token。

字段 | 解释
---|---
token | 用java的uuid，token为主键（或用自身的oid？）
expires_in | 到期时间，存储timestamp，long类型

==++token详细表++==
> token的详细信息，用于token的延期等。后天周期7天删除过期数据。

字段 | 解释
---|---
token | 用java的uuid，token为主键（或用自身的oid？）
expires_in | 过期时间，存储timestamp，long类型
refresh_token | uuid，过期时间7天
refresh_expired | timestamp
client_id | 如果不是第三方，默认是用户（user）类型
其它字段参照代码设计，必须与代码中字段一样。有些字段只有password类型token有

==++用户表++==
> 用户表只保存以下字段，用于登陆验证，详细信息通过用户user_id关联。

字段 | 解释
---|---
oid | 主键，mongo自动生产
user_id | 用户唯一编号，程序生成，自增。（IdGenerator.java）
username |
email |
phone |
password | 加密保存

==++用户详细表++==
> 参照mysql用户表和详细表，迁移到mongo中，有关事务的信息才在mysql中存储


==++client表++==
> 用于可信任的友商验证，并且验证是否启用账号。验证后，根据client_id查看是否有token并且不过期，返回已存在的token。否则生成新的token保存到token详细表覆盖client_id那条数据，token表中，过期token不用关心，定时线程删除。

字段 | 解释
---|---
client_id | 
client_secret | 
scope | 权限范围
enable | 是否启用，布尔类型。

#### code验证。
* code保存在内存中，唯一，用后删除，有效期10分钟
* code使用后保存token与时间戳，token有效期1天。
* 数据结构，Map<code,timestamp>,Map<token,timestamp>
* 用[guava](http://www.yiibai.com/)的缓存API，按照时间戳排序（实现compare接口），扫描删除（=null，集合容量不需要释放）直到有不过期（时间戳大于当前时间戳）。


#### 简化模式（implicit grant type）
* uuid生成token，默认过期时间为1天后，存入mongo的token详细表和token表。

#### 客户端模式（Client Credentials Grant）
* 验证友商的账号和密码，密码要加密
* uuid生成token，默认过期时间为1天后，存入mongo的token详细表和token表。

#### 密码模式（Resource Owner Password Credentials Grant）
> 默认密码模式为本站应用，内网应用使用，不提供给第三方

* 验证用户名与密码是否正确，用户名可以为email、手机号、账号
* 用户账号关联的第三方账户用另一张表存
* uuid生成token和refresh_token,存入mongo的token详细表和token表。
