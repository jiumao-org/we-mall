package org.jiumao.parse.template;

import java.io.IOException;
import java.net.URI;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.regex.Pattern;

import javax.annotation.Nullable;

import org.jiumao.parse.Format;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Templates {

    private static Map<Pattern, Template> tmpls = new ConcurrentHashMap<>();

    public static Templates toTmpl(String xml) {
        return null;
    }

    public static String toXml(Templates tmpl) {
        return null;
    }

    public static Format<String> stringFormat = (source, params) -> {
        return String.format(source, params.toArray());
    };

    @Nullable
    public static Template getTemplate(String url) {
        Iterator<Entry<Pattern, Template>> pr = tmpls.entrySet().iterator();
        while (pr.hasNext()) {
            Map.Entry<Pattern, Template> entry = pr.next();
            if (entry.getKey().matcher(url).find()) {
                return entry.getValue();
            }
        }
        return null;
    }

    /**
     * @param pattern 正则表达式
     */
    public static Template newTemplate(String pattern) {
        Pattern r = Pattern.compile(pattern);
        Template value = new Template(pattern);
        tmpls.put(r, value);
        return value;
    }


    public static Map<String, Set<String>> getUrls(Resource source) {
        Map<String, Set<String>> urls = Collections.emptyMap();
        Connection con = Jsoup.connect(source.getUrl());
        Template tmpl = source.getTemplate();
        Document doc = null;
        try {
            switch (tmpl.getType()) {
                case HttpGet:
                    doc = con.get();
                    break;

                case HttpPost:
                    doc = con.post();
                    break;
                default:
                    break;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (doc != null) {
            urls = new HashMap<>(2);
            List<Term> terms = tmpl.getTerms();

            for (Term term : terms) {
                Elements eles = doc.select(term.getPath());
                Set<String> us = new HashSet<>();
                for (Element e : eles) {
                    String url = e.absUrl("href");
                    if (tmpl.isMatchUrl(url)) {
                        us.add(url);
                    }
                }
                urls.put(term.getName(), us);
            }
        }
        return urls;
    }
    
    
    public static void getPages(Resource source) {
        
    }

    public static String toAbsUrl(String baseUri, String uri) {
        try {
            URI base = new URI(baseUri);
            return base.resolve(uri).toString();
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

}


