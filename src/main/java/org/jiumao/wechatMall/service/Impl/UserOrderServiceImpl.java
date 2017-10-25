package org.jiumao.wechatMall.service.Impl;
import java.util.List;

import org.jiumao.wechatMall.common.Assist;
import org.jiumao.wechatMall.dao.UserOrderDao;
import org.jiumao.wechatMall.entity.UserOrder;
import org.jiumao.wechatMall.service.UserOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class UserOrderServiceImpl implements UserOrderService{
    @Autowired
    private UserOrderDao userOrderDao;
    @Override
    public long getUserOrderRowCount(Assist assist){
        return userOrderDao.getUserOrderRowCount(assist);
    }
    @Override
    public List<UserOrder> selectUserOrder(Assist assist){
        return userOrderDao.selectUserOrder(assist);
    }
    @Override
    public UserOrder selectUserOrderByObj(UserOrder obj){
        return userOrderDao.selectUserOrderByObj(obj);
    }
    @Override
    public UserOrder selectUserOrderById(Object id){
        return userOrderDao.selectUserOrderById(id);
    }
    @Override
    public int insertUserOrder(UserOrder value){
        return userOrderDao.insertUserOrder(value);
    }
    @Override
    public int insertNonEmptyUserOrder(UserOrder value){
        return userOrderDao.insertNonEmptyUserOrder(value);
    }
    @Override
    public int deleteUserOrderById(Object id){
        return userOrderDao.deleteUserOrderById(id);
    }
    @Override
    public int deleteUserOrder(Assist assist){
        return userOrderDao.deleteUserOrder(assist);
    }
    @Override
    public int updateUserOrderById(UserOrder enti){
        return userOrderDao.updateUserOrderById(enti);
    }
    @Override
    public int updateUserOrder(UserOrder value, Assist assist){
        return userOrderDao.updateUserOrder(value,assist);
    }
    @Override
    public int updateNonEmptyUserOrderById(UserOrder enti){
        return userOrderDao.updateNonEmptyUserOrderById(enti);
    }
    @Override
    public int updateNonEmptyUserOrder(UserOrder value, Assist assist){
        return userOrderDao.updateNonEmptyUserOrder(value,assist);
    }

    public UserOrderDao getUserOrderDao() {
        return this.userOrderDao;
    }

    public void setUserOrderDao(UserOrderDao userOrderDao) {
        this.userOrderDao = userOrderDao;
    }

}