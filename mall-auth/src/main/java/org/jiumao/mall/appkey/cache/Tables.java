package org.jiumao.mall.appkey.cache;

import org.jiumao.mall.appkey.cache.Table.Row;

/**
 * Table 相关辅助操作
 * @author ppf@jiumao.org 
 * @date 2017/12/11
 */
public final class Tables {
    
    public static final String ACCESS_PATH_SPLIT =",";
    
    public static final Table EmptyTable = new EmptyTable();
    public static final Row EmptyRow = new EmptyRow();
    
    public static class EmptyRow implements Row{
        @Override
        public int id() {
            return 0;
        }
    }
    
    public static class EmptyTable implements Table{
        @Override
        public Row getRow(int index) {
            return EmptyRow;
        }

        @Override
        public Table copyEmptyTable() {
            return EmptyTable;
        }

        @Override
        public void init(int size) {
            
        }

        @Override
        public void delete(int id) {
            
        }

        @Override
        public boolean exist(int id) {
            return false;
        }
    }

    public static Table getEmptyTable() {
        return EmptyTable;
    }

    public static Row getEmptyRow() {
        return EmptyRow;
    }
    
    

}
