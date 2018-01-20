package org.mall.parse.template;

import org.mall.parse.Format;

public class Template {

    Resource resource;

    public Template() {
        super();
    }

    public static Template toTmpl(String xml) {
        return null;
    }

    public static String toXml(Template tmpl) {

        return null;
    }

    public static Format<String> stringFormat = (source, params) -> {
        return String.format(source, params.toArray());
    };

    public static Resource createResource(String url) {
        return new Resource(url);
    }

}


