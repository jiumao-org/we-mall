package org.mall.parse.template;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.mall.parse.Format;

public class Resource {
    // 网站 资源
    private String source;// http url 或者 其它数据源
    private List<String> params;// url 变化参数，用于拼接字符串
    private Format<?> format;
    private Type type;
    private Result result;
    
    // 资源类 抓url
    private PageTurn pageTurn;
    private MatchedUrl matched;
    

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

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public List<String> getParams() {
        return params;
    }

    public void setParams(List<String> params) {
        this.params = params;
    }

    public Format<?> getFormat() {
        return format;
    }

    public void setFormat(Format<?> format) {
        this.format = format;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
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

    public void setPageTurn(PageTurn pageTurn) {
        this.pageTurn = pageTurn;
    }

    public MatchedUrl getMatched() {
        return matched;
    }

    public void setMatched(MatchedUrl matched) {
        this.matched = matched;
    }

}
