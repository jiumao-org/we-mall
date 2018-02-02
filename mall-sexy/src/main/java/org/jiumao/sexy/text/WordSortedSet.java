package org.jiumao.sexy.text;

import java.util.Comparator;
import java.util.TreeMap;

import org.apache.commons.lang3.StringUtils;


/**
 * 用于统计词频和快速查找过滤
 * <p>
 * 根据字符串 hashcode 和 长度 排序，hash值相同放到一个桶里
 * <ol>
 * <li>hash到槽位
 * <li>红黑树排序
 * 
 * 
 * @author Bevis-Pei<ppf@jiumao.org>
 * @date 2018/01/19
 */
public class WordSortedSet {
    private static Comparator<String> wordComparator = createComparator();
    private TreeMap<String, Integer>[] house;
    private boolean isShortChar;
    private int initialCapacity;
    private int size;

    @SuppressWarnings("unchecked")
    public WordSortedSet(int initialCapacity, boolean isWord) {
        this.isShortChar = isWord;
        this.initialCapacity = Math.max(7, initialCapacity);
        this.house = new TreeMap[initialCapacity];

        for (int i = 0; i < house.length; i++) {
            house[i] = new TreeMap<String, Integer>(wordComparator);
        }
    }

    public WordSortedSet() {
        this(17, true);
    }



    /**
     * 根据字符串长度选择不同的比较器
     * 
     * @param isWord 是否是短语或长度很小的字符串
     */
    public static Comparator<String> createComparator() {
        return (String e1, String e2) -> {
            if (e1 == e2) return 0;
            if (e1.length() == e2.length()) {
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

    public int getCount(String word) {
        if (StringUtils.isEmpty(word)) return 0;

        int hash = isShortChar() ? WordSortedSet.fastHash(word) : word.hashCode();
        TreeMap<String, Integer> m = house[hash % initialCapacity];
        Integer count = m.get(word);
        return null == count ? 0 : count;
    }

    public boolean add(String word) {
        size++;
        int hash = isShortChar() ? WordSortedSet.fastHash(word) : word.hashCode();
        TreeMap<String, Integer> m = house[hash % initialCapacity];
        Integer count = m.get(word);
        return null != count ? m.put(word, ++count) == count : 1 == m.put(word, 1);
    }

    public int size() {
        return size;
    }

    public boolean contains(String word) {
        int hash = isShortChar() ? WordSortedSet.fastHash(word) : word.hashCode();
        TreeMap<String, Integer> m = house[hash % initialCapacity];
        Integer count = m.get(word);
        return null == count;
    }

    public TreeMap<String, Integer>[] getHouse() {
        return house;
    }

    public void setHouse(TreeMap<String, Integer>[] house) {
        this.house = house;
    }

    public boolean isShortChar() {
        return isShortChar;
    }

    public void setShortChar(boolean isShortChar) {
        this.isShortChar = isShortChar;
    }

}


