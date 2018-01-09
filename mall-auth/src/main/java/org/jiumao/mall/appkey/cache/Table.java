package org.jiumao.mall.appkey.cache;

/**
 * 多列元素，数组实现 ，通过数组下标访问
 * <P>
 * 必须有默认的构造方法
 * 
 * @author ppf@jiumao.org
 * @date 2017年11月22日
 */
public interface Table {

    Row getRow(int index);


    void delete(int id);


    /** Column */
//    void put(Column cl);
    
    boolean exist(int id);


    void init(int size);


    Table copyEmptyTable();

    public interface Row {
        int id();
    }


}
