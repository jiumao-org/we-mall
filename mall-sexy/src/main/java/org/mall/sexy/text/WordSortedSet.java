package org.mall.sexy.text;

import java.util.AbstractSet;
import java.util.Comparator;
import java.util.Iterator;
import java.util.TreeMap;

/**
 * 用于统计词频和快速查找过滤
 * <p>
 * 根据字符串 hashcode 和 长度 排序，hash值相同放到一个桶里
 * 
 * @author Bevis-Pei<ppf@jiumao.org>
 * @date 2018/01/19
 */
public class WordSortedSet extends AbstractSet<String> {
    private TreeMap<String, Integer> m = new TreeMap<>(WordComparator);

    public WordSortedSet() {
        super();
    }

    private static Comparator<String> WordComparator = (String e1, String e2) -> {
        if (e1.length() > e2.length()) return -1;
        if (e1.length() < e2.length()) return 1;

        if (e1.length() == e2.length()) {
            if (e1.hashCode() > e2.hashCode()) return -1;
            if (e1.hashCode() > e2.hashCode()) return 1;
        }
        return 0;
    };

    public Iterator<java.util.Map.Entry<String, Integer>> getMap() {
        return m.entrySet().iterator();
    }

    public Integer getCount(String word) {
        return m.get(word);
    }

    @Override
    public boolean add(String e) {
        Integer count = m.get(e);
        return null != count ? m.put(e, ++count) == count : 0 == m.put(e, 0);
    }

    @Override
    public Iterator<String> iterator() {
        return m.keySet().iterator();
    }

    @Override
    public int size() {
        return m.size();
    }

}


