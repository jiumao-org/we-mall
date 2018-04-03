package org.jiumao.parse.template;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.StringUtils;
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
            return;
        }

        final Source source = new Source();
        source.baseUri = url;
        Template tmpl = Templates.getTemplate(url);
        if (url.contains(".html")) System.out.println("采集的网页" + url);
        if (tmpl == null) return;

        downSource(source, tmpl);
        if (StringUtils.isEmpty(source.content)) {
            return;
        }

        exec(source, tmpl);
    }


    private void downSource(final Source source, Template tmpl) {
        Type type = tmpl.getType();
        try {
            switch (type) {
                case HttpPost:
                    break;
                case HttpGet:
                    source.content = Jsoup.connect(source.baseUri).get().html();
                    break;
                default:
                    break;
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println(source.baseUri);
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
