package org.jiumao.wechatMall.service.Impl;
import java.util.List;
import org.jiumao.wechatMall.dao.OrderDao;
import org.jiumao.wechatMall.entity.Order;
import org.jiumao.wechatMall.common.Assist;
import org.jiumao.wechatMall.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class OrderServiceImpl implements OrderService{
    @Autowired
    private OrderDao orderDao;
    @Override
    public long getOrderRowCount(Assist assist){
        return orderDao.getOrderRowCount(assist);
    }
    @Override
    public List<Order> selectOrder(Assist assist){
        return orderDao.selectOrder(assist);
    }
    @Override
    public Order selectOrderByObj(Order obj){
        return orderDao.selectOrderByObj(obj);
    }
    @Override
    public Order selectOrderById(Object id){
        return orderDao.selectOrderById(id);
    }
    @Override
    public int insertOrder(Order value){
        return orderDao.insertOrder(value);
    }
    @Override
    public int insertNonEmptyOrder(Order value){
        return orderDao.insertNonEmptyOrder(value);
    }
    @Override
    public int deleteOrderById(Object id){
        return orderDao.deleteOrderById(id);
    }
    @Override
    public int deleteOrder(Assist assist){
        return orderDao.deleteOrder(assist);
    }
    @Override
    public int updateOrderById(Order enti){
        return orderDao.updateOrderById(enti);
    }
    @Override
    public int updateOrder(Order value, Assist assist){
        return orderDao.updateOrder(value,assist);
    }
    @Override
    public int updateNonEmptyOrderById(Order enti){
        return orderDao.updateNonEmptyOrderById(enti);
    }
    @Override
    public int updateNonEmptyOrder(Order value, Assist assist){
        return orderDao.updateNonEmptyOrder(value,assist);
    }

    public OrderDao getOrderDao() {
        return this.orderDao;
    }

    public void setOrderDao(OrderDao orderDao) {
        this.orderDao = orderDao;
    }

}