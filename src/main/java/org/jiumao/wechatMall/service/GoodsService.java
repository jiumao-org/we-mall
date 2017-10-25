package org.jiumao.wechatMall.service;
import java.util.List;

import org.jiumao.wechatMall.common.Assist;
import org.jiumao.wechatMall.entity.Goods;
public interface GoodsService{
	/**
	 * 获得Goods数据的总行数,可以通过辅助工具Assist进行条件查询,如果没有条件则传入null
	 * @param assist
	 * @return
	 */
    long getGoodsRowCount(Assist assist);
	/**
	 * 获得Goods数据集合,可以通过辅助工具Assist进行条件查询,如果没有条件则传入null
	 * @param assist
	 * @return
	 */
    List<Goods> selectGoods(Assist assist);
	/**
	 * 获得一个Goods对象,以参数Goods对象中不为空的属性作为条件进行查询
	 * @param obj
	 * @return
	 */
    Goods selectGoodsByObj(Goods obj);
	/**
	 * 通过Goods的id获得Goods对象
	 * @param id
	 * @return
	 */
    Goods selectGoodsById(Object id);
	/**
	 * 插入Goods到数据库,包括null值
	 * @param value
	 * @return
	 */
    int insertGoods(Goods value);
	/**
	 * 插入Goods中属性值不为null的数据到数据库
	 * @param value
	 * @return
	 */
    int insertNonEmptyGoods(Goods value);
	/**
	 * 通过Goods的id删除Goods
	 * @param id
	 * @return
	 */
    int deleteGoodsById(Object id);
	/**
	 * 通过辅助工具Assist的条件删除Goods
	 * @param assist
	 * @return
	 */
    int deleteGoods(Assist assist);
	/**
	 * 通过Goods的id更新Goods中的数据,包括null值
	 * @param enti
	 * @return
	 */
    int updateGoodsById(Goods enti);
 	/**
	 * 通过辅助工具Assist的条件更新Goods中的数据,包括null值
	 * @param value
	 * @param assist
	 * @return
	 */
    int updateGoods(Goods value,  Assist assist);
	/**
	 * 通过Goods的id更新Goods中属性不为null的数据
	 * @param enti
	 * @return
	 */
    int updateNonEmptyGoodsById(Goods enti);
 	/**
	 * 通过辅助工具Assist的条件更新Goods中属性不为null的数据
	 * @param value
	 * @param assist
	 * @return
	 */
    int updateNonEmptyGoods(Goods value, Assist assist);
}