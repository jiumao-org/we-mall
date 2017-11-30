# 数据操作
> 数据持久化、消息队列、文件系统等等

## 数据库
> 关系型数据库 or Nosql

> 提供最简单原始封装类库，业务无关，用于微服务应用。应用通常指操作一个表，只需要一个数据库连接，不需要依赖框架或者业务。


### MySQL
* orm语句
* 通用查询工具

### MongoDB
* json操作
* other查询工具


### Kafka 0.10.1.0示例
> kafka 将作为一个数据中心使用
> 订单分析，统计等。订单查询


 - [Producer示例](https://github.com/habren/KafkaExample/tree/master/demokafka.0.10.1.0/src/main/java/com/jasongj/kafka/producer) Producer支持send callback
 - [Partitioner示例](https://github.com/habren/KafkaExample/blob/master/demokafka.0.10.1.0/src/main/java/com/jasongj/kafka/producer/HashPartitioner.java) Partitioner接口与旧版本相比有所区别，可以实现更多语义的消息路由/消息分发
 - [Consumer示例](https://github.com/habren/KafkaExample/tree/master/demokafka.0.10.1.0/src/main/java/com/jasongj/kafka/consumer) Kafka 0.10.*版本中新的Consumer使用同一套API同时实现0.8.*及以前版本中的High Level API及Low Level API
 - [Stream Low Level Processor API示例](https://github.com/habren/KafkaExample/blob/master/demokafka.0.10.1.0/src/main/java/com/jasongj/kafka/stream/WordCountProcessor.java) 
 - [Stream Topology示例](https://github.com/habren/KafkaExample/blob/master/demokafka.0.10.1.0/src/main/java/com/jasongj/kafka/stream/WordCountTopology.java) 使用Kafka Stream的Low-level Processor API实现word count
 - [Stream DSL示例](https://github.com/habren/KafkaExample/blob/master/demokafka.0.10.1.0/src/main/java/com/jasongj/kafka/stream/WordCountDSL.java) 通过Kafka Stream的DSL API实现word count功能
 - [Purchase Analysis](https://github.com/habren/KafkaExample/blob/master/demokafka.0.10.1.0/src/main/java/com/jasongj/kafka/stream/PurchaseAnalysis.java) 如何使用KStream与KTable Join，如何创建自己的Serializer/Deserializer和Serde，以及如何使用Kafka Stream的Transform和Kafka Stream的Window







