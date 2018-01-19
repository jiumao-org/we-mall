package org.mall.sexy.text;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.ansj.domain.Result;
import org.ansj.domain.Term;
import org.ansj.splitWord.analysis.ToAnalysis;
import org.jiumao.common.utils.SysConfig;


/**
 * 文章检测
 * 
 * @author Bevis-Pei<ppf@jiumao.org>
 * @date 2018/01/16
 */
public class Article {


    public static int seg = 200;

    /**
     * 分割为小段文本，提升准确率。
     * <ol>
     * <li>文章段落
     * <li>句号。
     * 
     * @param article 文本正文
     * @param length 文本最大长度
     */
    public static List<String> spiltText(String article, int length) {
        Objects.requireNonNull(article);
        length = length > 0 ? length : seg;
        List<String> list = new ArrayList<String>();
        String[] ss = article.split(SysConfig.LINE_SEPARATOR);
        for (String s : ss) {
            int splitNum = s.length() / length;
            if (splitNum < 3) {
                list.add(s);
            } else {
                if (s.contains("。")) {
                    String[] periods = s.split("。");
                    splitByStep(list, periods);

                } else if (s.contains(".")) {
                    String[] periods = s.split(".");
                    splitByStep(list, periods);
                }
            }
        }
        return list;
    }


    private static void splitByStep(List<String> list, String[] periods) {
        int lastAddIndex = 0, tmp = 0;
        StringBuilder bd = new StringBuilder((int) (seg * 1.5));
        for (int i = 0; i < periods.length; i++) {
            // 如果当前一句话很长直接加入
            if (periods[i].length() > seg) {
                bd.append(periods[i]);
                list.add(bd.toString());
                bd.delete(0, bd.length());
                lastAddIndex = i;
            }
            if (bd.length() < seg) {
                bd.append(periods[i]);
            } else {
                list.add(bd.toString());
                bd.delete(0, bd.length());
                tmp = i;
                // 保障一个句子语义连贯性，拼接上一个句子的后一半
                i = lastAddIndex + (i - lastAddIndex) / 2;
                lastAddIndex = tmp;
            }
        }
        list.add(bd.toString());
    }


    /**
     * 文章提取关键词
     * 
     * @param article 正文
     * @param map 停用词等
     * @return
     * @throws Exception
     */
    public WordSortedSet extracWord(String article, Map<String, WordSortedSet> map) {

        WordSortedSet list = new WordSortedSet();

        WordSortedSet list_c = map.get(SexyConstans.STOP_CHINESE);
        WordSortedSet list_e = map.get(SexyConstans.STOP_ENGLISH);

        Result parse = ToAnalysis.parse(article);
        for (Term term : parse) {
            String w  = term.getName().trim();
            if (list_c.contains(w)) {
                continue;
            }
            if (list_e.contains(w)) {
                continue;
            }
            list.add(w);
        }

        return list;
    }

}
