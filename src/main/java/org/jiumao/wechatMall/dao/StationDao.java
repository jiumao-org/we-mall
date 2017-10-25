package org.jiumao.wechatMall.dao;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.jiumao.wechatMall.common.Assist;
import org.jiumao.wechatMall.entity.Station;
public interface StationDao{
	/**
	 * 获得Station数据的总行数,可以通过辅助工具Assist进行条件查询,如果没有条件则传入null
	 * @param assist
	 * @return
	 */
    long getStationRowCount(Assist assist);
	/**
	 * 获得Station数据集合,可以通过辅助工具Assist进行条件查询,如果没有条件则传入null
	 * @param assist
	 * @return
	 */
    List<Station> selectStation(Assist assist);
	/**
	 * 获得一个Station对象,以参数Station对象中不为空的属性作为条件进行查询
	 * @param obj
	 * @return
	 */
    Station selectStationByObj(Station obj);
	/**
	 * 通过Station的id获得Station对象
	 * @param id
	 * @return
	 */
    Station selectStationById(Integer id);
	/**
	 * 插入Station到数据库,包括null值
	 * @param value
	 * @return
	 */
    int insertStation(Station value);
	/**
	 * 插入Station中属性值不为null的数据到数据库
	 * @param value
	 * @return
	 */
    int insertNonEmptyStation(Station value);
	/**
	 * 通过Station的id删除Station
	 * @param id
	 * @return
	 */
    int deleteStationById(Integer id);
	/**
	 * 通过辅助工具Assist的条件删除Station
	 * @param assist
	 * @return
	 */
    int deleteStation(Assist assist);
	/**
	 * 通过Station的id更新Station中的数据,包括null值
	 * @param enti
	 * @return
	 */
    int updateStationById(Station enti);
 	/**
	 * 通过辅助工具Assist的条件更新Station中的数据,包括null值
	 * @param value
	 * @param assist
	 * @return
	 */
    int updateStation(@Param("enti") Station value, @Param("assist") Assist assist);
	/**
	 * 通过Station的id更新Station中属性不为null的数据
	 * @param enti
	 * @return
	 */
    int updateNonEmptyStationById(Station enti);
 	/**
	 * 通过辅助工具Assist的条件更新Station中属性不为null的数据
	 * @param value
	 * @param assist
	 * @return
	 */
    int updateNonEmptyStation(@Param("enti") Station value, @Param("assist") Assist assist);
}