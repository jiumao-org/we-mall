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
   private int currentPage;
   private String pageMould;// baidu.com?page=news_%s&w=ppt
   private NextText text = NextText.NextCn;

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
    
    // setters/getters

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


}
