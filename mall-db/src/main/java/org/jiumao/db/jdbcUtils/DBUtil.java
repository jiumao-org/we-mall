package org.jiumao.db.jdbcUtils;

import java.io.IOException;
import java.io.Reader;
import java.sql.CallableStatement;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.jiumao.common.constants.LoggerName;
import org.jiumao.common.utils.DateTrans;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * 数据库操作工具类
 * @update 下步工作会更新此API
 */
public class DBUtil {

    private static final Logger log = LoggerFactory.getLogger(LoggerName.Transaction);

	/**
	 * 
	 * @param rs
	 *            结果
	 * @return 后得到的是 String[]
	 */
	public static String[] getStrs(Connection conn, String sql) {
		String[] strs = null;

		ResultSet rs = null;
		PreparedStatement ps = null;
		try {
			log.info("sql:" + sql);
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			strs = RSUtil.getStrs(rs);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("执行DBUtil方法异常");
		}  
		return strs;
	}

	/**
	 * 查询CLOB字段并转化为字符串
	 * 
	 * @param sql
	 *            查询SQL
	 * @return String，如果未查询到结果集则返回null
	 */
	public static String getClobToStr(Connection conn, String sql,
			List<SqlParam> in_params) {
		String str = null;
		ResultSet rs = null;
		PreparedStatement ps = null;

		Reader inStream = null;
		try {
			ps = conn.prepareStatement(sql);
			log.info("sql:" + sql);
			if (in_params != null) {
				for (int i = 0; i < in_params.size(); i++) {
					SqlParam param = (SqlParam) in_params.get(i);
					int type = param.getParam_type();
					String value = param.getParam_value();
					switch (type) {
					case SqlTypes.CHAR:
						ps.setString(i + 1, value);
						break;
					case SqlTypes.VARCHAR:
						ps.setString(i + 1, value);
						break;
					case SqlTypes.INTEGER:
						ps.setInt(i + 1, Integer.parseInt(value));
						break;
					case SqlTypes.DOUBLE:
						ps.setDouble(i + 1, Double.parseDouble(value));
						break;
					case SqlTypes.BIGINT:
						ps.setLong(i + 1, Long.parseLong(value));
						break;
					default:
						throw new RuntimeException(
								"###: SqlParam参数异常，目前只支持String,number两大类数据类型");

					}
				}
			}

			rs = ps.executeQuery();
			if (rs.next()) {
				Clob clob = rs.getClob(1);
				inStream = clob.getCharacterStream();
				char[] c = new char[(int) clob.length()];
				inStream.read(c);
				str = new String(c);
				inStream.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("执行DBUtil方法异常");
		} 

		return str;
	}

	/**
	 * 
	 * @param rs
	 *            二维结果
	 * @return list.get() 后得到的是 String[]
	 */
	public static List<String[]> getStrsList(Connection conn, String sql) {
		List<String[]> ls = new ArrayList<String[]>();
		ResultSet rs = null;
		PreparedStatement ps = null;
		try {
			log.info("sql:" + sql);
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			ls = RSUtil.getStrsList(rs);

		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("执行DBUtil方法异常");
		}
		return ls;
	}

	/**
	 * 
	 * @param rs
	 *            结果
	 * @return long 后得到的是 count(1)
	 */
	public static long getCount(Connection conn, String sql) {
		long cnt = -1;
		ResultSet rs = null;
		PreparedStatement ps = null;
		try {

			if (log.isInfoEnabled()) {
				log.info("sql:" + sql);
			}
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			cnt = RSUtil.getCount(rs);

		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("执行DBUtil方法异常");
		} 
		return cnt;
	}

	public static List<String[]> getStrsListLabel(Connection conn, String sql) {
		List<String[]> ls = new ArrayList<String[]>();
		ResultSet rs = null;
		PreparedStatement ps = null;
		try {
			log.info("sql:" + sql);
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			ls = RSUtil.getStrsListLabel(rs);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("执行DBUtil方法异常");
		}
		return ls;
	}

	public static ResultSet getResultSet(Connection conn, String sql)
			throws SQLException {
		return conn.prepareStatement(sql).executeQuery();
	}

	/**
	 * execute sql like :insert, update, delete with auto commit
	 */
	public static int update(Connection conn, String sql) throws SQLException {
		int i = -1;
		try {
			conn.setAutoCommit(true);
			log.info(sql);
			i = conn.prepareStatement(sql).executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}
		return i;
	}

	/**
	 * execute sqls like :insert, update, delete with transaction
	 */
	public static int update(Connection conn, String[] sqls)
			throws SQLException {

		int i = -1;
		try {
			conn.setAutoCommit(false);
			for (int j = 0; j < sqls.length; j++) {
				log.info(sqls[j]);
				i = conn.prepareStatement(sqls[j]).executeUpdate();
			}
			conn.commit();
			conn.setAutoCommit(true);
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}
		return i;
	}

	public static String getFormatSeq(Connection conn, String sequenceName) {
		long seq = DBUtil.getOraSeq(conn, sequenceName);
		return DateTrans.getDateFormat() + seq;
	}

	public static long getOraSeq(Connection conn, String sequence_name) {
		long seql = 0l;
		String getseq = "select " + sequence_name + ".nextval  from dual";

		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = conn.prepareStatement(getseq);
			rs = ps.executeQuery();
			while (rs.next()) {
				seql = rs.getLong(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("执行DBUtil方法异常");
		}

		return seql;
	}


	/**
	 * 预编译
	 */
	public static int executePreSql(Connection conn, String sql, List<?> in_params) {
		int ret = -1;
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement(sql);
			log.debug("PreSql:" + sql);
			if (in_params != null) {
				for (int i = 0; i < in_params.size(); i++) {
					SqlParam param = (SqlParam) in_params.get(i);
					int type = param.getParam_type();
					String value = param.getParam_value();
					log.debug("type:" + type + ",value:" + value);
					switch (type) {
					case SqlTypes.CHAR:
						ps.setString(i + 1, value);
						break;
					case SqlTypes.VARCHAR:
						ps.setString(i + 1, value);
						break;
					case SqlTypes.INTEGER:
						ps.setInt(i + 1, Integer.parseInt(value));
						break;
					case SqlTypes.DOUBLE:
						ps.setDouble(i + 1, Double.parseDouble(value));
						break;
					case SqlTypes.BIGINT:
						ps.setLong(i + 1, Long.parseLong(value));
						break;
					case SqlTypes.DATE:
						ps.setDate(i + 1,
								new java.sql.Date(Long.parseLong(value)));
						break;
					// add by luantao@20110309 add timestamp and time
					case SqlTypes.TIMESTAMP:
						ps.setTimestamp(i + 1,
								new java.sql.Timestamp(Long.parseLong(value)));
						break;
					case SqlTypes.TIME:
						ps.setTime(i + 1,
								new java.sql.Time(Long.parseLong(value)));
						break;
					default:
						throw new RuntimeException(
								"###: SqlParam参数异常，目前只支持String,number两大类数据类型");
					}
				}
			}
			ret = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ret;
	}

	/**
	 * 预编译执行批量SQL
	 */
	public static int executeBatchPreSql(Connection conn, String[] sql,
			List<List<SqlParam>> paraList) {
		int ret = -1;

		PreparedStatement ps = null;
		try {
			// 关闭自动提交，保持原子性
			conn.setAutoCommit(false);

			for (int j = 0; j < sql.length; j++) {
				List<SqlParam> in_params = paraList.get(j);

				ps = conn.prepareStatement(sql[j]);
				log.debug("PreSql[" + j + "]:" + sql[j]);
				if (in_params != null) {
					for (int i = 0; i < in_params.size(); i++) {
						SqlParam param = (SqlParam) in_params.get(i);
						int type = param.getParam_type();
						String value = param.getParam_value();
						log.debug("type:" + type + ",value:" + value);
						switch (type) {
						case SqlTypes.CHAR:
							ps.setString(i + 1, value);
							break;
						case SqlTypes.VARCHAR:
							ps.setString(i + 1, value);
							break;
						case SqlTypes.INTEGER:
							ps.setInt(i + 1, Integer.parseInt(value));
							break;
						case SqlTypes.DOUBLE:
							ps.setDouble(i + 1, Double.parseDouble(value));
							break;
						case SqlTypes.BIGINT:
							ps.setLong(i + 1, Long.parseLong(value));
							break;
						case SqlTypes.DATE:
							ps.setDate(i + 1,
									new java.sql.Date(Long.parseLong(value)));
							break;
						// add by luantao@20110309 add timestamp and time
						case SqlTypes.TIMESTAMP:
							ps.setTimestamp(
									i + 1,
									new java.sql.Timestamp(Long
											.parseLong(value)));
							break;
						case SqlTypes.TIME:
							ps.setTime(i + 1,
									new java.sql.Time(Long.parseLong(value)));
							break;
						default:
							throw new RuntimeException(
									"###: SqlParam参数异常，目前只支持String,number两大类数据类型");
						}
					}
				}
				ret = ps.executeUpdate();

				if (ret == -1) {
					conn.rollback();
					return -1;
				}
			}

			conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
				return -1;
			}

			return -1;
		}
		return ret;
	}

	/**
	 * 预编译执行批量SQL
	 */
	public static int executeBatchPreSql(Connection conn, List<String> sql,
			List<List<SqlParam>> paraList) {
		int ret = -1;
		System.out.println(sql.size() + "---" + paraList.size());

		PreparedStatement ps = null;
		try {

			for (int j = 0; j < sql.size(); j++) {
				List<SqlParam> in_params = paraList.get(j);
				log.debug("PreSql[" + j + "]:" + sql.get(j));
				ps = conn.prepareStatement(sql.get(j));
				if (in_params != null) {
					for (int i = 0; i < in_params.size(); i++) {
						SqlParam param = (SqlParam) in_params.get(i);
						int type = param.getParam_type();
						String value = param.getParam_value();
						log.debug("type:" + type + ",value:" + value);
						switch (type) {
						case SqlTypes.CHAR:
							ps.setString(i + 1, value);
							break;
						case SqlTypes.VARCHAR:
							ps.setString(i + 1, value);
							break;
						case SqlTypes.INTEGER:
							ps.setInt(i + 1, Integer.parseInt(value));
							break;
						case SqlTypes.DOUBLE:
							ps.setDouble(i + 1, Double.parseDouble(value));
							break;
						case SqlTypes.BIGINT:
							ps.setLong(i + 1, Long.parseLong(value));
							break;
						case SqlTypes.DATE:
							ps.setDate(i + 1,
									new java.sql.Date(Long.parseLong(value)));
							break;
						// add by luantao@20110309 add timestamp and time
						case SqlTypes.TIMESTAMP:
							ps.setTimestamp(
									i + 1,
									new java.sql.Timestamp(Long
											.parseLong(value)));
							break;
						case SqlTypes.TIME:
							ps.setTime(i + 1,
									new java.sql.Time(Long.parseLong(value)));
							break;
						default:
							throw new RuntimeException(
									"###: SqlParam参数异常，目前只支持String,number两大类数据类型");
						}
					}
				}
				ret = ps.executeUpdate();

				if (ret == -1) {
					conn.rollback();
					return -1;
				}
			}

			conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
				return -1;
			}

			return -1;
		}
		return ret;
	}

