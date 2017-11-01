package org.jiumao.wechatMall.dao;
import org.jiumao.wechatMall.entity.Order;
import java.util.List;
import org.jiumao.wechatMall.common.Assist;
import org.apache.ibatis.annotations.Param;
public interface OrderDao{
	/**
	 * 获得Order数据的总行数,可以通过辅助工具Assist进行条件查询,如果没有条件则传入null
	 * @param assist
	 * @return
	 */
    long getOrderRowCount(Assist assist);
	/**
	 * 获得Order数据集合,可以通过辅助工具Assist进行条件查询,如果没有条件则传入null
	 * @param assist
	 * @return
	 */
    List<Order> selectOrder(Assist assist);
	/**
	 * 获得一个Order对象,以参数Order对象中不为空的属性作为条件进行查询
	 * @param obj
	 * @return
	 */
    Order selectOrderByObj(Order obj);
	/**
	 * 通过Order的id获得Order对象
	 * @param id
	 * @return
	 */
    Order selectOrderById(Object id);
	/**
	 * 插入Order到数据库,包括null值
	 * @param value
	 * @return
	 */
    int insertOrder(Order value);
	/**
	 * 插入Order中属性值不为null的数据到数据库
	 * @param value
	 * @return
	 */
    int insertNonEmptyOrder(Order value);
	/**
	 * 通过Order的id删除Order
	 * @param id
	 * @return
	 */
    int deleteOrderById(Object id);
	/**
	 * 通过辅助工具Assist的条件删除Order
	 * @param assist
	 * @return
	 */
    int deleteOrder(Assist assist);
	/**
	 * 通过Order的id更新Order中的数据,包括null值
	 * @param enti
	 * @return
	 */
    int updateOrderById(Order enti);
 	/**
	 * 通过辅助工具Assist的条件更新Order中的数据,包括null值
	 * @param value
	 * @param assist
	 * @return
	 */
    int updateOrder(@Param("enti") Order value, @Param("assist") Assist assist);
	/**
	 * 通过Order的id更新Order中属性不为null的数据
	 * @param enti
	 * @return
	 */
    int updateNonEmptyOrderById(Order enti);
 	/**
	 * 通过辅助工具Assist的条件更新Order中属性不为null的数据
	 * @param value
	 * @param assist
	 * @return
	 */
    int updateNonEmptyOrder(@Param("enti") Order value, @Param("assist") Assist assist);
}