package org.mall.sexy;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.Map;
import java.util.Set;
import java.util.stream.Stream;

import org.jiumao.parse.template.MatchedUrl;
import org.jiumao.parse.template.PageTurn;
import org.jiumao.parse.template.PageTurn.NextText;
import org.jiumao.parse.template.Resource;
import org.jiumao.parse.template.Template;
import org.jiumao.parse.template.Template.Result;
import org.jiumao.parse.template.Templates;
import org.jiumao.parse.template.Term;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.junit.Before;
import org.junit.Test;

/**
 * Hello world!
 *
 */
public class App {

    public static void main(String[] args) throws Exception {
        String html = Jsoup.connect("http://www.hgh4.com/AAtupian/").get().html();
        System.out.println(html);
    }

    @Test
    public void list() throws Exception {
        String url = "http://www.hgh4.com/AAtupian/";

        Resource res = new Resource(url, Templates.getTemplate(url));
        Map<String, Set<String>> urls = Templates.getUrls(res);
        urls.forEach((k, v) -> {
            System.out.println(k);
            v.forEach((r) -> {
                System.out.println(r);
            });
        });


    }

    @Before
    public void tmpls() throws Exception {
        // 第一个模板
        String url = "http://www.hgh4.com/AAtupian/";
        Template tmpl = Templates.newTemplate(url);
        MatchedUrl matched = new MatchedUrl();
        matched.setContains("");
        tmpl.addMatched(matched)//
                .setResult(Result.Url)//
                .addTerm(new Term("list", "ul > li > a[href]"));

        // 文本翻页模板
        String regTxt = "http://www.hgh4.com/AAbook/AAAtb/";
        tmpl = Templates.newTemplate(regTxt);
        tmpl.setResult(Result.Page)//
                .setPageTurn(PageTurn.of(NextText.NextCn, "body"))//
                .addTerm(new Term("list", "ul > li > a[href]"));
        // 图片翻页模板
        String regPic = "http://www.hgh4.com/AAtupian/AAAtb/";
        tmpl = Templates.newTemplate(regPic);
        tmpl.setResult(Result.Page)//
                .setPageTurn(PageTurn.of(NextText.NextCn, "body"))//
                .addTerm(new Term("list", "ul > li > a[href]"));
    }
}
