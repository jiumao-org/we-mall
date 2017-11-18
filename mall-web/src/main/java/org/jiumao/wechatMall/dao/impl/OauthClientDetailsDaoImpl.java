package org.jiumao.wechatMall.dao.impl;

import com.mongodb.client.result.UpdateResult;
import org.jiumao.common.utils.CommonUtils;
import org.jiumao.mall.db.mongodb.AbstractMongoSupport;
import org.jiumao.wechatMall.common.Assist;
import org.jiumao.wechatMall.dao.OauthClientDetailsDao;
import org.jiumao.wechatMall.entity.OauthClientDetails;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by 13682 on 2017/11/18.
 */
@Repository("oauthClientDetailsDaoImpl")
public class OauthClientDetailsDaoImpl extends AbstractMongoSupport implements OauthClientDetailsDao{
    @Override
    public long getOauthClientDetailsRowCount(Assist assist) {
        List<Assist.WhereRequire<?>> whereRequires = assist.getRequire();
        Query query = new Query();
        for(int i =0; i<whereRequires.size();i++){
            query.addCriteria(new Criteria(whereRequires.get(i).getRequire()).is(whereRequires.get(i).getValue()));
        }
        return this.mongoTemplate().find(query,OauthClientDetails.class).size();
    }

    @Override
    public List<OauthClientDetails> selectOauthClientDetails(Assist assist) {
        List<Assist.WhereRequire<?>> whereRequires = assist.getRequire();
        Query query = new Query();
        for(int i =0; i<whereRequires.size();i++){
            query.addCriteria(new Criteria(whereRequires.get(i).getRequire()).is(whereRequires.get(i).getValue()));
        }
        return this.mongoTemplate().find(query,OauthClientDetails.class);
    }

    @Override
    public OauthClientDetails selectOauthClientDetailsByObj(OauthClientDetails obj) {
        List<Map> valuelist = CommonUtils.getFiledNamesAndValues(obj);
        Query query = new Query();
        for(int i =0; i<valuelist.size();i++){
            query.addCriteria(new Criteria(valuelist.get(i).get("name").toString()).is(valuelist.get(i).get("value").toString()));
        }
        return this.mongoTemplate().findOne(query,OauthClientDetails.class);
    }

    @Override
    public OauthClientDetails selectOauthClientDetailsById(String id) {
        Query query = new Query(new Criteria("clientId").is(id));
        return this.mongoTemplate().findOne(query,OauthClientDetails.class);
    }

    @Override
    public int insertOauthClientDetails(OauthClientDetails value) {
        return 0;
    }

    @Override
    public int insertNonEmptyOauthClientDetails(OauthClientDetails value) {
        this.mongoTemplate().save(value);
        return Integer.getInteger(value.clientId());
    }

    @Override
    public long deleteOauthClientDetailsById(String id) {
        OauthClientDetails oauthClientDetails = selectOauthClientDetailsById(id);
        return this.mongoTemplate().remove(oauthClientDetails).getDeletedCount();
    }

    @Override
    public long deleteOauthClientDetails(Assist assist) {
        List<Assist.WhereRequire<?>> whereRequires = assist.getRequire();
        Query query = new Query();
        for(int i =0; i<whereRequires.size();i++){
            query.addCriteria(new Criteria(whereRequires.get(i).getRequire()).is(whereRequires.get(i).getValue()));
        }
        return this.mongoTemplate().remove(query,OauthClientDetails.class).getDeletedCount();
    }

    @Override
    public long updateOauthClientDetailsById(OauthClientDetails enti) {
        List<Map> valuelist = CommonUtils.getFiledNamesAndValues(enti);
        Update upd = new Update();
        for(int i =0; i<valuelist.size();i++){
            if(valuelist.get(i).get("name").equals("clientId")){
                continue;
            }else{
                upd.set(valuelist.get(i).get("name").toString(),valuelist.get(i).get("value").toString());

            }
        }
        Query query = new Query(new Criteria("clientId").is(enti.clientId()));
        UpdateResult updateResult = this.mongoTemplate().updateFirst(query,upd,OauthClientDetails.class);
        return updateResult.getModifiedCount();
    }

    @Override
    public long updateOauthClientDetails(OauthClientDetails value, Assist assist) {
        List<Assist.WhereRequire<?>> whereRequires = assist.getRequire();
        Query query = new Query();
        for(int i =0; i<whereRequires.size();i++){
            query.addCriteria(new Criteria(whereRequires.get(i).getRequire()).is(whereRequires.get(i).getValue()));
        }
        List<Map> valuelist = CommonUtils.getFiledNamesAndValues(value);
        Update upd = new Update();
        for(int i =0; i<valuelist.size();i++){
            if(valuelist.get(i).get("name").equals("clientId")){
                continue;
            }else{
                upd.set(valuelist.get(i).get("name").toString(),valuelist.get(i).get("value").toString());

            }
        }
        UpdateResult updateResult = this.mongoTemplate().updateFirst(query,upd,OauthClientDetails.class);
        return updateResult.getModifiedCount();
    }

    @Override
    public long updateNonEmptyOauthClientDetailsById(OauthClientDetails enti) {
        List<Map> valuelist = CommonUtils.getFiledNamesAndValues(enti);
        Update upd = new Update();
        for(int i =0; i<valuelist.size();i++){
            if(valuelist.get(i).get("name").equals("clientId")){
                continue;
            }else if("".equals(valuelist.get(i).get("value").toString())||"null".equals(valuelist.get(i).get("value").toString())){
                continue;
            }else{
                upd.set(valuelist.get(i).get("name").toString(),valuelist.get(i).get("value").toString());

            }
        }
        Query query = new Query(new Criteria("clientId").is(enti.clientId()));
        UpdateResult updateResult = this.mongoTemplate().updateFirst(query,upd,OauthClientDetails.class);
        return updateResult.getModifiedCount();
    }

    @Override
    public long updateNonEmptyOauthClientDetails(OauthClientDetails value, Assist assist) {
        List<Assist.WhereRequire<?>> whereRequires = assist.getRequire();
        Query query = new Query();
        for(int i =0; i<whereRequires.size();i++){
            query.addCriteria(new Criteria(whereRequires.get(i).getRequire()).is(whereRequires.get(i).getValue()));
        }
        List<Map> valuelist = CommonUtils.getFiledNamesAndValues(value);
        Update upd = new Update();
        for(int i =0; i<valuelist.size();i++){
            if(valuelist.get(i).get("name").equals("clientId")){
                continue;
            }else if("".equals(valuelist.get(i).get("value").toString())||"null".equals(valuelist.get(i).get("value").toString())){
                continue;
            }else{
                upd.set(valuelist.get(i).get("name").toString(),valuelist.get(i).get("value").toString());

            }
        }
        UpdateResult updateResult = this.mongoTemplate().updateFirst(query,upd,OauthClientDetails.class);
        return updateResult.getModifiedCount();
    }
}
