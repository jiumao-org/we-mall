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

        if (e1.length() == e2.length()) {
            if (e1.hashCode() == e2.hashCode()) {
                int h1 = WordSortedSet.fastELFHash(e1);
                int h2 = WordSortedSet.fastELFHash(e2);
                if (h1 == h2) {
                    char c1 = '0', c2 = '0';
                    for (int i = 0; i < e1.length(); i++) {
                        c1 = e1.charAt(i);
                        c2 = e1.charAt(i);
                        if (c1==c2) {
                            continue;
                        }else {
                            return c1 > c2 ? -1 : 1;
                        }
                    }
                    return 0;
                } else {
                    return h1 > h2 ? -1 : 1;
                }

            } else {
                return e1.hashCode() > e2.hashCode() ? -1 : 1;
            }
        } else {
            return e1.length() > e2.length() ? -1 : 1;
        }
    };

    /**
     * <ol>
     * <li>遍历字符串每个字符
     * <li>x获取了hash高四位，影响下次生成
     * 
     */
    public static int fastELFHash(String s) {
        int hash = 0;
        int x = 0;
        for (int i = 0; i < s.length(); i++) {
            hash = (hash << 4) + s.charAt(i);
            if ((x = hash & 0x7f000_000) != 0) {
                hash ^= (x >> 24);
                hash &= ~x;
            }
        }
        return (hash & 0x7fffffff);
    }

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
    
    @Override
    public boolean contains(Object key) {
        return m.containsKey((String)key);
    }

}


