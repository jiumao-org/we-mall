package org.jiumao.wechatMall.service;
import java.util.List;
import org.jiumao.wechatMall.entity.Privilege;
import org.jiumao.wechatMall.common.Assist;
public interface PrivilegeService{
	/**
	 * 获得Privilege数据的总行数,可以通过辅助工具Assist进行条件查询,如果没有条件则传入null
	 * @param assist
	 * @return
	 */
    long getPrivilegeRowCount(Assist assist);
	/**
	 * 获得Privilege数据集合,可以通过辅助工具Assist进行条件查询,如果没有条件则传入null
	 * @param assist
	 * @return
	 */
    List<Privilege> selectPrivilege(Assist assist);
	/**
	 * 获得一个Privilege对象,以参数Privilege对象中不为空的属性作为条件进行查询
	 * @param obj
	 * @return
	 */
    Privilege selectPrivilegeByObj(Privilege obj);
	/**
	 * 通过Privilege的id获得Privilege对象
	 * @param id
	 * @return
	 */
    Privilege selectPrivilegeById(Object id);
	/**
	 * 插入Privilege到数据库,包括null值
	 * @param value
	 * @return
	 */
    int insertPrivilege(Privilege value);
	/**
	 * 插入Privilege中属性值不为null的数据到数据库
	 * @param value
	 * @return
	 */
    int insertNonEmptyPrivilege(Privilege value);
	/**
	 * 通过Privilege的id删除Privilege
	 * @param id
	 * @return
	 */
    int deletePrivilegeById(Object id);
	/**
	 * 通过辅助工具Assist的条件删除Privilege
	 * @param assist
	 * @return
	 */
    int deletePrivilege(Assist assist);
	/**
	 * 通过Privilege的id更新Privilege中的数据,包括null值
	 * @param enti
	 * @return
	 */
    int updatePrivilegeById(Privilege enti);
 	/**
	 * 通过辅助工具Assist的条件更新Privilege中的数据,包括null值
	 * @param value
	 * @param assist
	 * @return
	 */
    int updatePrivilege(Privilege value,  Assist assist);
	/**
	 * 通过Privilege的id更新Privilege中属性不为null的数据
	 * @param enti
	 * @return
	 */
    int updateNonEmptyPrivilegeById(Privilege enti);
 	/**
	 * 通过辅助工具Assist的条件更新Privilege中属性不为null的数据
	 * @param value
	 * @param assist
	 * @return
	 */
    int updateNonEmptyPrivilege(Privilege value, Assist assist);
}