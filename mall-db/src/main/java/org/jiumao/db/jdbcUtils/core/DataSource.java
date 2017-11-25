package org.jiumao.db.jdbcUtils.core;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.LinkedList;

/**
 * 介绍建造者模式 数据库连接池实习
 */
public class DataSource {

	private int init = 1;
	private int max = 1 << 6;
	int currentCount = 0;
	private JdbcConnect jdbc;

	LinkedList<Connection> connectionsPool = new LinkedList<Connection>();
	
	public DataSource(JdbcConnect jdbc) {
		this(jdbc, 0, 0);
	}

	public DataSource(JdbcConnect jdbc,int init,int max) {
		if (init>0)	this.init = init;
		if (max>this.init)	this.max = max;
		this.jdbc = jdbc;
		try {
			for (int i = 0; i < init; i++) {
				System.err.println("初始化了" + i + "条连接");
				this.connectionsPool.addLast(jdbc.getConnection());
				this.currentCount++;
			}
		} catch (SQLException e) {
			throw new ExceptionInInitializerError(e);
		}
	}

	public Connection getConnection() throws SQLException {
		if (this.connectionsPool.size() > 0)
			return this.connectionsPool.removeFirst();

		/*
		 * 如果当前没有连接了
		 */
		if (this.currentCount < max) {// 新建连接
			this.currentCount++;
			return jdbc.getConnection();
		}else {// 线程休眠，添加入后通知获取
			try {
				connectionsPool.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			return getConnection();
		}
	}

	public void free(Connection conn) {
		synchronized (connectionsPool) {
			this.connectionsPool.addLast(conn);
			connectionsPool.notify();
		}
	}

}
