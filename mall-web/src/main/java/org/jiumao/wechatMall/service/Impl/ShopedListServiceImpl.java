package org.jiumao.wechatMall.service.Impl;
import java.util.List;
import org.jiumao.wechatMall.dao.ShopedListDao;
import org.jiumao.wechatMall.entity.ShopedList;
import org.jiumao.wechatMall.common.Assist;
import org.jiumao.wechatMall.service.ShopedListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class ShopedListServiceImpl implements ShopedListService{
    @Autowired
    private ShopedListDao shopedListDao;
    @Override
    public long getShopedListRowCount(Assist assist){
        return shopedListDao.getShopedListRowCount(assist);
    }
    @Override
    public List<ShopedList> selectShopedList(Assist assist){
        return shopedListDao.selectShopedList(assist);
    }
    @Override
    public ShopedList selectShopedListByObj(ShopedList obj){
        return shopedListDao.selectShopedListByObj(obj);
    }
    @Override
    public ShopedList selectShopedListById(Object id){
        return shopedListDao.selectShopedListById(id);
    }
    @Override
    public int insertShopedList(ShopedList value){
        return shopedListDao.insertShopedList(value);
    }
    @Override
    public int insertNonEmptyShopedList(ShopedList value){
        return shopedListDao.insertNonEmptyShopedList(value);
    }
    @Override
    public int deleteShopedListById(Object id){
        return shopedListDao.deleteShopedListById(id);
    }
    @Override
    public int deleteShopedList(Assist assist){
        return shopedListDao.deleteShopedList(assist);
    }
    @Override
    public int updateShopedListById(ShopedList enti){
        return shopedListDao.updateShopedListById(enti);
    }
    @Override
    public int updateShopedList(ShopedList value, Assist assist){
        return shopedListDao.updateShopedList(value,assist);
    }
    @Override
    public int updateNonEmptyShopedListById(ShopedList enti){
        return shopedListDao.updateNonEmptyShopedListById(enti);
    }
    @Override
    public int updateNonEmptyShopedList(ShopedList value, Assist assist){
        return shopedListDao.updateNonEmptyShopedList(value,assist);
    }

    public ShopedListDao getShopedListDao() {
        return this.shopedListDao;
    }

    public void setShopedListDao(ShopedListDao shopedListDao) {
        this.shopedListDao = shopedListDao;
    }

}