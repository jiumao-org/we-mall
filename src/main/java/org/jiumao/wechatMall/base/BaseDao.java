package org.jiumao.wechatMall.base;

import java.util.List;

public abstract class BaseDao<T extends BaseBean> {

	/** 保存实体 */
	abstract int save(Object... parameters) throws Exception;

	/** 删除实体 */
	abstract int delete(Integer id) throws Exception;

	/** 更新实体 */
	abstract int update(String sql, Object... parameters) throws Exception;

	/** 更新实体通过id */
	abstract int updateById(Integer id, Object... parameters) throws Exception;

	/** 按id查询 */
	abstract T getById(Integer integer) throws Exception;

	abstract List<T> getByIds(Integer[] ids) throws Exception;

	abstract List<T> findAll(int pageNum, int pageSize) throws Exception;

	abstract List<Integer> findIds() throws Exception;
}
