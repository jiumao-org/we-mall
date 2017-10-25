package org.jiumao.wechatMall.dao;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.jiumao.wechatMall.common.Assist;
import org.jiumao.wechatMall.entity.ShopCart;
public interface ShopCartDao{
	/**
	 * 获得ShopCart数据的总行数,可以通过辅助工具Assist进行条件查询,如果没有条件则传入null
	 * @param assist
	 * @return
	 */
    long getShopCartRowCount(Assist assist);
	/**
	 * 获得ShopCart数据集合,可以通过辅助工具Assist进行条件查询,如果没有条件则传入null
	 * @param assist
	 * @return
	 */
    List<ShopCart> selectShopCart(Assist assist);
	/**
	 * 获得一个ShopCart对象,以参数ShopCart对象中不为空的属性作为条件进行查询
	 * @param obj
	 * @return
	 */
    ShopCart selectShopCartByObj(ShopCart obj);
	/**
	 * 通过ShopCart的id获得ShopCart对象
	 * @param id
	 * @return
	 */
    ShopCart selectShopCartById(Object id);
	/**
	 * 插入ShopCart到数据库,包括null值
	 * @param value
	 * @return
	 */
    int insertShopCart(ShopCart value);
	/**
	 * 插入ShopCart中属性值不为null的数据到数据库
	 * @param value
	 * @return
	 */
    int insertNonEmptyShopCart(ShopCart value);
	/**
	 * 通过ShopCart的id删除ShopCart
	 * @param id
	 * @return
	 */
    int deleteShopCartById(Object id);
	/**
	 * 通过辅助工具Assist的条件删除ShopCart
	 * @param assist
	 * @return
	 */
    int deleteShopCart(Assist assist);
	/**
	 * 通过ShopCart的id更新ShopCart中的数据,包括null值
	 * @param enti
	 * @return
	 */
    int updateShopCartById(ShopCart enti);
 	/**
	 * 通过辅助工具Assist的条件更新ShopCart中的数据,包括null值
	 * @param value
	 * @param assist
	 * @return
	 */
    int updateShopCart(@Param("enti") ShopCart value, @Param("assist") Assist assist);
	/**
	 * 通过ShopCart的id更新ShopCart中属性不为null的数据
	 * @param enti
	 * @return
	 */
    int updateNonEmptyShopCartById(ShopCart enti);
 	/**
	 * 通过辅助工具Assist的条件更新ShopCart中属性不为null的数据
	 * @param value
	 * @param assist
	 * @return
	 */
    int updateNonEmptyShopCart(@Param("enti") ShopCart value, @Param("assist") Assist assist);
}