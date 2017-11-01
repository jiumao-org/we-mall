package org.jiumao.wechatMall.dao;
import org.jiumao.wechatMall.entity.ShopedList;
import java.util.List;
import org.jiumao.wechatMall.common.Assist;
import org.apache.ibatis.annotations.Param;
public interface ShopedListDao{
	/**
	 * 获得ShopedList数据的总行数,可以通过辅助工具Assist进行条件查询,如果没有条件则传入null
	 * @param assist
	 * @return
	 */
    long getShopedListRowCount(Assist assist);
	/**
	 * 获得ShopedList数据集合,可以通过辅助工具Assist进行条件查询,如果没有条件则传入null
	 * @param assist
	 * @return
	 */
    List<ShopedList> selectShopedList(Assist assist);
	/**
	 * 获得一个ShopedList对象,以参数ShopedList对象中不为空的属性作为条件进行查询
	 * @param obj
	 * @return
	 */
    ShopedList selectShopedListByObj(ShopedList obj);
	/**
	 * 通过ShopedList的id获得ShopedList对象
	 * @param id
	 * @return
	 */
    ShopedList selectShopedListById(Object id);
	/**
	 * 插入ShopedList到数据库,包括null值
	 * @param value
	 * @return
	 */
    int insertShopedList(ShopedList value);
	/**
	 * 插入ShopedList中属性值不为null的数据到数据库
	 * @param value
	 * @return
	 */
    int insertNonEmptyShopedList(ShopedList value);
	/**
	 * 通过ShopedList的id删除ShopedList
	 * @param id
	 * @return
	 */
    int deleteShopedListById(Object id);
	/**
	 * 通过辅助工具Assist的条件删除ShopedList
	 * @param assist
	 * @return
	 */
    int deleteShopedList(Assist assist);
	/**
	 * 通过ShopedList的id更新ShopedList中的数据,包括null值
	 * @param enti
	 * @return
	 */
    int updateShopedListById(ShopedList enti);
 	/**
	 * 通过辅助工具Assist的条件更新ShopedList中的数据,包括null值
	 * @param value
	 * @param assist
	 * @return
	 */
    int updateShopedList(@Param("enti") ShopedList value, @Param("assist") Assist assist);
	/**
	 * 通过ShopedList的id更新ShopedList中属性不为null的数据
	 * @param enti
	 * @return
	 */
    int updateNonEmptyShopedListById(ShopedList enti);
 	/**
	 * 通过辅助工具Assist的条件更新ShopedList中属性不为null的数据
	 * @param value
	 * @param assist
	 * @return
	 */
    int updateNonEmptyShopedList(@Param("enti") ShopedList value, @Param("assist") Assist assist);
}