package org.jiumao.parse.template;

public class Resource {

    private String url;
    private String source;
    private Template template;

    public Resource(String url, Template template) {
        super();
        this.url = url;
        this.template = template;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public Template getTemplate() {
        return template;
    }

    public void setTemplate(Template template) {
        this.template = template;
    }


}
