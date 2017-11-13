package org.jiumao.common.AsynHttp;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
    private static final Logger logger = LoggerFactory.getLogger(LoggerName.Server);
    
    private static final Map<String, String> DefaultHttpHeaders = new HashMap<String, String>();
    static{
        DefaultHttpHeaders.put("accept", "*/*");
        DefaultHttpHeaders.put("Accept-Encoding", "gzip, deflate, sdch");
        DefaultHttpHeaders.put("Accept-Language", "zh-CN,zh;q=0.8");
        DefaultHttpHeaders.put("Connection", "keep-alive");
        DefaultHttpHeaders.put("Cache-Control", "max-age=0");
        DefaultHttpHeaders.put("Upgrade-Insecure-Requests", "1");
        DefaultHttpHeaders.put("user-agent",
                "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/49.0.2623.87 Safari/537.36 Gecko/20100101 Firefox/55.0");
    }
    
    public static void addDefaultHttpHeader(String key,String value){
        DefaultHttpHeaders.put(key, value);
    }

    private static final CloseableHttpAsyncClient HTTP_CLIENT = AsynHttpPool.create(null);

    static {
        HTTP_CLIENT.start();
    }


    public static Future<HttpResponse> GET(String url, FutureCallback<HttpResponse> callback) {
        return GET(url, callback, DefaultHttpHeaders);
    }


    public static Future<HttpResponse> GET(String url, FutureCallback<HttpResponse> callback,
            Map<String, String> headers) {
        HttpGet get = new HttpGet(url);
        headers.forEach((key, value) -> {
            get.setHeader(key, value);
        });
        return HTTP_CLIENT.execute(get, callback);
    }


    public static Future<HttpResponse> POST(String url, FutureCallback<HttpResponse> callback,
            List<NameValuePair> params, String encoding, Map<String, String> headers) {
        HttpPost post = new HttpPost(url);
        headers.forEach((key, value) -> {
            post.setHeader(key, value);
        });
        HttpEntity entity = new UrlEncodedFormEntity(params, HttpClientUtil.getEncode(encoding));
        post.setEntity(entity);
        return HTTP_CLIENT.execute(post, callback);
    }


    public static Future<HttpResponse> POST(String url, FutureCallback<HttpResponse> callback,
            List<NameValuePair> params, String encoding) {
        return POST(url, callback, params, encoding, DefaultHttpHeaders);
    }


    public void close() {
        if (HTTP_CLIENT.isRunning()) {
            try {
                HTTP_CLIENT.close();
            }
            catch (IOException e) {
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
