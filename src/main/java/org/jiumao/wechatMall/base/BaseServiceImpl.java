package org.jiumao.wechatMall.base;

import java.util.List;

public abstract class BaseServiceImpl<T extends BaseBean> implements BaseService<T>{
	private Class<T> clazz;
	protected BaseDao<T> baseDao;
	@SuppressWarnings("unchecked")
	public BaseServiceImpl() {
		// 使用反射技术得到T的真实类型
	}
	
	public int save(Object... parameters) throws Exception {
		return baseDao.save(parameters);
	}
	public int delete(Integer id) throws Exception {
		return baseDao.delete(id);
	}
	public int update(String sql,Object... parameters) throws Exception {
		return baseDao.update(sql,parameters);
	}
	public T getById(Integer id) throws Exception {
		return baseDao.getById(id);
	}
	public List<T> getByIds(Integer[] ids) throws Exception {
		return baseDao.getByIds(ids);
	}
	public List<T> findAll(int fistResult, int pageSize) throws Exception {
		return baseDao.findAll(fistResult, pageSize);
	}
	@Override
	public int updateById(Integer id, Object... parameters) throws Exception {
		return baseDao.updateById(id, parameters);
	}
}
