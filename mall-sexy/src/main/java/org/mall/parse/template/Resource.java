package org.mall.parse.template;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.mall.parse.Format;

public class Resource {
    private String source;// http url 或者 其它数据源
    private List<String> params;// url 变化参数，用于拼接字符串
    private Format<?> format;
    private Type type;
    private Result result;
    private boolean isPage;

    private List<Term> terms = Collections.emptyList();
    private List<Resource> resource = Collections.emptyList();

    public Resource(String source) {
        super();
        this.source = source;
        this.resource = new ArrayList<>(1);
    }

    public static enum Type {
        HttpGet, HttpPost,
        /**
         * 从上下文获取数据
         */
        Context;
    }
    public static enum Result {
        Json, Html, Text;
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

    public boolean isPage() {
        return isPage;
    }

    public void setPage(boolean isPage) {
        this.isPage = isPage;
    }

    public List<Term> addTerm(Term t) {
        if (null != t) terms.add(t);
        return terms;
    }

    public List<Resource> addResource(Resource r) {
        if (null != r) this.resource.add(r);
        return resource;
    }

}
