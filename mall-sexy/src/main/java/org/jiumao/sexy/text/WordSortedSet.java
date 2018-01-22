package org.jiumao.sexy.text;

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
    private TreeMap<String, Integer> m;
    private static Comparator<String> wordComparator = createComparator(true);

    public WordSortedSet() {
        m = new TreeMap<>(wordComparator);
    }

    /**
     * 根据字符串长度选择不同的比较器
     * 
     * @param isWord 是否是短语或长度很小的字符串
     * @return
     */
    public static Comparator<String> createComparator(boolean isWord) {
        return (String e1, String e2) -> {
            if (e1 == e2) return 0;
            if (e1.length() == e2.length()) {
                // hashcode
                int h1 = isWord ? WordSortedSet.fastHash(e1) : e1.hashCode();
                int h2 = isWord ? WordSortedSet.fastHash(e2) : e2.hashCode();
                if (h1 == h2) {
                    char c1 = '0', c2 = '0';
                    for (int i = 0; i < e1.length(); i++) {
                        c1 = e1.charAt(i);
                        c2 = e1.charAt(i);
                        if (c1 == c2) {
                            continue;
                        } else {
                            return c1 > c2 ? -1 : 1;
                        }
                    }
                    return 0;
                } else {
                    return h1 > h2 ? -1 : 1;
                }
            } else {
                return e1.length() > e2.length() ? -1 : 1;
            }
        };
    }



    static final int x = 0B111111;

    /**
     * 用于短字符串hash生成
     * <ol>
     * <li>遍历字符串每个字符
     * <li>获取hash高四位，影响下次生成
     * 
     */
    public static int fastHash(String s) {
        int hash = 0;
        for (int i = 0; i < s.length(); i++) {
            hash += s.charAt(i) << (i << 0B111111);
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
        return m.containsKey((String) key);
    }

}


