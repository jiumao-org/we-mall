package org.jiumao.parse.template;

import java.util.List;

import org.jiumao.parse.Format;

public class Term {

    private String name;
    private String formatSource;// http://%s.baidu.com?w=%s
    private List<String> params;
    private Format<?> format;
    private String path;


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



}
