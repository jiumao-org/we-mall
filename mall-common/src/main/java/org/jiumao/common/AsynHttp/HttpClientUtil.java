package org.jiumao.common.AsynHttp;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.jiumao.common.constants.LoggerName;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.jiumao.common.constants.ParseConstants.*;

/**
 * Http相关工具方法
 * @author ppf@jiumao.org
 * @date 2017年9月1日
 */
public final class HttpClientUtil {
    
    private static final Logger logger = LoggerFactory
            .getLogger(LoggerName.Server);


	public static Charset getEncode(String encoding) {
		try {
			if (null == encoding) {// 尽量不要抛异常
				return Charset.defaultCharset();
			}
			return Charset.isSupported(encoding) ? Charset.forName(encoding)
					: Charset.defaultCharset();
		} catch (Exception e) {// May throw IllegalCharsetNameException
			logger.error("IllegalCharsetNameException :" + encoding, e);
			return Charset.defaultCharset();
		}
	}

	/**
	 * HTTP get请求参数转为post k-v
	 * 
	 * @param httpgetParams
	 *            ？号后面的
	 */
	public static List<NameValuePair> asNameValuePair(String httpgetParams) {
		if (StringUtils.isEmpty(httpgetParams)) {
			return Collections.emptyList();
		}
		NameValuePairBuilder builder = kvBuilder();
		String[] params = httpgetParams.split(AND);
		for (String kv : params) {
			String[] kvs = kv.split(EQUAL_SIGN);
			if (2 == kvs.length) {
				builder.add(kvs[0], kvs[1]);
			}
		}
		return builder.asList();
	}

	/**
	 * HTTP请求参数k-v化
	 */
	public static NameValuePairBuilder kvBuilder() {
		return new NameValuePairBuilder();
	}

	public static class NameValuePairBuilder {
		private List<NameValuePair> ls = new ArrayList<NameValuePair>(8);

		public NameValuePairBuilder() {
		}

		public List<NameValuePair> asList() {
			return ls;
		}

		public void add(String key, String value) {
			ls.add(new BasicNameValuePair(key, value));
		}
	}
}
