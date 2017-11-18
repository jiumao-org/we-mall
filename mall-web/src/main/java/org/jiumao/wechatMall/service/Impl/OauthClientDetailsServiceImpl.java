package org.jiumao.wechatMall.service.Impl;
import java.util.List;
import org.jiumao.wechatMall.dao.OauthClientDetailsDao;
import org.jiumao.wechatMall.entity.OauthClientDetails;
import org.jiumao.wechatMall.common.Assist;
import org.jiumao.wechatMall.service.OauthClientDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class OauthClientDetailsServiceImpl implements OauthClientDetailsService{
    @Autowired
    private OauthClientDetailsDao oauthClientDetailsDao;
    @Override
    public long getOauthClientDetailsRowCount(Assist assist){
        return oauthClientDetailsDao.getOauthClientDetailsRowCount(assist);
    }
    @Override
    public List<OauthClientDetails> selectOauthClientDetails(Assist assist){
        return oauthClientDetailsDao.selectOauthClientDetails(assist);
    }
    @Override
    public OauthClientDetails selectOauthClientDetailsByObj(OauthClientDetails obj){
        return oauthClientDetailsDao.selectOauthClientDetailsByObj(obj);
    }
    @Override
    public OauthClientDetails selectOauthClientDetailsById(String id){
        return oauthClientDetailsDao.selectOauthClientDetailsById(id);
    }
    @Override
    public int insertOauthClientDetails(OauthClientDetails value){
        return oauthClientDetailsDao.insertOauthClientDetails(value);
    }
    @Override
    public int insertNonEmptyOauthClientDetails(OauthClientDetails value){
        return oauthClientDetailsDao.insertNonEmptyOauthClientDetails(value);
    }
    @Override
    public long deleteOauthClientDetailsById(String id){
        return oauthClientDetailsDao.deleteOauthClientDetailsById(id);
    }
    @Override
    public long deleteOauthClientDetails(Assist assist){
        return oauthClientDetailsDao.deleteOauthClientDetails(assist);
    }
    @Override
    public long updateOauthClientDetailsById(OauthClientDetails enti){
        return oauthClientDetailsDao.updateOauthClientDetailsById(enti);
    }
    @Override
    public long updateOauthClientDetails(OauthClientDetails value, Assist assist){
        return oauthClientDetailsDao.updateOauthClientDetails(value,assist);
    }
    @Override
    public long updateNonEmptyOauthClientDetailsById(OauthClientDetails enti){
        return oauthClientDetailsDao.updateNonEmptyOauthClientDetailsById(enti);
    }
    @Override
    public long updateNonEmptyOauthClientDetails(OauthClientDetails value, Assist assist){
        return oauthClientDetailsDao.updateNonEmptyOauthClientDetails(value,assist);
    }

    public OauthClientDetailsDao getOauthClientDetailsDao() {
        return this.oauthClientDetailsDao;
    }

    public void setOauthClientDetailsDao(OauthClientDetailsDao oauthClientDetailsDao) {
        this.oauthClientDetailsDao = oauthClientDetailsDao;
    }

}