package org.jiumao.parse.parser;

import org.jiumao.parse.template.Source;
import org.jiumao.parse.template.Template;

import com.alibaba.fastjson.JSONObject;

public interface Parser {

    JSONObject parse(Source source,Template tmpl);
}
