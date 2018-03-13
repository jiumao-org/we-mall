package org.jiumao.parse.template;

import java.io.IOException;
import java.net.URI;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.regex.Pattern;

import javax.annotation.Nullable;

import org.jiumao.parse.Format;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.alibaba.fastjson.JSONObject;

import kafka.utils.json.JsonObject;

public class Templates {

    private static Map<Pattern, Template> tmpls = new ConcurrentHashMap<>();
    

    public static Format<String> stringFormat = (source, params) -> {
        return String.format(source, params.toArray());
    };

    @Nullable
    public static Template getTemplate(String url) {
        Iterator<Entry<Pattern, Template>> pr = tmpls.entrySet().iterator();
        while (pr.hasNext()) {
            Map.Entry<Pattern, Template> entry = pr.next();
            if (entry.getKey().matcher(url).find()) {
                return entry.getValue();
            }
        }
        return null;
    }

    /**
     * @param pattern 正则表达式
     */
    public static Template newTemplate(String pattern) {
        Pattern r = Pattern.compile(pattern);
        Template value = new Template(pattern);
        tmpls.put(r, value);
        return value;
    }



    public static String toAbsUrl(String baseUri, String uri) {
        try {
            URI base = new URI(baseUri);
            return base.resolve(uri).toString();
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

}


