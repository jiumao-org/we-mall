package org.jiumao.db.mongo;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;

import com.mongodb.BasicDBObject;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.model.Filters;
import com.mongodb.client.result.DeleteResult;


public final class MongoDBUtil {

    public static void insert(MongoCollection<Document> col, String... jsons) {
        if (jsons.length == 1) {
            col.insertOne(Document.parse(jsons[0]));
        }
        else {
            List<Document> docs = new ArrayList<Document>(jsons.length);
            for (String json : jsons) {
                docs.add(Document.parse(json));
            }
            col.insertMany(docs);
        }
    }


    public static void insertBatch(MongoCollection<Document> col, List<String> jsons) {
        List<Document> docs = new ArrayList<Document>(jsons.size());
        for (String json : jsons) {
            docs.add(Document.parse(json));
        }
        col.insertMany(docs);
    }


    public static Document findById(MongoCollection<Document> col, String id) {
        Document myDoc = col.find(Filters.eq("_id", new ObjectId(id))).first();
        return myDoc;
    }


    public static int getCount(MongoCollection<Document> col) {
        int count = (int) col.count();
        return count;
    }


    public static MongoCursor<Document> find(MongoCollection<Document> col, Bson filter) {
        return col.find(filter).iterator();
    }


    public static MongoCursor<Document> findByPage(MongoCollection<Document> col, Bson filter, int pageNo,
            int pageSize) {
        Bson orderBy = new BasicDBObject("_id", 1);
        return col.find(filter).sort(orderBy).skip((pageNo - 1) * pageSize).limit(pageSize).iterator();
    }


    public static int deleteById(MongoCollection<Document> col, String id) {
        int count = 0;
        Bson filter = Filters.eq("_id", new ObjectId(id));
        DeleteResult deleteResult = col.deleteOne(filter);
        count = (int) deleteResult.getDeletedCount();
        return count;
    }


    public static Document updateById(MongoCollection<Document> col, String id, Document newdoc,
            boolean repalce) {
        Bson filter = Filters.eq("_id", new ObjectId(id));
        if (repalce)
            col.replaceOne(filter, newdoc);
        else
            col.updateOne(filter, new Document("$set", newdoc));
        return newdoc;
    }

}
