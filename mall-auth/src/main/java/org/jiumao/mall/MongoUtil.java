package org.jiumao.mall;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;
import org.bson.conversions.Bson;
import org.jiumao.db.mongo.MongoV3;

import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.IndexOptions;


public class MongoUtil {
   
    private static final MongoV3 mongo;
    private static final MongoDatabase adminDB;
    static {
        List<ServerAddress> servers = new ArrayList<ServerAddress>(1);
        servers.add(new ServerAddress("121.201.109.100", 27017));
        List<MongoCredential> authors = new ArrayList<MongoCredential>(1);

        authors.add(MongoCredential.createCredential("wechatmall", "admin", "peipengfei".toCharArray()));
        mongo = new MongoV3(servers, authors);
        adminDB = mongo.getDB("admin");
    }

    public static MongoV3 getMongo() {
        return mongo;
    }

    public static MongoDatabase getAdmindb() {
        return adminDB;
    }

    public static MongoCollection<Document> getCollection(String name) {
        return mongo.getCollection(adminDB, name);
    }
    
}
