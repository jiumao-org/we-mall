package org.jiumao.parse.parser;

import java.util.List;

import org.jiumao.parse.task.UrlPool;
import org.jiumao.parse.template.Source;
import org.jiumao.parse.template.Template;
import org.jiumao.parse.template.Templates;
import org.jiumao.parse.template.Term;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.alibaba.fastjson.JSONObject;

public class UrlParser implements Parser {
    public final static UrlParser PARSER = new UrlParser();
    @Override
    public JSONObject parse(Source source, Template tmpl) {
        Document doc = Jsoup.parse(source.content);
        List<Term> terms = tmpl.getTerms();

        for (Term term : terms) {
            Elements eles = doc.select(term.getPath());
            for (Element e : eles) {
                String url = e.attr("href");
                url = Templates.toAbsUrl(source.baseUri, url);
                if (tmpl.isMatchUrl(url)) {
                    UrlPool.addUrl(url);
                }
            }
        }
        return null;
    }

}
