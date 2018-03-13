package org.jiumao.parse.template;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.concurrent.TimeUnit;

import org.jiumao.parse.parser.HtmlParser;
import org.jiumao.parse.parser.PageParser;
import org.jiumao.parse.parser.UrlParser;
import org.jiumao.parse.store.Sink;
import org.jiumao.parse.task.UrlPool;
import org.jiumao.parse.template.Template.Result;
import org.jiumao.parse.template.Template.Type;
import org.jsoup.Jsoup;

import com.alibaba.fastjson.JSONObject;


public class Command implements Runnable {

    @Override
    public void run() {
        for (;;) {
            try {
                runTask();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    private void runTask() throws InterruptedException {
        String url = UrlPool.popUrl();
        if (url == null) {
            TimeUnit.SECONDS.sleep(10);
            if (url == null) return;
        }
        final Source source = new Source();
        source.baseUri = url;
        Template tmpl = null;
        try {
            tmpl = Templates.getTemplate(url);
            if (url.contains(".html")) {
                System.out.println(url);
            }
        } catch (Exception e) {
            System.out.println(url);
            return;
        }
        Type type = tmpl.getType();
        switch (type) {
            case HttpPost:
                break;
            case HttpGet:
                try {
                    source.content = Jsoup.connect(url).get().html();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                    System.err.println(url);
                }
                exec(source, tmpl);
                break;
            default:
                break;

        }
        
    }


    private static void exec(final Source source, Template tmpl) {
        Result res = tmpl.getResult();
        JSONObject json = null;
        switch (res) {
            case Html:
                json = HtmlParser.PARSER.parse(source, tmpl);
                break;
            case Url:
                json = UrlParser.PARSER.parse(source, tmpl);
                break;
            case Page:
                json = PageParser.PARSER.parse(source, tmpl);
                break;
            default:
                break;
        }
        save(tmpl, json);
    }


    private static void save(Template tmpl, JSONObject json) {
        Sink sink = tmpl.getSink();
        if (sink != null) {
            try {
                sink.save(json);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
