package org.jiumao.db.jdbcUtils.core;

/**
 * 桥接模式
 * 对应不同的数据源提供不同的数据库连接
 */

import java.sql.Connection;
import java.sql.SQLException;

import org.jiumao.common.constants.LoggerName;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * 数据库会话操作<br>
 * 连接池管理。处理每个会话请求
 */
public abstract class SessionFactory {
    private DataSource dataSource;
    private ThreadLocal<Connection> thread = new ThreadLocal<Connection>();

    private static final Logger logger = LoggerFactory.getLogger(LoggerName.Server);


    public SessionFactory(DataSource dataSource) {
        this.dataSource = dataSource;
    }


    public Connection getConnection() {
        try {
            Connection con = thread.get();
            if (con == null) {
                con = dataSource.getConnection();
                thread.set(con);
            }
            return con;
        }
        catch (SQLException e) {
            logger.error("获取连接异常",e);
        }
        return null;
    }


    public Session getSession() {
        return new Session(getConnection());
    }


    public void free(Connection conn) {
        this.dataSource.free(conn);
    }


    public void close(Session s) {
        if (null != s.getConn()) {
            this.free(s.getConn());
            s.close();
        }
    }

}
