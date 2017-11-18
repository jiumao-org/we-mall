package org.jiumao.mall.db.mongodb;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.jiumao.mall.OAuth2.BeanProvider;
import java.io.Serializable;

/**
 * Created by 13682 on 2017/11/14.
 */

public class AbstractMongoSupport {

    private MongoTemplate mongoTemplate;
    protected static final String ID = "_id";

    protected AbstractMongoSupport() {
    }

    protected MongoTemplate mongoTemplate() {
        if (mongoTemplate == null) {
            mongoTemplate = BeanProvider.getBean(MongoTemplate.class);
        }
        return mongoTemplate;
    }
    public void setMongoTemplate(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    protected <T extends Serializable> T findById(Class<T> clazz, Object id) {
        Query query = new Query(new Criteria(ID).is(id));
        return this.mongoTemplate().findOne(query, clazz);
    }


    protected Query createIDQuery(Object id) {
        final Criteria criteria = new Criteria(ID).is(id);
        return new Query(criteria);
    }
}
