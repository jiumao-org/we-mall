package org.jiumao.wechatMall.service.Impl;
import java.util.List;
import org.jiumao.wechatMall.dao.OauthRefreshTokenDao;
import org.jiumao.wechatMall.entity.OauthRefreshToken;
import org.jiumao.wechatMall.common.Assist;
import org.jiumao.wechatMall.service.OauthRefreshTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class OauthRefreshTokenServiceImpl implements OauthRefreshTokenService{
    @Autowired
    private OauthRefreshTokenDao oauthRefreshTokenDao;
    @Override
    public long getOauthRefreshTokenRowCount(Assist assist){
        return oauthRefreshTokenDao.getOauthRefreshTokenRowCount(assist);
    }
    @Override
    public List<OauthRefreshToken> selectOauthRefreshToken(Assist assist){
        return oauthRefreshTokenDao.selectOauthRefreshToken(assist);
    }
    @Override
    public OauthRefreshToken selectOauthRefreshTokenByObj(OauthRefreshToken obj){
        return oauthRefreshTokenDao.selectOauthRefreshTokenByObj(obj);
    }
    @Override
    public OauthRefreshToken selectOauthRefreshTokenById(Object id){
        return oauthRefreshTokenDao.selectOauthRefreshTokenById(id);
    }
    @Override
    public int insertOauthRefreshToken(OauthRefreshToken value){
        return oauthRefreshTokenDao.insertOauthRefreshToken(value);
    }
    @Override
    public int insertNonEmptyOauthRefreshToken(OauthRefreshToken value){
        return oauthRefreshTokenDao.insertNonEmptyOauthRefreshToken(value);
    }
    @Override
    public int deleteOauthRefreshTokenById(Object id){
        return oauthRefreshTokenDao.deleteOauthRefreshTokenById(id);
    }
    @Override
    public int deleteOauthRefreshToken(Assist assist){
        return oauthRefreshTokenDao.deleteOauthRefreshToken(assist);
    }
    @Override
    public int updateOauthRefreshTokenById(OauthRefreshToken enti){
        return oauthRefreshTokenDao.updateOauthRefreshTokenById(enti);
    }
    @Override
    public int updateOauthRefreshToken(OauthRefreshToken value, Assist assist){
        return oauthRefreshTokenDao.updateOauthRefreshToken(value,assist);
    }
    @Override
    public int updateNonEmptyOauthRefreshTokenById(OauthRefreshToken enti){
        return oauthRefreshTokenDao.updateNonEmptyOauthRefreshTokenById(enti);
    }
    @Override
    public int updateNonEmptyOauthRefreshToken(OauthRefreshToken value, Assist assist){
        return oauthRefreshTokenDao.updateNonEmptyOauthRefreshToken(value,assist);
    }

    public OauthRefreshTokenDao getOauthRefreshTokenDao() {
        return this.oauthRefreshTokenDao;
    }

    public void setOauthRefreshTokenDao(OauthRefreshTokenDao oauthRefreshTokenDao) {
        this.oauthRefreshTokenDao = oauthRefreshTokenDao;
    }

}