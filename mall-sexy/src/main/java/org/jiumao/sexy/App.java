package org.jiumao.sexy;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.Map;
import java.util.Set;
import java.util.stream.Stream;

import org.jiumao.parse.template.MatchedUrl;
import org.jiumao.parse.template.Resource;
import org.jiumao.parse.template.Resource.Result;
import org.jiumao.parse.template.Template;
import org.jiumao.parse.template.Term;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
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
        url(url);
    }

    private void url(String url) {

        Resource source = Template.createResource(url);
        MatchedUrl matched = new MatchedUrl();
        matched.setContains("");
        source.addMatched(matched)//
                .setResult(Result.Url)//
                .addTerm(new Term("list", "ul > li > a[href]"));
        Map<String, Set<String>> urls = Template.getUrls(source);
        urls.forEach((k, v) -> {
            System.out.println(k);
            v.forEach((r) -> {
                System.out.println(r);
            });
        });
    }

}
