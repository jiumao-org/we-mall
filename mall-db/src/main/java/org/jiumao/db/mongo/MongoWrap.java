package org.jiumao.db.mongo;


/**
 * mongo 的增删改查接口实现
 * 
 * @author ppf@jiumao.org
 * @date 2017年11月24日
 */
public abstract class MongoWrap<DB,Collection> {

    protected MongoConnect client;

    abstract void setMongoConnect(MongoConnect client);

    abstract Collection getCollection(DB db, String name);

    abstract DB getDB(String dbName);

}
