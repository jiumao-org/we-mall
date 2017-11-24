package org.jiumao.db.jdbcUtils.core;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JdbcConnect {

	private String url;// 协议:子协议:<数据资源名称>//主机名：端口/子协议。
	private String user;//登录数据库用户名
	private String password;//密码
	private String driver;//连接驱动


	JdbcConnect(String url, String user, String password, String driver) {
		this.url = url;
		this.user = user;
		this.password = password;
		this.driver = driver;
		try {
			Class.forName(this.driver);
			System.out.println(this.driver);
		} catch (Exception e) {
			throw new ExceptionInInitializerError(e);
		}
	}
	
	/**
	 * 获取数据库连接
	 * 
	 * @return
	 * @throws SQLException
	 */
	Connection getConnection() throws SQLException {
		return DriverManager.getConnection(url, user, password);
	}

	/**
	 * 释放连接资源
	 */
	static void free(ResultSet rs, Statement st, Connection conn) {
		try {
			if (rs != null)
				rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (st != null)
					st.close();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				if (conn != null)
					try {
						conn.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
			}
		}
	}
}
