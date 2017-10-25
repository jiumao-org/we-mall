package org.jiumao.wechatMall.service.Impl;
import java.util.List;

import org.jiumao.wechatMall.common.Assist;
import org.jiumao.wechatMall.dao.ShopCartDao;
import org.jiumao.wechatMall.entity.ShopCart;
import org.jiumao.wechatMall.service.ShopCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class ShopCartServiceImpl implements ShopCartService{
    @Autowired
    private ShopCartDao shopCartDao;
    @Override
    public long getShopCartRowCount(Assist assist){
        return shopCartDao.getShopCartRowCount(assist);
    }
    @Override
    public List<ShopCart> selectShopCart(Assist assist){
        return shopCartDao.selectShopCart(assist);
    }
    @Override
    public ShopCart selectShopCartByObj(ShopCart obj){
        return shopCartDao.selectShopCartByObj(obj);
    }
    @Override
    public ShopCart selectShopCartById(Object id){
        return shopCartDao.selectShopCartById(id);
    }
    @Override
    public int insertShopCart(ShopCart value){
        return shopCartDao.insertShopCart(value);
    }
    @Override
    public int insertNonEmptyShopCart(ShopCart value){
        return shopCartDao.insertNonEmptyShopCart(value);
    }
    @Override
    public int deleteShopCartById(Object id){
        return shopCartDao.deleteShopCartById(id);
    }
    @Override
    public int deleteShopCart(Assist assist){
        return shopCartDao.deleteShopCart(assist);
    }
    @Override
    public int updateShopCartById(ShopCart enti){
        return shopCartDao.updateShopCartById(enti);
    }
    @Override
    public int updateShopCart(ShopCart value, Assist assist){
        return shopCartDao.updateShopCart(value,assist);
    }
    @Override
    public int updateNonEmptyShopCartById(ShopCart enti){
        return shopCartDao.updateNonEmptyShopCartById(enti);
    }
    @Override
    public int updateNonEmptyShopCart(ShopCart value, Assist assist){
        return shopCartDao.updateNonEmptyShopCart(value,assist);
    }

    public ShopCartDao getShopCartDao() {
        return this.shopCartDao;
    }

    public void setShopCartDao(ShopCartDao shopCartDao) {
        this.shopCartDao = shopCartDao;
    }

}