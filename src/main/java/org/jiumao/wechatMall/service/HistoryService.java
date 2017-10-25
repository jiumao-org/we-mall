package org.jiumao.wechatMall.service;
import java.util.List;

import org.jiumao.wechatMall.common.Assist;
import org.jiumao.wechatMall.entity.History;
public interface HistoryService{
	/**
	 * 获得History数据的总行数,可以通过辅助工具Assist进行条件查询,如果没有条件则传入null
	 * @param assist
	 * @return
	 */
    long getHistoryRowCount(Assist assist);
	/**
	 * 获得History数据集合,可以通过辅助工具Assist进行条件查询,如果没有条件则传入null
	 * @param assist
	 * @return
	 */
    List<History> selectHistory(Assist assist);
	/**
	 * 获得一个History对象,以参数History对象中不为空的属性作为条件进行查询
	 * @param obj
	 * @return
	 */
    History selectHistoryByObj(History obj);
	/**
	 * 通过History的id获得History对象
	 * @param id
	 * @return
	 */
    History selectHistoryById(Object id);
	/**
	 * 插入History到数据库,包括null值
	 * @param value
	 * @return
	 */
    int insertHistory(History value);
	/**
	 * 插入History中属性值不为null的数据到数据库
	 * @param value
	 * @return
	 */
    int insertNonEmptyHistory(History value);
	/**
	 * 通过History的id删除History
	 * @param id
	 * @return
	 */
    int deleteHistoryById(Object id);
	/**
	 * 通过辅助工具Assist的条件删除History
	 * @param assist
	 * @return
	 */
    int deleteHistory(Assist assist);
	/**
	 * 通过History的id更新History中的数据,包括null值
	 * @param enti
	 * @return
	 */
    int updateHistoryById(History enti);
 	/**
	 * 通过辅助工具Assist的条件更新History中的数据,包括null值
	 * @param value
	 * @param assist
	 * @return
	 */
    int updateHistory(History value,  Assist assist);
	/**
	 * 通过History的id更新History中属性不为null的数据
	 * @param enti
	 * @return
	 */
    int updateNonEmptyHistoryById(History enti);
 	/**
	 * 通过辅助工具Assist的条件更新History中属性不为null的数据
	 * @param value
	 * @param assist
	 * @return
	 */
    int updateNonEmptyHistory(History value, Assist assist);
}