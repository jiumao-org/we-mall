package org.mall.sexy;

import org.jiumao.parse.task.UrlPool;

public class Test {

    public static void main(String[] args) {
        addPage();

    }

    private static void addPage() {
        String[] url = {"http://www.hgh4.com/AAtupian/AAAtb/asia/index-", //
                "http://www.hgh4.com/AAtupian/AAAtb/oumei/index-", //
                "http://www.hgh4.com/AAtupian/AAAtb/zipai/index-", //
                "http://www.hgh4.com/AAtupian/AAAtb/meimei/index-", //
                "http://www.hgh4.com/AAtupian/AAAtb/meitui/index-", //
                "http://www.hgh4.com/AAtupian/AAAtb/mingxing/index-", //
                "http://www.hgh4.com/AAtupian/AAAtb/cartoon/index-", //
                "http://www.hgh4.com/AAtupian/AAAtb/luanlun/index-", //
                "http://www.hgh4.com/AAtupian/AAAtb/linglei/index-",//
        };
        for (int i = 0; i < 1000; i++) {
            for (String u : url) {
                UrlPool.addSource(u + i + ".html");
            }
        }
    }
}
