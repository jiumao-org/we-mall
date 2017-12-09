package org.jiumao.mall.appkey.cache;
/**
 * 返回 所在的table 和 id，在table的数组下标: table[id%tableSize]
 * @author ppf@jiumao.org
 * @date 2017年11月23日
 * @param <T>
 */
public class TbPosition<T extends Table> {
    private T table;
    private int id;


    public TbPosition() {
    }


    public TbPosition(T t, int id) {
        super();
        this.table = t;
        this.id = id;
    }


    public int getId() {
        return id;
    }


    public void setId(int id) {
        this.id = id;
    }


    public T getTable() {
        return table;
    }


    public void setTable(T table) {
        this.table = table;
    }

}