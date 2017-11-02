package org.jiumao.wechatMall.service.Impl;
import java.util.List;
import org.jiumao.wechatMall.dao.GoodsDao;
import org.jiumao.wechatMall.entity.Goods;
import org.jiumao.wechatMall.common.Assist;
import org.jiumao.wechatMall.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class GoodsServiceImpl implements GoodsService{
    @Autowired
    private GoodsDao goodsDao;
    @Override
    public long getGoodsRowCount(Assist assist){
        return goodsDao.getGoodsRowCount(assist);
    }
    @Override
    public List<Goods> selectGoods(Assist assist){
        return goodsDao.selectGoods(assist);
    }
    @Override
    public Goods selectGoodsByObj(Goods obj){
        return goodsDao.selectGoodsByObj(obj);
    }
    @Override
    public Goods selectGoodsById(Object id){
        return goodsDao.selectGoodsById(id);
    }
    @Override
    public int insertGoods(Goods value){
        return goodsDao.insertGoods(value);
    }
    @Override
    public int insertNonEmptyGoods(Goods value){
        return goodsDao.insertNonEmptyGoods(value);
    }
    @Override
    public int deleteGoodsById(Object id){
        return goodsDao.deleteGoodsById(id);
    }
    @Override
    public int deleteGoods(Assist assist){
        return goodsDao.deleteGoods(assist);
    }
    @Override
    public int updateGoodsById(Goods enti){
        return goodsDao.updateGoodsById(enti);
    }
    @Override
    public int updateGoods(Goods value, Assist assist){
        return goodsDao.updateGoods(value,assist);
    }
    @Override
    public int updateNonEmptyGoodsById(Goods enti){
        return goodsDao.updateNonEmptyGoodsById(enti);
    }
    @Override
    public int updateNonEmptyGoods(Goods value, Assist assist){
        return goodsDao.updateNonEmptyGoods(value,assist);
    }

    public GoodsDao getGoodsDao() {
        return this.goodsDao;
    }

    public void setGoodsDao(GoodsDao goodsDao) {
        this.goodsDao = goodsDao;
    }

}