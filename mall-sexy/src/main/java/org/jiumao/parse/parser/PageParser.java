package org.jiumao.parse.parser;

import java.util.List;

import org.jiumao.parse.ParseConstants;
import org.jiumao.parse.task.UrlPool;
import org.jiumao.parse.template.PageTurn;
import org.jiumao.parse.template.Source;
import org.jiumao.parse.template.Template;
import org.jiumao.parse.template.Templates;
import org.jiumao.parse.template.Term;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.alibaba.fastjson.JSONObject;

/**
 * 翻页解析
 * 
 * @author Bevis-Pei<ppf@jiumao.org>
 * @date 2018/03/12
 */
public class PageParser implements Parser {

    public static final PageParser PARSER = new PageParser();

    @Override
    public JSONObject parse(Source source, Template tmpl) {
        Document doc = Jsoup.parse(source.content);
        JSONObject jsonRes = new JSONObject();
        List<Term> terms = tmpl.getTerms();
        for (Term term : terms) {
            // 提取url
            if (ParseConstants.LIST.equalsIgnoreCase(term.getName())) {
                Elements eles = doc.select(term.getPath());
                for (Element e : eles) {
                    String url = e.attr("href");
                    url = Templates.toAbsUrl(source.baseUri, url);
                    if (tmpl.isMatchUrl(url)) {
                        UrlPool.addUrl(url);
                    }
                }
            }
        }
        // 翻页
        PageTurn page = tmpl.getPageTurn();
        Elements eles = doc.select(page.getPathCssSelector());
        for (Element e : eles) {
            String url = e.attr("href");
            url = Templates.toAbsUrl(source.baseUri, url);
            if (e.text().contains(page.getText().toString())) {
                UrlPool.addUrl(url);
                break;
            }
        }

        return null;
    }

}
