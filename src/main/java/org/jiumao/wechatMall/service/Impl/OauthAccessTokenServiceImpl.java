package org.jiumao.wechatMall.service.Impl;
import java.util.List;
import org.jiumao.wechatMall.dao.OauthAccessTokenDao;
import org.jiumao.wechatMall.entity.OauthAccessToken;
import org.jiumao.wechatMall.common.Assist;
import org.jiumao.wechatMall.service.OauthAccessTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class OauthAccessTokenServiceImpl implements OauthAccessTokenService{
    @Autowired
    private OauthAccessTokenDao oauthAccessTokenDao;
    @Override
    public long getOauthAccessTokenRowCount(Assist assist){
        return oauthAccessTokenDao.getOauthAccessTokenRowCount(assist);
    }
    @Override
    public List<OauthAccessToken> selectOauthAccessToken(Assist assist){
        return oauthAccessTokenDao.selectOauthAccessToken(assist);
    }
    @Override
    public OauthAccessToken selectOauthAccessTokenByObj(OauthAccessToken obj){
        return oauthAccessTokenDao.selectOauthAccessTokenByObj(obj);
    }
    @Override
    public OauthAccessToken selectOauthAccessTokenById(Object id){
        return oauthAccessTokenDao.selectOauthAccessTokenById(id);
    }
    @Override
    public int insertOauthAccessToken(OauthAccessToken value){
        return oauthAccessTokenDao.insertOauthAccessToken(value);
    }
    @Override
    public int insertNonEmptyOauthAccessToken(OauthAccessToken value){
        return oauthAccessTokenDao.insertNonEmptyOauthAccessToken(value);
    }
    @Override
    public int deleteOauthAccessTokenById(Object id){
        return oauthAccessTokenDao.deleteOauthAccessTokenById(id);
    }
    @Override
    public int deleteOauthAccessToken(Assist assist){
        return oauthAccessTokenDao.deleteOauthAccessToken(assist);
    }
    @Override
    public int updateOauthAccessTokenById(OauthAccessToken enti){
        return oauthAccessTokenDao.updateOauthAccessTokenById(enti);
    }
    @Override
    public int updateOauthAccessToken(OauthAccessToken value, Assist assist){
        return oauthAccessTokenDao.updateOauthAccessToken(value,assist);
    }
    @Override
    public int updateNonEmptyOauthAccessTokenById(OauthAccessToken enti){
        return oauthAccessTokenDao.updateNonEmptyOauthAccessTokenById(enti);
    }
    @Override
    public int updateNonEmptyOauthAccessToken(OauthAccessToken value, Assist assist){
        return oauthAccessTokenDao.updateNonEmptyOauthAccessToken(value,assist);
    }

    public OauthAccessTokenDao getOauthAccessTokenDao() {
        return this.oauthAccessTokenDao;
    }

    public void setOauthAccessTokenDao(OauthAccessTokenDao oauthAccessTokenDao) {
        this.oauthAccessTokenDao = oauthAccessTokenDao;
    }

}