package org.jiumao.db.jdbcUtils.core;
public class SqlSessionFactory extends SessionFactory {
	public SqlSessionFactory(DataSource dataSource) {
		super(dataSource);
	}

	public static SessionFactory getSessionFactory(String url, String user,
			String password, String driver, int initConnNums, int maxConnNums) {
		JdbcConnect jdbc = new JdbcConnect(url, user, password, driver);
		DataSource dataSource = new DataSource(jdbc, initConnNums, maxConnNums);
		return new SqlSessionFactory(dataSource);
	}
	
	public static SessionFactory getSessionFactory(String url, String user,
			String password, String driver) {
		return getSessionFactory(url, user, password, driver,0,0);
	}
}
