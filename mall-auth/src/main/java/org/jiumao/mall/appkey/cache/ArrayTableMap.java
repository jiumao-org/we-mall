package org.jiumao.mall.appkey.cache;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.Timer;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

import javax.inject.Singleton;

import org.jiumao.mall.appkey.cache.Table.Row;


/**
 * hash结构，int:table<col1,col2...>
 * 
 * @author ppf@jiumao.org
 * @date 2017年11月22日
 */
@Singleton
public class ArrayTableMap<T extends AbstractTable> {

    private T currentTable;
    private Class<T> clazz;
    

    private int leftShift = 10;
    private final int tableSize = 2 << leftShift;
    private final AtomicInteger index = new AtomicInteger(0);

    // 超过阈值，启动数组压缩
    private AtomicInteger deleteCount = new AtomicInteger(0);
    private final int deleteThreshold = tableSize >> 2;
    private volatile boolean arrayCompression = false;

    private final List<T> tables = new ArrayList<T>(8);
    private final Map<Integer, Set<Integer>> deleteTableIndex =
            new ConcurrentHashMap<Integer, Set<Integer>>();


    private void grow() {
        try {
            T t = clazz.newInstance();
            t.init(tableSize);
            tables.add(t);
            currentTable = t;
            deleteTableIndex.put(countTable(), new HashSet<Integer>(8));
        }
        catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }


    public void addTable(T e) {
        tables.add(e);
        currentTable = e;
        deleteTableIndex.put(countTable(), new HashSet<Integer>(8));
    }


    public ArrayTableMap(Class<T> clz) {
        this.clazz = clz;
        grow();
    }


    public void clear() {
        tables.clear();
        deleteTableIndex.clear();
        index.set(0);
    }


    public int countTable() {
        return tables.size();
    }


    public Row getById(int id) {
        int idx = id % tableSize;
        for (T t : tables) {
            Row cl = t.getRow(idx);
            if (id == cl.id()) {
                return cl;
            }
        }
        return Tables.getEmptyRow();
    }


    public Row deleteById(int key) {
        if (deleteCount.incrementAndGet() > deleteThreshold) {
            arrayCompression = true;
        }
        int tb = 0, idx = key % tableSize;
        Row cl = null;
        for (Iterator<T> iterator = tables.iterator(); iterator.hasNext(); tb++) {
            T t = iterator.next();
            cl = t.getRow(idx);
            if (cl.id() == key) {
                addToDelIdx(tb, idx);
                break;
            }
        }
        return cl;
    }


    /**
     * writer for T.put(...)
     */
    public TbPosition<T> getPosition() {
        if (arrayCompression) {
            TbPosition<T> pos = combine();
            if (null != pos)
                return pos;
            arrayCompression = false;
            deleteCount.set(0);
        }
        int idx = index.getAndIncrement() % tableSize;
        int id = getId(idx);
        ensureCapacity();

        // 数组按序插入，返回当前table
        return new TbPosition<T>(currentTable, id);
    }


    private void ensureCapacity() {
        if ((index.get() - 1) / tableSize > (countTable() - 1)) {
            grow();
        }
    }


    private int getId(int idx) {
        int id = currentTable.getGenerator().id() * tableSize + idx;
        if (contains(id) != -1) {
            getById(idx);
        }
        return id;
    }


    /**
     * @return -1 or tables[i]
     */
    public int contains(int id) {
        for (int i = 0; i < countTable(); i++) {
            if (tables.get(i).exist(id)) {
                return i;
            }
        }
        return -1;
    }


    private void addToDelIdx(int tableNum, int index) {
        deleteTableIndex.get(tableNum).add(index);
    }


    private TbPosition<T> combine() {
        for (Iterator<Entry<Integer, Set<Integer>>> tbs = deleteTableIndex.entrySet().iterator(); tbs
            .hasNext();) {
            Entry<Integer, Set<Integer>> tb = tbs.next();
            for (Iterator<Integer> idxs = tb.getValue().iterator(); idxs.hasNext();) {
                int idx = idxs.next();
                idxs.remove();
                if (idx > -1) {
                    int id = getId(idx);
                    return new TbPosition<T>(tables.get(tb.getKey()), id);
                }
            }
        }

        return null;
    }


    /**
     * 找出表中所有id <= 0 的位置，放到{@link #deleteTableIndex}
     */
    public void findEmptyRow() {
        index.set(0);
        synchronized (tables) {
            int tableNum = -1;
            for (T t : tables) {
                tableNum++;
                for (int i = 0; i < tableSize; i++) {
                    index.getAndIncrement();
                    if (t.getRow(i).id() < 1) {
                        addToDelIdx(tableNum, i);
                    }
                }
            }
        }
    }


    /**
     * tablesize = 2 << {@link #leftShift}
     */
    public ArrayTableMap(int leftShift, T t) {
        this.leftShift = leftShift;
        tables.add(t);
    }


    public int getLeftShift() {
        return leftShift;
    }


    public int getTableSize() {
        return tableSize;
    }


    public int getNextIndex() {
        return index.getAndIncrement();
    }


    public int size() {
        return index.get();
    }


    public List<T> getTables() {
        return tables;
    }

}
