package org.jiumao.wechatMall.dao.impl;

import com.mongodb.client.result.UpdateResult;
import org.jiumao.common.utils.CommonUtils;
import org.jiumao.mall.db.mongodb.AbstractMongoSupport;
import org.jiumao.wechatMall.common.Assist;
import org.jiumao.wechatMall.dao.OauthAccessTokenDao;
import org.jiumao.wechatMall.entity.OauthAccessToken;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by 13682 on 2017/11/18.
 */
@Repository("oauthAccessTokenDaoImpl")
public class OauthAccessTokenDaoImpl extends AbstractMongoSupport implements OauthAccessTokenDao{
    @Override
    public long getOauthAccessTokenRowCount(Assist assist) {
        List<Assist.WhereRequire<?>> whereRequires = assist.getRequire();
        Query query = new Query();
        for(int i =0; i<whereRequires.size();i++){
            query.addCriteria(new Criteria(whereRequires.get(i).getRequire()).is(whereRequires.get(i).getValue()));
        }
        return  this.mongoTemplate().find(query,OauthAccessToken.class).size();
    }

    @Override
    public List<OauthAccessToken> selectOauthAccessToken(Assist assist) {
        List<Assist.WhereRequire<?>> whereRequires = assist.getRequire();
        Query query = new Query();
        for(int i =0; i<whereRequires.size();i++){
            query.addCriteria(new Criteria(whereRequires.get(i).getRequire()).is(whereRequires.get(i).getValue()));
        }
        return this.mongoTemplate().find(query,OauthAccessToken.class);
    }

    @Override
    public OauthAccessToken selectOauthAccessTokenByObj(OauthAccessToken obj) {
        List<Map> valuelist = CommonUtils.getFiledNamesAndValues(obj);
        Query query = new Query();
        for(int i =0; i<valuelist.size();i++){
            query.addCriteria(new Criteria(valuelist.get(i).get("name").toString()).is(valuelist.get(i).get("value").toString()));
        }
        return this.mongoTemplate().findOne(query,OauthAccessToken.class);
    }

    @Override
    public OauthAccessToken selectOauthAccessTokenById(Object id) {
        Query query = new Query(new Criteria("tokenId").is(id));
        return this.mongoTemplate().findOne(query,OauthAccessToken.class);
    }


    @Override
    public int insertNonEmptyOauthAccessToken(OauthAccessToken value) {
        this.mongoTemplate().save(value);
        return Integer.getInteger(value.getTokenId());
    }

    @Override
    public long deleteOauthAccessTokenById(Object id) {
        OauthAccessToken oauthAccessToken = selectOauthAccessTokenById(id);
        return this.mongoTemplate().remove(oauthAccessToken).getDeletedCount();
    }

    @Override
    public long deleteOauthAccessToken(Assist assist) {
        List<Assist.WhereRequire<?>> whereRequires = assist.getRequire();
        Query query = new Query();
        for(int i =0; i<whereRequires.size();i++){
            query.addCriteria(new Criteria(whereRequires.get(i).getRequire()).is(whereRequires.get(i).getValue()));
        }
        return this.mongoTemplate().remove(query,OauthAccessToken.class).getDeletedCount();
    }

    @Override
    public long updateOauthAccessTokenById(OauthAccessToken enti) {
        List<Map> valuelist = CommonUtils.getFiledNamesAndValues(enti);
        Update upd = new Update();
        for(int i =0; i<valuelist.size();i++){
            if(valuelist.get(i).get("name").equals("tokenId")){
                continue;
            }else{
                upd.set(valuelist.get(i).get("name").toString(),valuelist.get(i).get("value").toString());

            }
        }
        Query query = new Query(new Criteria("tokenId").is(enti.getTokenId()));
        UpdateResult updateResult = this.mongoTemplate().updateFirst(query,upd,OauthAccessToken.class);
        return updateResult.getModifiedCount();
    }

    @Override
    public long updateOauthAccessToken(OauthAccessToken value, Assist assist) {
        List<Assist.WhereRequire<?>> whereRequires = assist.getRequire();
        Query query = new Query();
        for(int i =0; i<whereRequires.size();i++){
            query.addCriteria(new Criteria(whereRequires.get(i).getRequire()).is(whereRequires.get(i).getValue()));
        }
        List<Map> valuelist = CommonUtils.getFiledNamesAndValues(value);
        Update upd = new Update();
        for(int i =0; i<valuelist.size();i++){
            if(valuelist.get(i).get("name").equals("tokenId")){
                continue;
            }else{
                upd.set(valuelist.get(i).get("name").toString(),valuelist.get(i).get("value").toString());

            }
        }
        UpdateResult updateResult = this.mongoTemplate().updateFirst(query,upd,OauthAccessToken.class);
        return updateResult.getModifiedCount();
    }

    @Override
    public int insertOauthAccessToken(OauthAccessToken value) {
        return 0;
    }


    @Override
    public long updateNonEmptyOauthAccessTokenById(OauthAccessToken enti){
        List<Map> valuelist = CommonUtils.getFiledNamesAndValues(enti);
        Update upd = new Update();
        for(int i =0; i<valuelist.size();i++){
            if(valuelist.get(i).get("name").equals("tokenId")){
                continue;
            }else if("".equals(valuelist.get(i).get("value").toString())||"null".equals(valuelist.get(i).get("value").toString())){
                continue;
            }else{
                upd.set(valuelist.get(i).get("name").toString(),valuelist.get(i).get("value").toString());

            }
        }
        Query query = new Query(new Criteria("tokenId").is(enti.getTokenId()));
        UpdateResult updateResult = this.mongoTemplate().updateFirst(query,upd,OauthAccessToken.class);
        return updateResult.getModifiedCount();
    }

    @Override
    public long updateNonEmptyOauthAccessToken(OauthAccessToken value, Assist assist) {
        List<Assist.WhereRequire<?>> whereRequires = assist.getRequire();
        Query query = new Query();
        for(int i =0; i<whereRequires.size();i++){
            query.addCriteria(new Criteria(whereRequires.get(i).getRequire()).is(whereRequires.get(i).getValue()));
        }
        List<Map> valuelist = CommonUtils.getFiledNamesAndValues(value);
        Update upd = new Update();
        for(int i =0; i<valuelist.size();i++){
            if(valuelist.get(i).get("name").equals("tokenId")){
                continue;
            }else if("".equals(valuelist.get(i).get("value").toString())||"null".equals(valuelist.get(i).get("value").toString())){
                continue;
            }else{
                upd.set(valuelist.get(i).get("name").toString(),valuelist.get(i).get("value").toString());

            }
        }
        UpdateResult updateResult = this.mongoTemplate().updateFirst(query,upd,OauthAccessToken.class);
        return updateResult.getModifiedCount();
    }

}
