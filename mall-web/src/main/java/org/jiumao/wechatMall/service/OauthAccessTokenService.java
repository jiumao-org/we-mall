package org.jiumao.wechatMall.service;
import java.util.List;
import org.jiumao.wechatMall.entity.OauthAccessToken;
import org.jiumao.wechatMall.common.Assist;
public interface OauthAccessTokenService{
	/**
	 * 获得OauthAccessToken数据的总行数,可以通过辅助工具Assist进行条件查询,如果没有条件则传入null
	 * @param assist
	 * @return
	 */
    long getOauthAccessTokenRowCount(Assist assist);
	/**
	 * 获得OauthAccessToken数据集合,可以通过辅助工具Assist进行条件查询,如果没有条件则传入null
	 * @param assist
	 * @return
	 */
    List<OauthAccessToken> selectOauthAccessToken(Assist assist);
	/**
	 * 获得一个OauthAccessToken对象,以参数OauthAccessToken对象中不为空的属性作为条件进行查询
	 * @param obj
	 * @return
	 */
    OauthAccessToken selectOauthAccessTokenByObj(OauthAccessToken obj);
	/**
	 * 通过OauthAccessToken的id获得OauthAccessToken对象
	 * @param id
	 * @return
	 */
    OauthAccessToken selectOauthAccessTokenById(Object id);
	/**
	 * 插入OauthAccessToken到数据库,包括null值
	 * @param value
	 * @return
	 */
    int insertOauthAccessToken(OauthAccessToken value);
	/**
	 * 插入OauthAccessToken中属性值不为null的数据到数据库
	 * @param value
	 * @return
	 */
    int insertNonEmptyOauthAccessToken(OauthAccessToken value);
	/**
	 * 通过OauthAccessToken的id删除OauthAccessToken
	 * @param id
	 * @return
	 */
    int deleteOauthAccessTokenById(Object id);
	/**
	 * 通过辅助工具Assist的条件删除OauthAccessToken
	 * @param assist
	 * @return
	 */
    int deleteOauthAccessToken(Assist assist);
	/**
	 * 通过OauthAccessToken的id更新OauthAccessToken中的数据,包括null值
	 * @param enti
	 * @return
	 */
    int updateOauthAccessTokenById(OauthAccessToken enti);
 	/**
	 * 通过辅助工具Assist的条件更新OauthAccessToken中的数据,包括null值
	 * @param value
	 * @param assist
	 * @return
	 */
    int updateOauthAccessToken(OauthAccessToken value,  Assist assist);
	/**
	 * 通过OauthAccessToken的id更新OauthAccessToken中属性不为null的数据
	 * @param enti
	 * @return
	 */
    int updateNonEmptyOauthAccessTokenById(OauthAccessToken enti);
 	/**
	 * 通过辅助工具Assist的条件更新OauthAccessToken中属性不为null的数据
	 * @param value
	 * @param assist
	 * @return
	 */
    int updateNonEmptyOauthAccessToken(OauthAccessToken value, Assist assist);
}