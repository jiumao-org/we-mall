package org.jiumao.wechatMall.dao.impl;
import com.mongodb.client.result.UpdateResult;
import org.jiumao.common.utils.CommonUtils;
import org.jiumao.mall.db.mongodb.AbstractMongoSupport;
import org.jiumao.wechatMall.common.Assist;
import org.jiumao.wechatMall.dao.OauthRefreshTokenDao;
import org.jiumao.wechatMall.entity.OauthRefreshToken;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by 13682 on 2017/11/18.
 */
@Repository("oauthRefreshTokenDaoImpl")
public class OauthRefreshTokenDaoImpl extends AbstractMongoSupport implements OauthRefreshTokenDao{
    @Override
    public long getOauthRefreshTokenRowCount(Assist assist) {
        List<Assist.WhereRequire<?>> whereRequires = assist.getRequire();
        Query query = new Query();
        for(int i =0; i<whereRequires.size();i++){
            query.addCriteria(new Criteria(whereRequires.get(i).getRequire()).is(whereRequires.get(i).getValue()));
        }
        return  this.mongoTemplate().find(query,OauthRefreshToken.class).size();
    }

    @Override
    public List<OauthRefreshToken> selectOauthRefreshToken(Assist assist) {
        List<Assist.WhereRequire<?>> whereRequires = assist.getRequire();
        Query query = new Query();
        for(int i =0; i<whereRequires.size();i++){
            query.addCriteria(new Criteria(whereRequires.get(i).getRequire()).is(whereRequires.get(i).getValue()));
        }
        return this.mongoTemplate().find(query,OauthRefreshToken.class);
    }

    @Override
    public OauthRefreshToken selectOauthRefreshTokenByObj(OauthRefreshToken obj) {
        List<Map> valuelist = CommonUtils.getFiledNamesAndValues(obj);
        Query query = new Query();
        for(int i =0; i<valuelist.size();i++){
            query.addCriteria(new Criteria(valuelist.get(i).get("name").toString()).is(valuelist.get(i).get("value").toString()));
        }
        return this.mongoTemplate().findOne(query,OauthRefreshToken.class);
    }

    @Override
    public OauthRefreshToken selectOauthRefreshTokenById(Object id) {
        Query query = new Query(new Criteria("tokenId").is(id));
        return this.mongoTemplate().findOne(query,OauthRefreshToken.class);
    }

    @Override
    public int insertOauthRefreshToken(OauthRefreshToken value) {
        return 0;
    }

    @Override
    public int insertNonEmptyOauthRefreshToken(OauthRefreshToken value) {
        this.mongoTemplate().save(value);
        return Integer.getInteger(value.getTokenId());
    }

    @Override
    public long deleteOauthRefreshTokenById(Object id) {
        OauthRefreshToken oauthRefreshToken = selectOauthRefreshTokenById(id);
        return this.mongoTemplate().remove(oauthRefreshToken).getDeletedCount();
    }

    @Override
    public long deleteOauthRefreshToken(Assist assist) {
        List<Assist.WhereRequire<?>> whereRequires = assist.getRequire();
        Query query = new Query();
        for(int i =0; i<whereRequires.size();i++){
            query.addCriteria(new Criteria(whereRequires.get(i).getRequire()).is(whereRequires.get(i).getValue()));
        }
        return this.mongoTemplate().remove(query,OauthRefreshToken.class).getDeletedCount();
    }

    @Override
    public long updateOauthRefreshTokenById(OauthRefreshToken enti) {
        List<Map> valuelist = CommonUtils.getFiledNamesAndValues(enti);
        Update upd = new Update();
        for(int i =0; i<valuelist.size();i++){
            if((valuelist.get(i).get("name").toString()).equals("tokenId")){
                continue;
            }else{
                upd.set(valuelist.get(i).get("name").toString(),valuelist.get(i).get("value").toString());

            }
        }
        Query query = new Query(new Criteria("tokenId").is(enti.getTokenId()));
        UpdateResult updateResult = this.mongoTemplate().updateFirst(query,upd,OauthRefreshToken.class);
        return updateResult.getModifiedCount();
    }

    @Override
    public long updateOauthRefreshToken(OauthRefreshToken value, Assist assist) {
        List<Assist.WhereRequire<?>> whereRequires = assist.getRequire();
        Query query = new Query();
        for(int i =0; i<whereRequires.size();i++){
            query.addCriteria(new Criteria(whereRequires.get(i).getRequire()).is(whereRequires.get(i).getValue()));
        }
        List<Map> valuelist = CommonUtils.getFiledNamesAndValues(value);
        Update upd = new Update();
        for(int i =0; i<valuelist.size();i++){
            if((valuelist.get(i).get("name").toString()).equals("tokenId")){
                continue;
            }else{
                upd.set(valuelist.get(i).get("name").toString(),valuelist.get(i).get("value").toString());

            }
        }
        UpdateResult updateResult = this.mongoTemplate().updateFirst(query,upd,OauthRefreshToken.class);
        return updateResult.getModifiedCount();
    }

    @Override
    public long updateNonEmptyOauthRefreshTokenById(OauthRefreshToken enti) {
        List<Map> valuelist = CommonUtils.getFiledNamesAndValues(enti);
        Update upd = new Update();
        for(int i =0; i<valuelist.size();i++){
            if((valuelist.get(i).get("name").toString()).equals("tokenId")){
                continue;
            }else if("".equals(valuelist.get(i).get("value").toString())||"null".equals(valuelist.get(i).get("value").toString())){
                continue;
            }else{
                upd.set(valuelist.get(i).get("name").toString(),valuelist.get(i).get("value").toString());

            }
        }
        Query query = new Query(new Criteria("tokenId").is(enti.getTokenId()));
        UpdateResult updateResult = this.mongoTemplate().updateFirst(query,upd,OauthRefreshToken.class);
        return updateResult.getModifiedCount();
    }

    @Override
    public long updateNonEmptyOauthRefreshToken(OauthRefreshToken value, Assist assist) {
        List<Assist.WhereRequire<?>> whereRequires = assist.getRequire();
        Query query = new Query();
        for(int i =0; i<whereRequires.size();i++){
            query.addCriteria(new Criteria(whereRequires.get(i).getRequire()).is(whereRequires.get(i).getValue()));
        }
        List<Map> valuelist = CommonUtils.getFiledNamesAndValues(value);
        Update upd = new Update();
        for(int i =0; i<valuelist.size();i++){
            if((valuelist.get(i).get("name").toString()).equals("tokenId")){
                continue;
            }else if("".equals(valuelist.get(i).get("value").toString())||"null".equals(valuelist.get(i).get("value").toString())){
                continue;
            }else{
                upd.set(valuelist.get(i).get("name").toString(),valuelist.get(i).get("value").toString());

            }
        }
        UpdateResult updateResult = this.mongoTemplate().updateFirst(query,upd,OauthRefreshToken.class);
        return updateResult.getModifiedCount();
    }
}