	public static List<String[]> getStrsListPreSql(Connection conn, String sql,
			List<SqlParam> in_params) {
		List<String[]> ls = null;

		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = conn.prepareStatement(sql);
			log.debug("PreSql:" + sql);
			if (in_params != null) {
				for (int i = 0; i < in_params.size(); i++) {
					SqlParam param = in_params.get(i);
					int type = param.getParam_type();
					String value = param.getParam_value();
					switch (type) {
					case SqlTypes.CHAR:
						ps.setString(i + 1, value);
						break;
					case SqlTypes.VARCHAR:
						ps.setString(i + 1, value);
						break;
					case SqlTypes.INTEGER:
						ps.setInt(i + 1, Integer.parseInt(value));
						break;
					case SqlTypes.DOUBLE:
						ps.setDouble(i + 1, Double.parseDouble(value));
						break;
					case SqlTypes.BIGINT:
						ps.setLong(i + 1, Long.parseLong(value));
						break;
					default:
						throw new RuntimeException(
								"###: SqlParam参数异常，目前只支持String,number两大类数据类型");

					}
				}
			}
			rs = ps.executeQuery();
			ls = RSUtil.getStrsList(rs);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return ls;

	}


	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static List<String[]> getStrArrayListPreProc(Connection conn,
			String sql, List<SqlParam> in_params, List<SqlParam> out_params) {
		List oList = new ArrayList();
		List<String[]> list = new ArrayList();

		CallableStatement proc = null;
		ResultSet rs = null;
		int i = 1;

		try {
			proc = conn.prepareCall(sql);

			// 绑定输入参数
			if (in_params != null) {
				for (; i <= in_params.size(); i++) {
					SqlParam param = (SqlParam) in_params.get(i - 1);
					int type = param.getParam_type();
					String value = param.getParam_value();

					switch (type) {
					case SqlTypes.CHAR:
						proc.setString(i, value);
						break;
					case SqlTypes.VARCHAR:
						proc.setString(i, value);
						break;
					case SqlTypes.INTEGER:
						proc.setInt(i, Integer.parseInt(value));
						break;
					case SqlTypes.DOUBLE:
						proc.setDouble(i, Double.parseDouble(value));
						break;
					case SqlTypes.BIGINT:
						proc.setLong(i, Long.parseLong(value));
						break;
					default:
						throw new RuntimeException(
								"###: SqlParam参数异常，目前只支持String,number两大类数据类型");
					}
				}
			}

			// 绑定输出参数
			if (out_params != null) {
				for (int j = 0; j < out_params.size(); j++) {
					SqlParam param = (SqlParam) out_params.get(j);
					int type = param.getParam_type();

					proc.registerOutParameter(i + j, type);
				}
			}
			long start_tmp = System.currentTimeMillis();
			// 调用存储过程
			proc.execute();
			System.out.println("--------------->"
					+ (System.currentTimeMillis() - start_tmp));

			// 将输出结果放入List中
			if (out_params != null) {
				int oIndex = in_params.size();
				for (int k = 1; k <= out_params.size(); k++) {
					SqlParam param = (SqlParam) out_params.get(k - 1);
					int type = param.getParam_type();

					switch (type) {
					case SqlTypes.VARCHAR:
						String value = (String) proc.getString(oIndex + k);
						oList.add(value);
						break;
					case SqlTypes.CURSOR:
						rs = (ResultSet) proc.getObject(oIndex + k);
						list = RSUtil.getStrsList(rs);
						oList.add(list);
						break;
					default:
						throw new RuntimeException(
								"###: SqlParam参数异常，目前只支持String,number两大类数据类型");
					}
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return oList;
	}
}
