package org.jiumao.wechatMall.service;
import java.util.List;

import org.jiumao.wechatMall.common.Assist;
import org.jiumao.wechatMall.entity.OrderGoods;
public interface OrderGoodsService{
	/**
	 * 获得OrderGoods数据的总行数,可以通过辅助工具Assist进行条件查询,如果没有条件则传入null
	 * @param assist
	 * @return
	 */
    long getOrderGoodsRowCount(Assist assist);
	/**
	 * 获得OrderGoods数据集合,可以通过辅助工具Assist进行条件查询,如果没有条件则传入null
	 * @param assist
	 * @return
	 */
    List<OrderGoods> selectOrderGoods(Assist assist);
	/**
	 * 获得一个OrderGoods对象,以参数OrderGoods对象中不为空的属性作为条件进行查询
	 * @param obj
	 * @return
	 */
    OrderGoods selectOrderGoodsByObj(OrderGoods obj);
	/**
	 * 通过OrderGoods的id获得OrderGoods对象
	 * @param id
	 * @return
	 */
    OrderGoods selectOrderGoodsById(Object id);
	/**
	 * 插入OrderGoods到数据库,包括null值
	 * @param value
	 * @return
	 */
    int insertOrderGoods(OrderGoods value);
	/**
	 * 插入OrderGoods中属性值不为null的数据到数据库
	 * @param value
	 * @return
	 */
    int insertNonEmptyOrderGoods(OrderGoods value);
	/**
	 * 通过OrderGoods的id删除OrderGoods
	 * @param id
	 * @return
	 */
    int deleteOrderGoodsById(Object id);
	/**
	 * 通过辅助工具Assist的条件删除OrderGoods
	 * @param assist
	 * @return
	 */
    int deleteOrderGoods(Assist assist);
	/**
	 * 通过OrderGoods的id更新OrderGoods中的数据,包括null值
	 * @param enti
	 * @return
	 */
    int updateOrderGoodsById(OrderGoods enti);
 	/**
	 * 通过辅助工具Assist的条件更新OrderGoods中的数据,包括null值
	 * @param value
	 * @param assist
	 * @return
	 */
    int updateOrderGoods(OrderGoods value,  Assist assist);
	/**
	 * 通过OrderGoods的id更新OrderGoods中属性不为null的数据
	 * @param enti
	 * @return
	 */
    int updateNonEmptyOrderGoodsById(OrderGoods enti);
 	/**
	 * 通过辅助工具Assist的条件更新OrderGoods中属性不为null的数据
	 * @param value
	 * @param assist
	 * @return
	 */
    int updateNonEmptyOrderGoods(OrderGoods value, Assist assist);
}