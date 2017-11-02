package org.jiumao.wechatMall.service;
import java.util.List;
import org.jiumao.wechatMall.entity.GiftCard;
import org.jiumao.wechatMall.common.Assist;
public interface GiftCardService{
	/**
	 * 获得GiftCard数据的总行数,可以通过辅助工具Assist进行条件查询,如果没有条件则传入null
	 * @param assist
	 * @return
	 */
    long getGiftCardRowCount(Assist assist);
	/**
	 * 获得GiftCard数据集合,可以通过辅助工具Assist进行条件查询,如果没有条件则传入null
	 * @param assist
	 * @return
	 */
    List<GiftCard> selectGiftCard(Assist assist);
	/**
	 * 获得一个GiftCard对象,以参数GiftCard对象中不为空的属性作为条件进行查询
	 * @param obj
	 * @return
	 */
    GiftCard selectGiftCardByObj(GiftCard obj);
	/**
	 * 通过GiftCard的id获得GiftCard对象
	 * @param id
	 * @return
	 */
    GiftCard selectGiftCardById(Long id);
	/**
	 * 插入GiftCard到数据库,包括null值
	 * @param value
	 * @return
	 */
    int insertGiftCard(GiftCard value);
	/**
	 * 插入GiftCard中属性值不为null的数据到数据库
	 * @param value
	 * @return
	 */
    int insertNonEmptyGiftCard(GiftCard value);
	/**
	 * 通过GiftCard的id删除GiftCard
	 * @param id
	 * @return
	 */
    int deleteGiftCardById(Long id);
	/**
	 * 通过辅助工具Assist的条件删除GiftCard
	 * @param assist
	 * @return
	 */
    int deleteGiftCard(Assist assist);
	/**
	 * 通过GiftCard的id更新GiftCard中的数据,包括null值
	 * @param enti
	 * @return
	 */
    int updateGiftCardById(GiftCard enti);
 	/**
	 * 通过辅助工具Assist的条件更新GiftCard中的数据,包括null值
	 * @param value
	 * @param assist
	 * @return
	 */
    int updateGiftCard(GiftCard value,  Assist assist);
	/**
	 * 通过GiftCard的id更新GiftCard中属性不为null的数据
	 * @param enti
	 * @return
	 */
    int updateNonEmptyGiftCardById(GiftCard enti);
 	/**
	 * 通过辅助工具Assist的条件更新GiftCard中属性不为null的数据
	 * @param value
	 * @param assist
	 * @return
	 */
    int updateNonEmptyGiftCard(GiftCard value, Assist assist);
}