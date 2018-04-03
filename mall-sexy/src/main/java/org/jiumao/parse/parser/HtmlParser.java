package org.jiumao.parse.parser;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.jiumao.common.utils.FileUtil;
import org.jiumao.common.utils.MixAll;
import org.jiumao.parse.task.UrlPool;
import org.jiumao.parse.template.Source;
import org.jiumao.parse.template.Template;
import org.jiumao.parse.template.Templates;
import org.jiumao.parse.template.Term;
import org.jiumao.parse.template.Type;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.alibaba.fastjson.JSONObject;


public class HtmlParser implements Parser {
    public final static HtmlParser PARSER = new HtmlParser();

    @Override
    public JSONObject parse(Source source, Template tmpl) {
        Document doc = Jsoup.parse(source.content);
        JSONObject jsonRes = new JSONObject();
        List<Term> terms = tmpl.getTerms();
        boolean pic = false;
        for (Term term : terms) {
            Elements eles = doc.select(term.getPath());
            if (1 == eles.size()) {
                Element e = eles.first();
                jsonRes.put(term.getName(), ele(term, e, source));
            } else {
                String[] res = new String[eles.size()];
                int i = 0;
                for (Element e : eles) {
                    res[i++] = ele(term, e, source);
                }
                if (term.getType() == Type.Img && eles.size() != 0) {
                    pic = true;
                }
                jsonRes.put(term.getName(), res);
            }

        }
        if (!pic) {
            System.err.println("图片采集为空：" + source.baseUri);
        }
        UrlPool.addDoneUrl(source.baseUri);
        return jsonRes;
    }

    private String ele(Term term, Element e, Source source) {
        String attr = term.getAttr();
        String v = null;
        if (StringUtils.isNotEmpty(attr)) {
            v = e.attr(attr);
        } else {
            Type t = term.getType();
            switch (t) {
                case Txt:
                    v = e.text();
                    break;
                case Img:
                    v = img(e, source);
                    break;

                default:
                    v = e.text();
                    break;
            }

        }
        return v;
    }

    private String img(Element e, Source source) {
        String v = e.attr("src");
        v = Templates.toAbsUrl(source.baseUri, v);
        String picPath = MixAll.ProjectPath + File.separator + "pics";
        String fileName;
        try {
            fileName = URLEncoder.encode(v, "utf-8");
            FileUtil.downImg(picPath, fileName, v);
        } catch (UnsupportedEncodingException e1) {
            e1.printStackTrace();
        } catch (IOException e1) {
            e1.printStackTrace();
        }

        return v;
    }

    public static void main(String[] args) throws Exception {
        String v = "https://www.dssz.com/42827.html";
        System.out.println(URLEncoder.encode(v, "utf-8"));
    }

}
