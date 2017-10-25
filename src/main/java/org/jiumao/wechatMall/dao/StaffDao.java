package org.jiumao.wechatMall.dao;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.jiumao.wechatMall.common.Assist;
import org.jiumao.wechatMall.entity.Staff;
public interface StaffDao{
	/**
	 * 获得Staff数据的总行数,可以通过辅助工具Assist进行条件查询,如果没有条件则传入null
	 * @param assist
	 * @return
	 */
    long getStaffRowCount(Assist assist);
	/**
	 * 获得Staff数据集合,可以通过辅助工具Assist进行条件查询,如果没有条件则传入null
	 * @param assist
	 * @return
	 */
    List<Staff> selectStaff(Assist assist);
	/**
	 * 获得一个Staff对象,以参数Staff对象中不为空的属性作为条件进行查询
	 * @param obj
	 * @return
	 */
    Staff selectStaffByObj(Staff obj);
	/**
	 * 通过Staff的id获得Staff对象
	 * @param id
	 * @return
	 */
    Staff selectStaffById(Object id);
	/**
	 * 插入Staff到数据库,包括null值
	 * @param value
	 * @return
	 */
    int insertStaff(Staff value);
	/**
	 * 插入Staff中属性值不为null的数据到数据库
	 * @param value
	 * @return
	 */
    int insertNonEmptyStaff(Staff value);
	/**
	 * 通过Staff的id删除Staff
	 * @param id
	 * @return
	 */
    int deleteStaffById(Object id);
	/**
	 * 通过辅助工具Assist的条件删除Staff
	 * @param assist
	 * @return
	 */
    int deleteStaff(Assist assist);
	/**
	 * 通过Staff的id更新Staff中的数据,包括null值
	 * @param enti
	 * @return
	 */
    int updateStaffById(Staff enti);
 	/**
	 * 通过辅助工具Assist的条件更新Staff中的数据,包括null值
	 * @param value
	 * @param assist
	 * @return
	 */
    int updateStaff(@Param("enti") Staff value, @Param("assist") Assist assist);
	/**
	 * 通过Staff的id更新Staff中属性不为null的数据
	 * @param enti
	 * @return
	 */
    int updateNonEmptyStaffById(Staff enti);
 	/**
	 * 通过辅助工具Assist的条件更新Staff中属性不为null的数据
	 * @param value
	 * @param assist
	 * @return
	 */
    int updateNonEmptyStaff(@Param("enti") Staff value, @Param("assist") Assist assist);
}