package org.jiumao.wechatMall.service.Impl;
import java.util.List;
import org.jiumao.wechatMall.dao.GiftCardDao;
import org.jiumao.wechatMall.entity.GiftCard;
import org.jiumao.wechatMall.common.Assist;
import org.jiumao.wechatMall.service.GiftCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class GiftCardServiceImpl implements GiftCardService{
    @Autowired
    private GiftCardDao giftCardDao;
    @Override
    public long getGiftCardRowCount(Assist assist){
        return giftCardDao.getGiftCardRowCount(assist);
    }
    @Override
    public List<GiftCard> selectGiftCard(Assist assist){
        return giftCardDao.selectGiftCard(assist);
    }
    @Override
    public GiftCard selectGiftCardByObj(GiftCard obj){
        return giftCardDao.selectGiftCardByObj(obj);
    }
    @Override
    public GiftCard selectGiftCardById(Long id){
        return giftCardDao.selectGiftCardById(id);
    }
    @Override
    public int insertGiftCard(GiftCard value){
        return giftCardDao.insertGiftCard(value);
    }
    @Override
    public int insertNonEmptyGiftCard(GiftCard value){
        return giftCardDao.insertNonEmptyGiftCard(value);
    }
    @Override
    public int deleteGiftCardById(Long id){
        return giftCardDao.deleteGiftCardById(id);
    }
    @Override
    public int deleteGiftCard(Assist assist){
        return giftCardDao.deleteGiftCard(assist);
    }
    @Override
    public int updateGiftCardById(GiftCard enti){
        return giftCardDao.updateGiftCardById(enti);
    }
    @Override
    public int updateGiftCard(GiftCard value, Assist assist){
        return giftCardDao.updateGiftCard(value,assist);
    }
    @Override
    public int updateNonEmptyGiftCardById(GiftCard enti){
        return giftCardDao.updateNonEmptyGiftCardById(enti);
    }
    @Override
    public int updateNonEmptyGiftCard(GiftCard value, Assist assist){
        return giftCardDao.updateNonEmptyGiftCard(value,assist);
    }

    public GiftCardDao getGiftCardDao() {
        return this.giftCardDao;
    }

    public void setGiftCardDao(GiftCardDao giftCardDao) {
        this.giftCardDao = giftCardDao;
    }

}