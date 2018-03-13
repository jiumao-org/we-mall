package org.jiumao.parse.template;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.jiumao.common.utils.CheckUtil;
import org.jiumao.parse.Format;
import org.jiumao.parse.store.Sink;

/**
 * json解析模板
 * @author Bevis-Pei<ppf@jiumao.org> 
 * @date 2018/03/12
 */
public class Template {
    // 网站 资源
    private List<String> params = Collections.emptyList();// url 变化参数，用于拼接字符串
    private Format<?> format = Templates.stringFormat;
    private Type type = Type.HttpGet;
    private Result result = Result.Html;
    private String regrex;

    // 资源类 抓url
    private PageTurn pageTurn;
    private List<MatchedUrl> matched;


    public List<Term> getTerms() {
        return terms;
    }

    public void setTerms(List<Term> terms) {
        this.terms = terms;
    }

    public List<Template> getResource() {
        return resource;
    }

    // 解析规则
    private List<Term> terms = Collections.emptyList();
    private List<Template> resource = Collections.emptyList();
    
    private Sink sink;

    public Template(String regrex) {
        super();
        this.regrex = regrex;
        this.params = new ArrayList<>(4);
    }

    public static enum Type {
        HttpGet, HttpPost,
        /**
         * 从上下文获取数据
         */
        Context;
    }
    public static enum Result {
        Json, Html, Text, Url,Page;
    }


    public List<String> getParams() {
        return params;
    }

    public Template setParams(List<String> params) {
        this.params = params;
        return this;
    }

    public Format<?> getFormat() {
        return format;
    }

    public Template setFormat(Format<?> format) {
        this.format = format;
        return this;
    }

    public Type getType() {
        return type;
    }

    public Template setType(Type type) {
        this.type = type;
        return this;
    }

    public Result getResult() {
        return result;
    }

    public Template setResult(Result result) {
        this.result = result;
        return this;
    }

    public Template addTerm(Term t) {
        if (terms.size() == 0) this.terms = new ArrayList<>(1);
        if (null != t) terms.add(t);
        return this;
    }

    public List<Template> addResource(Template r) {
        if (resource.size() == 0) this.resource = new ArrayList<>(1);
        if (null != r) this.resource.add(r);
        return resource;
    }

    public PageTurn getPageTurn() {
        return pageTurn;
    }

    public Template setPageTurn(PageTurn pageTurn) {
        this.pageTurn = pageTurn;
        return this;
    }

    public List<MatchedUrl> getMatched() {
        return matched;
    }

    public void setMatched(List<MatchedUrl> matched) {
        this.matched = matched;
    }

    public void setResource(List<Template> resource) {
        this.resource = resource;
    }

    public boolean isMatchUrl(String url) {
        for (MatchedUrl m : matched) {
            if (MatchedUrl.isMatched(m, url)) return true;
        }
        return false;
    }

    public Template addMatched(MatchedUrl match) {
        if (CheckUtil.isEmpty(matched)) {
            matched = new ArrayList<>(2);
        }
        matched.add(match);
        return this;
    }

    public String getRegrex() {
        return regrex;
    }

    public void setRegrex(String regrex) {
        this.regrex = regrex;
    }

    public void addSink(Sink sink) {
        this.setSink(sink);
    }

    public Sink getSink() {
        return sink;
    }

    public void setSink(Sink sink) {
        this.sink = sink;
    }

}
