package org.jiumao.wechatMall.service;
import java.util.List;
import org.jiumao.wechatMall.entity.Account;
import org.jiumao.wechatMall.common.Assist;
public interface AccountService{
	/**
	 * 获得Account数据的总行数,可以通过辅助工具Assist进行条件查询,如果没有条件则传入null
	 * @param assist
	 * @return
	 */
    long getAccountRowCount(Assist assist);
	/**
	 * 获得Account数据集合,可以通过辅助工具Assist进行条件查询,如果没有条件则传入null
	 * @param assist
	 * @return
	 */
    List<Account> selectAccount(Assist assist);
	/**
	 * 获得一个Account对象,以参数Account对象中不为空的属性作为条件进行查询
	 * @param obj
	 * @return
	 */
    Account selectAccountByObj(Account obj);
	/**
	 * 通过Account的id获得Account对象
	 * @param id
	 * @return
	 */
    Account selectAccountById(Object id);
	/**
	 * 插入Account到数据库,包括null值
	 * @param value
	 * @return
	 */
    int insertAccount(Account value);
	/**
	 * 插入Account中属性值不为null的数据到数据库
	 * @param value
	 * @return
	 */
    int insertNonEmptyAccount(Account value);
	/**
	 * 通过Account的id删除Account
	 * @param id
	 * @return
	 */
    int deleteAccountById(Object id);
	/**
	 * 通过辅助工具Assist的条件删除Account
	 * @param assist
	 * @return
	 */
    int deleteAccount(Assist assist);
	/**
	 * 通过Account的id更新Account中的数据,包括null值
	 * @param enti
	 * @return
	 */
    int updateAccountById(Account enti);
 	/**
	 * 通过辅助工具Assist的条件更新Account中的数据,包括null值
	 * @param value
	 * @param assist
	 * @return
	 */
    int updateAccount(Account value,  Assist assist);
	/**
	 * 通过Account的id更新Account中属性不为null的数据
	 * @param enti
	 * @return
	 */
    int updateNonEmptyAccountById(Account enti);
 	/**
	 * 通过辅助工具Assist的条件更新Account中属性不为null的数据
	 * @param value
	 * @param assist
	 * @return
	 */
    int updateNonEmptyAccount(Account value, Assist assist);
}