package org.jiumao.wechatMall.service;
import java.util.List;
import org.jiumao.wechatMall.dao.OrderGoodsDao;
import org.jiumao.wechatMall.entity.OrderGoods;
import org.jiumao.wechatMall.common.Assist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class OrderGoodsServiceImpl implements OrderGoodsService{
    @Autowired
    private OrderGoodsDao orderGoodsDao;
    @Override
    public long getOrderGoodsRowCount(Assist assist){
        return orderGoodsDao.getOrderGoodsRowCount(assist);
    }
    @Override
    public List<OrderGoods> selectOrderGoods(Assist assist){
        return orderGoodsDao.selectOrderGoods(assist);
    }
    @Override
    public OrderGoods selectOrderGoodsByObj(OrderGoods obj){
        return orderGoodsDao.selectOrderGoodsByObj(obj);
    }
    @Override
    public OrderGoods selectOrderGoodsById(Object id){
        return orderGoodsDao.selectOrderGoodsById(id);
    }
    @Override
    public int insertOrderGoods(OrderGoods value){
        return orderGoodsDao.insertOrderGoods(value);
    }
    @Override
    public int insertNonEmptyOrderGoods(OrderGoods value){
        return orderGoodsDao.insertNonEmptyOrderGoods(value);
    }
    @Override
    public int deleteOrderGoodsById(Object id){
        return orderGoodsDao.deleteOrderGoodsById(id);
    }
    @Override
    public int deleteOrderGoods(Assist assist){
        return orderGoodsDao.deleteOrderGoods(assist);
    }
    @Override
    public int updateOrderGoodsById(OrderGoods enti){
        return orderGoodsDao.updateOrderGoodsById(enti);
    }
    @Override
    public int updateOrderGoods(OrderGoods value, Assist assist){
        return orderGoodsDao.updateOrderGoods(value,assist);
    }
    @Override
    public int updateNonEmptyOrderGoodsById(OrderGoods enti){
        return orderGoodsDao.updateNonEmptyOrderGoodsById(enti);
    }
    @Override
    public int updateNonEmptyOrderGoods(OrderGoods value, Assist assist){
        return orderGoodsDao.updateNonEmptyOrderGoods(value,assist);
    }

    public OrderGoodsDao getOrderGoodsDao() {
        return this.orderGoodsDao;
    }

    public void setOrderGoodsDao(OrderGoodsDao orderGoodsDao) {
        this.orderGoodsDao = orderGoodsDao;
    }

}