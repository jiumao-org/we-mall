package org.mall.parse.template;

import java.util.List;

import org.mall.parse.Format;

public class Term {
    
    private String name;
    private String formatSource;
    private List<String> params; 
    private Format<?> format;
    
    
    public Term(String name, String formatSource) {
        super();
        this.name = name;
        this.formatSource = formatSource;
        this.format = Template.stringFormat;
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
