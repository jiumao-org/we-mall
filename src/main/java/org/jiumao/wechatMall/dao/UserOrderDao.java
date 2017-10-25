package org.jiumao.wechatMall.dao;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.jiumao.wechatMall.common.Assist;
import org.jiumao.wechatMall.entity.UserOrder;
public interface UserOrderDao{
	/**
	 * 获得UserOrder数据的总行数,可以通过辅助工具Assist进行条件查询,如果没有条件则传入null
	 * @param assist
	 * @return
	 */
    long getUserOrderRowCount(Assist assist);
	/**
	 * 获得UserOrder数据集合,可以通过辅助工具Assist进行条件查询,如果没有条件则传入null
	 * @param assist
	 * @return
	 */
    List<UserOrder> selectUserOrder(Assist assist);
	/**
	 * 获得一个UserOrder对象,以参数UserOrder对象中不为空的属性作为条件进行查询
	 * @param obj
	 * @return
	 */
    UserOrder selectUserOrderByObj(UserOrder obj);
	/**
	 * 通过UserOrder的id获得UserOrder对象
	 * @param id
	 * @return
	 */
    UserOrder selectUserOrderById(Object id);
	/**
	 * 插入UserOrder到数据库,包括null值
	 * @param value
	 * @return
	 */
    int insertUserOrder(UserOrder value);
	/**
	 * 插入UserOrder中属性值不为null的数据到数据库
	 * @param value
	 * @return
	 */
    int insertNonEmptyUserOrder(UserOrder value);
	/**
	 * 通过UserOrder的id删除UserOrder
	 * @param id
	 * @return
	 */
    int deleteUserOrderById(Object id);
	/**
	 * 通过辅助工具Assist的条件删除UserOrder
	 * @param assist
	 * @return
	 */
    int deleteUserOrder(Assist assist);
	/**
	 * 通过UserOrder的id更新UserOrder中的数据,包括null值
	 * @param enti
	 * @return
	 */
    int updateUserOrderById(UserOrder enti);
 	/**
	 * 通过辅助工具Assist的条件更新UserOrder中的数据,包括null值
	 * @param value
	 * @param assist
	 * @return
	 */
    int updateUserOrder(@Param("enti") UserOrder value, @Param("assist") Assist assist);
	/**
	 * 通过UserOrder的id更新UserOrder中属性不为null的数据
	 * @param enti
	 * @return
	 */
    int updateNonEmptyUserOrderById(UserOrder enti);
 	/**
	 * 通过辅助工具Assist的条件更新UserOrder中属性不为null的数据
	 * @param value
	 * @param assist
	 * @return
	 */
    int updateNonEmptyUserOrder(@Param("enti") UserOrder value, @Param("assist") Assist assist);
}