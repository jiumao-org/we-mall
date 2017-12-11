package org.jiumao.db;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;
import org.jiumao.common.utils.MallConstants;
import org.jiumao.db.mongo.MongoV3;

import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;


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


    public static class Indexs {
        public static final Document HASHED_ID = Document.parse("{ _id: \"hashed\" }");

    }
    public static class Tables {
        public static final MongoCollection<Document> GOOD_COL = MongoUtil.getCollection(MallConstants.GOOD_TABLE_NAME);
        public static final MongoCollection<Document> ORDER_COL = MongoUtil.getCollection(MallConstants.ORDER_TABLE_NAME);
        /** 用于回查订单状态 */
        public static final MongoCollection<Document> ORDER_STATE_COL = MongoUtil.getCollection(MallConstants.ORDER_STATE_TABLE_NAME);

        public static final MongoCollection<Document> USER_ORDER_COL = MongoUtil.getCollection(MallConstants.USER_ORDERS_TABLE_NAME);
        static {
            GOOD_COL.createIndex(Indexs.HASHED_ID);
            GOOD_COL.createIndex(new Document().append("userId", 1).append("unique", true));
            ORDER_COL.createIndex(Indexs.HASHED_ID);
            ORDER_COL.createIndex(new Document().append("userId", 1).append("unique", true));
            ORDER_COL.createIndex(new Document().append("creatTime", 1).append("unique", true));
        }
    }



}
