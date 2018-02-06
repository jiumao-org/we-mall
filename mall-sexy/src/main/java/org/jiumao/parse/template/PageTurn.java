package org.jiumao.parse.template;

/**
 * 网站资源页，翻页属性
 * 
 * @author Bevis-Pei<ppf@jiumao.org>
 * @date 2018/01/22
 */
public class PageTurn {

    private int begin;
    private int end;
    private int currentPage;// 用于状态保存
    private String pageMould;// baidu.com?page=news_%s&w=ppt


    private NextText text = NextText.NextCn;
    private String pathCssSelector;

    public static enum NextText {
        NextCn("下一页"), NextEn("next");
        private String text;


        private NextText(String text) {
            this.text = text;
        }


        @Override
        public String toString() {
            return text;
        }

    }

    private PageTurn() {

    }

    /**
     * 通过正则表达式翻页
     * 
     * @param begin 开始页号
     * @param end 结束页号
     * @param pageMould baidu.com?page=news_%s&w=ppt
     * @return
     */
    public static PageTurn of(int begin, int end, String pageMould) {
        PageTurn p = new PageTurn();
        p.setBegin(begin);
        p.setEnd(end);
        p.setPageMould(pageMould);
        return p;
    }

    /**
     * 通过翻页链接中文本翻页，如：下一页、next、>>
     */
    public static PageTurn of(NextText text, String css) {
        PageTurn p = new PageTurn();
        p.setText(text);
        p.setPathCssSelector(css);
        return p;
    }

    // =============================setters/getters===============================

    public int getBegin() {
        return begin;
    }

    public void setBegin(int begin) {
        this.begin = begin;
    }

    public int getEnd() {
        return end;
    }

    public void setEnd(int end) {
        this.end = end;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public String getPageMould() {
        return pageMould;
    }

    public void setPageMould(String pageMould) {
        this.pageMould = pageMould;
    }

    public NextText getText() {
        return text;
    }

    public void setText(NextText text) {
        this.text = text;
    }

    public String getPathCssSelector() {
        return pathCssSelector;
    }

    public void setPathCssSelector(String pathCssSelector) {
        this.pathCssSelector = pathCssSelector;
    }


}
