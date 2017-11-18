package org.jiumao.wechatMall.service.Impl;
import java.util.List;
import org.jiumao.wechatMall.dao.OauthCodeDao;
import org.jiumao.wechatMall.entity.OauthCode;
import org.jiumao.wechatMall.common.Assist;
import org.jiumao.wechatMall.service.OauthCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class OauthCodeServiceImpl implements OauthCodeService{
    @Autowired
    private OauthCodeDao oauthCodeDao;
    @Override
    public long getOauthCodeRowCount(Assist assist){
        return oauthCodeDao.getOauthCodeRowCount(assist);
    }
    @Override
    public List<OauthCode> selectOauthCode(Assist assist){
        return oauthCodeDao.selectOauthCode(assist);
    }
    @Override
    public OauthCode selectOauthCodeByObj(OauthCode obj){
        return oauthCodeDao.selectOauthCodeByObj(obj);
    }
    @Override
    public OauthCode selectOauthCodeById(Object id){
        return oauthCodeDao.selectOauthCodeById(id);
    }
    @Override
    public int insertOauthCode(OauthCode value){
        return oauthCodeDao.insertOauthCode(value);
    }
    @Override
    public int insertNonEmptyOauthCode(OauthCode value){
        return oauthCodeDao.insertNonEmptyOauthCode(value);
    }
    @Override
    public long deleteOauthCodeById(Object id){
        return oauthCodeDao.deleteOauthCodeById(id);
    }
    @Override
    public long deleteOauthCode(Assist assist){
        return oauthCodeDao.deleteOauthCode(assist);
    }
    @Override
    public long updateOauthCodeById(OauthCode enti){
        return oauthCodeDao.updateOauthCodeById(enti);
    }
    @Override
    public long updateOauthCode(OauthCode value, Assist assist){
        return oauthCodeDao.updateOauthCode(value,assist);
    }
    @Override
    public long updateNonEmptyOauthCodeById(OauthCode enti){
        return oauthCodeDao.updateNonEmptyOauthCodeById(enti);
    }
    @Override
    public long updateNonEmptyOauthCode(OauthCode value, Assist assist){
        return oauthCodeDao.updateNonEmptyOauthCode(value,assist);
    }

    public OauthCodeDao getOauthCodeDao() {
        return this.oauthCodeDao;
    }

    public void setOauthCodeDao(OauthCodeDao oauthCodeDao) {
        this.oauthCodeDao = oauthCodeDao;
    }

}