package org.jiumao.parse.template;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.jiumao.parse.Format;

public class Resource {
    // 网站 资源
    private String source;// http url 或者 其它数据源
    private List<String> params = Collections.emptyList();// url 变化参数，用于拼接字符串
    private Format<?> format = Template.stringFormat;
    private Type type = Type.HttpGet;
    private Result result = Result.Html;

    // 资源类 抓url
    private PageTurn pageTurn;
    private MatchedUrl matched;


    public List<Term> getTerms() {
        return terms;
    }

    public void setTerms(List<Term> terms) {
        this.terms = terms;
    }

    public List<Resource> getResource() {
        return resource;
    }

    // 解析规则
    private List<Term> terms = Collections.emptyList();
    private List<Resource> resource = Collections.emptyList();

    public Resource(String source) {
        super();
        this.source = source;
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
        Json, Html, Text, Url;
    }

    public String getUrl() {
        return source;
    }

    public Resource setSource(String source) {
        this.source = source;
        return this;
    }

    public List<String> getParams() {
        return params;
    }

    public Resource setParams(List<String> params) {
        this.params = params;
        return this;
    }

    public Format<?> getFormat() {
        return format;
    }

    public Resource setFormat(Format<?> format) {
        this.format = format;
        return this;
    }

    public Type getType() {
        return type;
    }

    public Resource setType(Type type) {
        this.type = type;
        return this;
    }

    public Result getResult() {
        return result;
    }

    public Resource setResult(Result result) {
        this.result = result;
        return this;
    }

    public List<Term> addTerm(Term t) {
        if (terms.size() == 0) this.terms = new ArrayList<>(1);
        if (null != t) terms.add(t);
        return terms;
    }

    public List<Resource> addResource(Resource r) {
        if (resource.size() == 0) this.resource = new ArrayList<>(1);
        if (null != r) this.resource.add(r);
        return resource;
    }

    public PageTurn getPageTurn() {
        return pageTurn;
    }

    public Resource setPageTurn(PageTurn pageTurn) {
        this.pageTurn = pageTurn;
        return this;
    }

    public MatchedUrl getMatched() {
        return matched;
    }

    public Resource setMatched(MatchedUrl matched) {
        this.matched = matched;
        return this;
    }


}
