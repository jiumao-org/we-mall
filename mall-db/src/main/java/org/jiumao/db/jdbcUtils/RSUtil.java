package org.jiumao.db.jdbcUtils;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


/**
 * 把数据库中的数据取出，用一定的数据结构存到内存当中，以便于在数据库连接断开之后， 还可以使用这些数据。
 * 
 * @author iceblade
 * @date 2006-06-21
 * 
 */
public final class RSUtil {



    /**
     * 
     * @param rs select count(1) 的结果集(只有一条记录)
     * @return long 得到的是长整型的数数据
     */
    public static long getCount(ResultSet rs) throws SQLException {
        long i = 0;
        while (rs.next()) {
            i = rs.getLong(1);
        }
        return i;
    }


    /**
     * 
     * @param rs 结果集(只有一条记录)
     * @return 得到的是 String[]
     */
    public static String[] getStrs(ResultSet rs) throws SQLException {
        int cols = rs.getMetaData().getColumnCount();
        String strs[] = new String[cols];
        while (rs.next()) {
            for (int i = 1; i <= cols; i++) {
                strs[i - 1] = rs.getString(i);
            }
        }

        return strs;

    }


    /**
     * 
     * @param rs 二维结果
     * @return list.get() 后得到的是 String[]
     */
    public static List<String[]> getStrsList(ResultSet rs) throws SQLException {
        List<String[]> ls = new ArrayList<String[]>();
        int cols = rs.getMetaData().getColumnCount();
        while (rs.next()) {
            String strs[] = new String[cols];
            for (int i = 1; i <= cols; i++) {

                strs[i - 1] = rs.getString(i);
            }
            ls.add(strs);
        }

        return ls;

    }


    /**
     * 
     * @param rs 二维结果
     * @param begin_row 开始行(从第一行开始)
     * @param end_row 结束行 begin_row<= $ <=end_row
     * 
     * @return list.get() 后得到的是 String[]
     */
    public static List<String[]> getStrsList(ResultSet rs, int begin_row, int end_row) throws SQLException {
        List<String[]> ls = new ArrayList<String[]>();
        int cols = rs.getMetaData().getColumnCount();
        int n = 0;
        while (rs.next()) {
            n++;
            if (n < begin_row)
                continue;

            String strs[] = new String[cols];
            for (int i = 1; i <= cols; i++) {

                strs[i - 1] = rs.getString(i);
            }
            ls.add(strs);

            if (n >= end_row)
                break;
        }

        return ls;

    }


    /**
     * 
     * @param rs 二维结果
     * @return list.get() 后得到的是 String[] 第一行是字段的Label ，第二行开始才是数据
     */

    public static List<String[]> getStrsListLabel(ResultSet rs) throws SQLException {
        List<String[]> ls = new ArrayList<String[]>();
        int cols = rs.getMetaData().getColumnCount();
        String strs1[] = new String[cols];
        for (int i = 0; i < cols; i++) {
            strs1[i] = rs.getMetaData().getColumnLabel(i + 1);
        }
        ls.add(strs1);
        while (rs.next()) {
            String strs[] = new String[cols];
            for (int i = 1; i <= cols; i++) {

                strs[i - 1] = rs.getString(i);
            }
            ls.add(strs);
        }
        return ls;

    }

}
