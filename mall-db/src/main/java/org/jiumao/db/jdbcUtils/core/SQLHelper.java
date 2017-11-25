package org.jiumao.db.jdbcUtils.core;

/**
 * 用于辅助拼接Sql语句
 */
public abstract class SQLHelper {
	/** " , "*/
	protected final static String DOT = " , ";//注意是否需要空格
	/** " select "*/
	protected final static String SELECT =  "SELECT ";//注意是否需要空格
	/** " from "*/
	protected final static String FROM = " FROM ";
	/**  " where "	 */
	protected final static String WHERE = " WHERE ";
	/** " AND "*/
	protected final static String AND = " AND ";
	/** " ORDER BY "*/
	protected final static String ORDER_BY = " ORDER BY ";
	/** " ASC "*/
	protected final static String ASC = " ASC ";
	/** " DESC "*/
	protected final static String DESC = " DESC ";
	
	
	protected String fromClause; // FROM子句
	protected String whereClause = ""; // Where子句
	protected String orderByClause = ""; // OrderBy子句


	/**
	 * 拼接Where子句
	 * @param condition
	 */
	public SQLHelper addCondition(String condition) {
		// 拼接
		if (whereClause.length() == 0) {
			whereClause = WHERE + condition;
		} else {
			whereClause += AND + condition;
		}
		return this;
	}


	/**
	 * 拼接OrderBy子句
	 * 
	 * @param propertyName
	 *            参与排序的属性名
	 * @param asc
	 *            true表示升序，false表示降序
	 */
	public SQLHelper addOrderProperty(String propertyName, boolean asc) {
		if (orderByClause.length() == 0) {
			orderByClause = ORDER_BY + propertyName + (asc ? ASC : DESC);
		} else {
			orderByClause += DOT + propertyName + (asc ? ASC : DESC);
		}
		return this;
	}

	public String toString(){
		return fromClause + whereClause + orderByClause; 
	}
}
