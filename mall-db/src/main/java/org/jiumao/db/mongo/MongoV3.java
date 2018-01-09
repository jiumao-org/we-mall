package org.jiumao.db.mongo;

import java.util.List;
import org.bson.Document;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.WriteConcern;
import com.mongodb.MongoClientOptions.Builder;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;


/**
 * mongo操作端口
 * 
 * @since mongo 3.0
 * @author ppf@jiumao.org
 * @date 2017年11月24日
 */
@SuppressWarnings("rawtypes")
public class MongoV3 extends MongoWrap<MongoDatabase,MongoCollection<Document>> implements MongoConnect {

    private MongoClient con;

    @Override
    public MongoConnect getInstance() {
        return client;
    }

    public MongoV3(List<ServerAddress> servers, List<MongoCredential> authors) {
        Builder options = new MongoClientOptions.Builder();
        options.connectionsPerHost(50);// 连接池设置为300个连接,默认为100
        options.connectTimeout(15000);// 连接超时，推荐>3000毫秒
        options.maxWaitTime(5000); //
        options.socketTimeout(500);
        options.writeConcern(WriteConcern.W2);
        con = new MongoClient(servers, authors, options.build());
        setMongoConnect(this);
    }


    @Override
    public void setMongoConnect(MongoConnect client) {
        this.client = client;
    }

    public MongoCollection getCollection(String dbName, String collName) {
        if (null == collName || "".equals(collName)) {
            return null;
        }
        if (null == dbName || "".equals(dbName)) {
            return null;
        }
        MongoCollection collection = con.getDatabase(dbName).getCollection(collName);
        return collection;
    }

    public void close() {
        if (con != null) {
            con.close();
            con = null;
        }
    }


    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        close();
    }


    @Override
    public MongoCollection<Document> getCollection(MongoDatabase db, String name) {
        return db.getCollection(name);
    }


    @Override
    public  MongoDatabase getDB(String dbName) {
        return con.getDatabase(dbName);
    }
    
}
