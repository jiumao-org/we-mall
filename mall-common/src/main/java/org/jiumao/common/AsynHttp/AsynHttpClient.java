package org.jiumao.common.AsynHttp;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.Future;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.concurrent.FutureCallback;
import org.apache.http.impl.nio.client.CloseableHttpAsyncClient;
import org.jiumao.common.constants.LoggerName;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 异步的HTTP请求：单例
 * 
 * @author ppf@jiumao.org
 * @date 2017年8月31日
 */
public class AsynHttpClient {
    private static final Logger logger = LoggerFactory
            .getLogger(LoggerName.Server);

	private static final CloseableHttpAsyncClient HTTP_CLIENT = AsynHttpPool.create(null);

	static {
		HTTP_CLIENT.start();
	}

	public static Future<HttpResponse> GET(String url, FutureCallback<HttpResponse> callback) {
		HttpGet get = new HttpGet(url);
		get.setHeader("accept", "*/*");
		get.setHeader("connection", "Keep-Alive");
		get.setHeader("user-agent",
				"Mozilla/5.0 (Windows NT 6.1; WOW64; rv:55.0) Gecko/20100101 Firefox/55.0");
		return HTTP_CLIENT.execute(get, callback);
	}

	public static Future<HttpResponse> POST(String uri, FutureCallback<HttpResponse> callback,
			List<NameValuePair> params, String encoding) {
		HttpPost post = new HttpPost(uri);
		post.setHeader("accept", "*/*");
		post.setHeader("connection", "Keep-Alive");
		post.setHeader("user-agent",
				"Mozilla/5.0 (Windows NT 6.1; WOW64; rv:55.0) Gecko/20100101 Firefox/55.0");
		HttpEntity entity = new UrlEncodedFormEntity(params,
				HttpClientUtil.getEncode(encoding));
		post.setEntity(entity);
		return HTTP_CLIENT.execute(post, callback);
	}

	public void close() {
		if (HTTP_CLIENT.isRunning()) {
			try {
				HTTP_CLIENT.close();
			} catch (IOException e) {
				logger.error("Asynhttpclient close failed", e);
			}
		}
	}

	/**
	 * Just in case.
	 */
	@Override
	protected void finalize() throws Throwable {
		super.finalize();
		close();
	}
}
