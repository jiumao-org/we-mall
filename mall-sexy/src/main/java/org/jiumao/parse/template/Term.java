package org.jiumao.parse.template;

import java.util.List;

import org.jiumao.parse.Format;

public class Term {

    private String name;
    private String formatSource;// http://%s.baidu.com?w=%s
    private List<String> params;
    private Format<?> format;
    private String path;
    private String attr;// html标签 属性
    private Type type = Type.Txt;


    public Term(String name, String path) {
        super();
        this.name = name;
        this.path = path;
        this.format = Templates.stringFormat;
    }

    public String getPath() {
        return path;
    }


    public void setPath(String path) {
        this.path = path;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFormatSource() {
        return formatSource;
    }

    public void setFormatSource(String formatSource) {
        this.formatSource = formatSource;
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

    public String getAttr() {
        return attr;
    }

    public void setAttr(String attr) {
        this.attr = attr;
    }

    public Type getType() {
        return type;
    }

    public Term setType(Type type) {
        this.type = type;
        return this;
    }

}
