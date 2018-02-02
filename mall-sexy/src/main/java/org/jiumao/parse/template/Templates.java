package org.jiumao.parse.template;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.jiumao.parse.Format;
import org.jiumao.parse.template.Resource.Result;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Template {

    public static Template toTmpl(String xml) {
        return null;
    }

    public static String toXml(Template tmpl) {

        return null;
    }

    public static Format<String> stringFormat = (source, params) -> {
        return String.format(source, params.toArray());
    };

    public static Resource createResource(String url) {
        return new Resource(url);
    }


    public static Map<String, Set<String>> getUrls(Resource source) {
        Map<String, Set<String>> urls = Collections.emptyMap();
        Connection con = Jsoup.connect(source.getUrl());
        Document doc = null;
        try {
            switch (source.getType()) {
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
            List<Term> terms = source.getTerms();

            for (Term term : terms) {
                Elements eles = doc.select(term.getPath());
                Set<String> us = new HashSet<>();
                for (Element e : eles) {
                    String url = e.absUrl("href");
                    if (source.isMatchUrl(url)) {
                        us.add(url);
                    }
                }
                urls.put(term.getName(), us);
            }
        }
        return urls;
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


