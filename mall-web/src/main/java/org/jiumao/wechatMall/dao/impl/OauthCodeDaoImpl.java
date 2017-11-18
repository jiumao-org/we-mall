package org.jiumao.wechatMall.dao.impl;

import org.jiumao.wechatMall.dao.OauthCodeDao;
import org.jiumao.wechatMall.entity.OauthCode;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;
import com.mongodb.client.result.UpdateResult;
import org.jiumao.common.utils.CommonUtils;
import org.jiumao.mall.db.mongodb.AbstractMongoSupport;
import org.jiumao.wechatMall.common.Assist;

import java.util.List;
import java.util.Map;

/**
 * Created by 13682 on 2017/11/18.
 */
@Repository("oauthCodeDaoImpl")
public class OauthCodeDaoImpl extends AbstractMongoSupport implements OauthCodeDao {
    @Override
    public long getOauthCodeRowCount(Assist assist) {
        List<Assist.WhereRequire<?>> whereRequires = assist.getRequire();
        Query query = new Query();
        for(int i =0; i<whereRequires.size();i++){
            query.addCriteria(new Criteria(whereRequires.get(i).getRequire()).is(whereRequires.get(i).getValue()));
        }
        return  this.mongoTemplate().find(query,OauthCode.class).size();
    }

    @Override
    public List<OauthCode> selectOauthCode(Assist assist) {
        List<Assist.WhereRequire<?>> whereRequires = assist.getRequire();
        Query query = new Query();
        for(int i =0; i<whereRequires.size();i++){
            query.addCriteria(new Criteria(whereRequires.get(i).getRequire()).is(whereRequires.get(i).getValue()));
        }
        return this.mongoTemplate().find(query,OauthCode.class);
    }

    @Override
    public OauthCode selectOauthCodeByObj(OauthCode obj) {
        List<Map> valuelist = CommonUtils.getFiledNamesAndValues(obj);
        Query query = new Query();
        for(int i =0; i<valuelist.size();i++){
            query.addCriteria(new Criteria(valuelist.get(i).get("name").toString()).is(valuelist.get(i).get("value").toString()));
        }
        return this.mongoTemplate().findOne(query,OauthCode.class);
    }

    @Override
    public OauthCode selectOauthCodeById(Object id) {
        Query query = new Query(new Criteria("code").is(id));
        return this.mongoTemplate().findOne(query,OauthCode.class);
    }

    @Override
    public int insertOauthCode(OauthCode value) {
        this.mongoTemplate().save(value);
        return Integer.getInteger(value.code());
    }

    @Override
    public int insertNonEmptyOauthCode(OauthCode value) {
        return 0;
    }

    @Override
    public long deleteOauthCodeById(Object id) {
        OauthCode oauthCode = selectOauthCodeById(id);
        return this.mongoTemplate().remove(oauthCode).getDeletedCount();
    }

    @Override
    public long deleteOauthCode(Assist assist) {
        List<Assist.WhereRequire<?>> whereRequires = assist.getRequire();
        Query query = new Query();
        for(int i =0; i<whereRequires.size();i++){
            query.addCriteria(new Criteria(whereRequires.get(i).getRequire()).is(whereRequires.get(i).getValue()));
        }
        return this.mongoTemplate().remove(query,OauthCode.class).getDeletedCount();
    }

    @Override
    public long updateOauthCodeById(OauthCode enti) {
        List<Map> valuelist = CommonUtils.getFiledNamesAndValues(enti);
        Update upd = new Update();
        for(int i =0; i<valuelist.size();i++){
            if(valuelist.get(i).get("name").equals("code")){
                continue;
            }else{
                upd.set(valuelist.get(i).get("name").toString(),valuelist.get(i).get("value").toString());

            }
        }
        Query query = new Query(new Criteria("code").is(enti.code()));
        UpdateResult updateResult = this.mongoTemplate().updateFirst(query,upd,OauthCode.class);
        return updateResult.getModifiedCount();
    }

    @Override
    public long updateOauthCode(OauthCode value, Assist assist) {
        List<Assist.WhereRequire<?>> whereRequires = assist.getRequire();
        Query query = new Query();
        for(int i =0; i<whereRequires.size();i++){
            query.addCriteria(new Criteria(whereRequires.get(i).getRequire()).is(whereRequires.get(i).getValue()));
        }
        List<Map> valuelist = CommonUtils.getFiledNamesAndValues(value);
        Update upd = new Update();
        for(int i =0; i<valuelist.size();i++){
            if(valuelist.get(i).get("name").equals("code")){
                continue;
            }else{
                upd.set(valuelist.get(i).get("name").toString(),valuelist.get(i).get("value").toString());

            }
        }
        UpdateResult updateResult = this.mongoTemplate().updateFirst(query,upd,OauthCode.class);
        return updateResult.getModifiedCount();
    }

    @Override
    public long updateNonEmptyOauthCodeById(OauthCode enti) {
        List<Map> valuelist = CommonUtils.getFiledNamesAndValues(enti);
        Update upd = new Update();
        for(int i =0; i<valuelist.size();i++){
            if("code".equals(valuelist.get(i).get("name").toString())){
                continue;
            }
            else if("".equals(valuelist.get(i).get("value").toString())||"null".equals(valuelist.get(i).get("value").toString())){
                continue;
            }else{
                upd.set(valuelist.get(i).get("name").toString(),valuelist.get(i).get("value").toString());
            }
        }
        Query query = new Query(new Criteria("code").is(enti.code()));
        UpdateResult updateResult = this.mongoTemplate().updateFirst(query,upd,OauthCode.class);
        return updateResult.getModifiedCount();
    }

    @Override
    public long updateNonEmptyOauthCode(OauthCode value, Assist assist) {
        List<Assist.WhereRequire<?>> whereRequires = assist.getRequire();
        Query query = new Query();
        for(int i =0; i<whereRequires.size();i++){
            query.addCriteria(new Criteria(whereRequires.get(i).getRequire()).is(whereRequires.get(i).getValue()));
        }
        List<Map> valuelist = CommonUtils.getFiledNamesAndValues(value);
        Update upd = new Update();
        for(int i =0; i<valuelist.size();i++){
            if(valuelist.get(i).get("name").equals("code")){
                continue;
            }else if("".equals(valuelist.get(i).get("value").toString())||"null".equals(valuelist.get(i).get("value").toString())){
                continue;
            }else{
                upd.set(valuelist.get(i).get("name").toString(),valuelist.get(i).get("value").toString());
            }
        }
        UpdateResult updateResult = this.mongoTemplate().updateFirst(query,upd,OauthCode.class);
        return updateResult.getModifiedCount();
    }
}
