package org.jiumao.parse.template;

import org.apache.commons.lang3.StringUtils;


/**
 * 基于广度优先，抓取整站URL
 * <p>
 * 多用于文本数据如：新闻、笑话
 * 
 * @author Bevis-Pei<ppf@jiumao.org>
 * @date 2018/01/22
 */
public class MatchedUrl {

    private String startWith = "";
    private String endWith = "";
    private String contains = "";

    private String regex;// 正则表达式
    private int depth = 3;// 抓取深度，

    public MatchedUrl() {
        super();
    }

    public String getStartWith() {
        return startWith;
    }

    public void setStartWith(String startWith) {
        this.startWith = startWith;
    }

    public String getEndWith() {
        return endWith;
    }

    public void setEndWith(String endWith) {
        this.endWith = endWith;
    }

    public String getContains() {
        return contains;
    }

    public void setContains(String contains) {
        this.contains = contains;
    }

    public String getRegex() {
        return regex;
    }

    public void setRegex(String regex) {
        this.regex = regex;
    }

    public int getDepth() {
        return depth;
    }

    public void setDepth(int depth) {
        this.depth = depth;
    }

    public static boolean isMatched(MatchedUrl matched, String url) {
        if (StringUtils.isEmpty(url)) return false;
        return url.startsWith(matched.getStartWith())//
                || url.endsWith(matched.getEndWith())//
                || url.contains(matched.getContains());
    }

}
