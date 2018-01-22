package org.mall.parse.template;

/**
 * 网站资源页，翻页属性
 * 
 * @author Bevis-Pei<ppf@jiumao.org>
 * @date 2018/01/22
 */
public class PageTurn {

    int begin;
    int end;


    enum NextText {
        NextCn("下一页"),NextEn("next");
        private String text;


        private NextText(String text) {
            this.text = text;
        }


        @Override
        public String toString() {
            return text;
        }

    }


}
