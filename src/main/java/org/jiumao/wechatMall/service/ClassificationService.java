package org.jiumao.wechatMall.service;
import java.util.List;

import org.jiumao.wechatMall.common.Assist;
import org.jiumao.wechatMall.entity.Classification;
public interface ClassificationService{
	/**
	 * 获得Classification数据的总行数,可以通过辅助工具Assist进行条件查询,如果没有条件则传入null
	 * @param assist
	 * @return
	 */
    long getClassificationRowCount(Assist assist);
	/**
	 * 获得Classification数据集合,可以通过辅助工具Assist进行条件查询,如果没有条件则传入null
	 * @param assist
	 * @return
	 */
    List<Classification> selectClassification(Assist assist);
	/**
	 * 获得一个Classification对象,以参数Classification对象中不为空的属性作为条件进行查询
	 * @param obj
	 * @return
	 */
    Classification selectClassificationByObj(Classification obj);
	/**
	 * 通过Classification的id获得Classification对象
	 * @param id
	 * @return
	 */
    Classification selectClassificationById(Object id);
	/**
	 * 插入Classification到数据库,包括null值
	 * @param value
	 * @return
	 */
    int insertClassification(Classification value);
	/**
	 * 插入Classification中属性值不为null的数据到数据库
	 * @param value
	 * @return
	 */
    int insertNonEmptyClassification(Classification value);
	/**
	 * 通过Classification的id删除Classification
	 * @param id
	 * @return
	 */
    int deleteClassificationById(Object id);
	/**
	 * 通过辅助工具Assist的条件删除Classification
	 * @param assist
	 * @return
	 */
    int deleteClassification(Assist assist);
	/**
	 * 通过Classification的id更新Classification中的数据,包括null值
	 * @param enti
	 * @return
	 */
    int updateClassificationById(Classification enti);
 	/**
	 * 通过辅助工具Assist的条件更新Classification中的数据,包括null值
	 * @param value
	 * @param assist
	 * @return
	 */
    int updateClassification(Classification value,  Assist assist);
	/**
	 * 通过Classification的id更新Classification中属性不为null的数据
	 * @param enti
	 * @return
	 */
    int updateNonEmptyClassificationById(Classification enti);
 	/**
	 * 通过辅助工具Assist的条件更新Classification中属性不为null的数据
	 * @param value
	 * @param assist
	 * @return
	 */
    int updateNonEmptyClassification(Classification value, Assist assist);
}