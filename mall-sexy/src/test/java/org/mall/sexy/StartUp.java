package org.mall.sexy;

import static org.junit.Assert.*;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.StringUtils;
import org.jiumao.common.AsynHttp.AbstractListener;
import org.jiumao.common.AsynHttp.AsynHttpClient;
import org.jiumao.parse.ParseConstants;
import org.jiumao.parse.store.FilePipeline;
import org.jiumao.parse.store.Sink;
import org.jiumao.parse.task.ThreadPool;
import org.jiumao.parse.task.UrlPool;
import org.jiumao.parse.template.Command;
import org.jiumao.parse.template.MatchedUrl;
import org.jiumao.parse.template.PageTurn;
import org.jiumao.parse.template.PageTurn.NextText;
import org.jiumao.parse.template.Template;
import org.jiumao.parse.template.Template.Result;
import org.jiumao.parse.template.Templates;
import org.jiumao.parse.template.Term;
import org.jiumao.parse.template.Type;

/**
 * Hello world!
 *
 */
public class StartUp {

    public static void main(String[] args) throws Exception {
        // 第一个模板
        String url = "http://www.hgh4.com/AAtupian/";

        tmpls(url);
        run(url);
    }

    private static void run(String url) throws InterruptedException {
         UrlPool.addSource(url);
        int nThreads = Runtime.getRuntime().availableProcessors();
        for (int i = 0; i < nThreads * 2; i++) {
            new Thread(new Command()).start();
            TimeUnit.SECONDS.sleep(10);
        }
    }

    private static void tmpls(String url) throws IOException {
        Template tmpl = Templates.newTemplate(url);
        MatchedUrl matched = new MatchedUrl();
         matched.setStartWith("http://www.hgh4.com/AA");
         tmpl.addMatched(matched)//
         .setResult(Result.Url)//
         .addTerm(new Term(ParseConstants.LIST, "ul > li > a[href]"));

        // // 文本翻页模板
        // String regTxt = "http://www.hgh4.com/AAbook/AAAtb/";
        // tmpl = Templates.newTemplate(regTxt);
        // matched = new MatchedUrl();
        // matched.setEndWith(".html");
        // tmpl.addMatched(matched)//
        // .setResult(Result.Page)//
        // .setPageTurn(PageTurn.of(NextText.NextCn, "div.classList a"))//
        // .addTerm(new Term(ParseConstants.LIST, "ul > li > a[href]"));
        //
        // // 文本解析模板
        // String regArticle = "http://www.hgh4.com/AAbook/[^\\\\s]*.html";
        // tmpl = Templates.newTemplate(regArticle);
        // tmpl.setResult(Result.Html)//
        // .addTerm(new Term("title", "body > div.main > div.contentList > h1 > span"))//
        // .addTerm(new Term("content", "body > div.main > div.contentList > div.content > p"))//
        // .addSink(new Sink(new FilePipeline("sexy.txt")));

        // 图片翻页模板
        String regPic = "http://www.hgh4.com/AAtupian/AAAtb/";
        tmpl = Templates.newTemplate(regPic);
        matched = new MatchedUrl();
        matched.setEndWith(".html");
        tmpl.addMatched(matched)//
                .setResult(Result.Page)//
                .setPageTurn(PageTurn.of(NextText.NextCn, "div.classList a"))//
                .addTerm(new Term(ParseConstants.LIST, "ul > li > a[href]"));

        // 图片解析模板
        String regPicContent = "http://www.hgh4.com/AAtupian/AAAwz[^\\s]*.html";
        tmpl = Templates.newTemplate(regPicContent);
        tmpl.setResult(Result.Html)//
                .addTerm(new Term("title", "body > div.main > div.contentList > h1 > span"))//
                .addTerm(new Term("pics", "img[class=zoom]").setType(Type.Img))
                .addTerm(new Term("pics2", "div.content img").setType(Type.Img));
    }

}
