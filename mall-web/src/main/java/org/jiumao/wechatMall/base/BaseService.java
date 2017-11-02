package org.jiumao.wechatMall.base;
import java.util.List;

public interface BaseService<T extends BaseBean> {
	/**  保存实体 */
	int save(Object... parameters) throws Exception ;

	/**  删除实体*/
	int delete(Integer id) throws Exception ;

	/**  更新实体 */
	int update(String sql,Object... parameters) throws Exception ;
	
	/**  更新实体通过id */
	int updateById(Integer id,Object... parameters) throws Exception;

	/** 按id查询 */
	T getById(Integer integer) throws Exception ;

	List<T> getByIds(Integer[] ids) throws Exception ;

	List<T> findAll(int pageNum, int pageSize) throws Exception ;
}