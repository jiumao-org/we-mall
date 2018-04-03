package org.jiumao.common.utils;

import com.alibaba.fastjson.JSONObject;

import sun.misc.BASE64Encoder;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;
import java.util.Set;

import org.apache.commons.lang3.RandomUtils;
import org.jiumao.common.constants.LoggerName;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 简单的http类
 * 
 * @author ppf
 * @since 2017年7月12日
 */
public final class HttpUtil {
    private static final Logger log = LoggerFactory
            .getLogger(LoggerName.Error);
    /** jdk <= 7 */
    public static final BASE64Encoder BASE64 = new BASE64Encoder();
    public static final byte[] EMPTY_ABYTE = new byte[0];

    public static String sendGet(String url, String param) throws Exception {
        return sendGet(url, param, null, null);
    }

    public static String sendGet(String url, String param, String auth) throws Exception {
        return sendGet(url, param, null, null);
    }

    /**
     * 兼容处理，最好不要使用
     * 
     * @see #sendGet(String, String, String)
     */
    public static String sendGet(String url, String param, JSONObject auth) throws Exception {
        if (auth != null && auth.containsKey("username") && auth.containsKey("password")) {
            String login = auth.get("username") + ":" + auth.get("password");
            return sendGet(url, param, login, null);
        }
        return sendGet(url, param, null, null);
    }

    public static String sendPost(String url, String param) throws Exception {
        return sendPost(url, param, null, null);
    }

    public static String sendPost(String url, String param, String auth) throws Exception {
        return sendPost(url, param, null, null);
    }

    /**
     * 兼容处理，最好不要使用
     * 
     * @see #sendPost(String, String, String)
     */
    public static String sendPost(String url, String param, JSONObject auth) throws Exception {
        if (auth != null && auth.containsKey("username") && auth.containsKey("password")) {
            String login = auth.get("username") + ":" + auth.get("password");
            return sendPost(url, param, login, null);
        }
        return sendPost(url, param, null, null);
    }


    /**
     * POST请求
     * 
     * @param url 发送请求的 URL
     * @param param 请求应该与目标协议一致,name1=value1&name2=value2
     * @param auth basic认证信息 username:password
     * @param info k-v 加入浏览器需要的信息
     * @return 可能为空
     */
    public static String sendPost(String url, String param, String auth, Map<String, String> info) throws Exception {
        // 1.打开连接
        HttpURLConnection conn = (HttpURLConnection) new URL(url).openConnection();
        buliderConnectionHeader(conn);
        addAuthInfo(conn, null == auth ? EMPTY_ABYTE : auth.getBytes());
        addHttpInfo(conn, info);
        // 发送POST请求必须设置如下两行
        conn.setDoOutput(true);
        conn.setDoInput(true);
        conn.setRequestMethod("POST");
        conn.connect();

        // 2.设置请求参数
        PrintWriter out = new PrintWriter(conn.getOutputStream());
        out.print(param);
        out.flush();

        // 3.接受返回数据
        BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        String line, result = "";
        while ((line = in.readLine()) != null) {
            result += line;
        }
        out.close();
        in.close();
        conn.disconnect();
        return result;
    }

    /**
     * 向指定URL发送GET方法的请求
     * 
     * @param url 发送请求的URL
     * @param param 请求参数，形式 name1=value1&name2=value2
     * @param auth basic认证信息 username:password
     * @param info k-v 加入浏览器需要的信息
     * @return URL 响应结果，可能为null
     */
    public static String sendGet(String url, String param, String auth, Map<String, String> info) throws Exception {
        // 1.打开连接
        HttpURLConnection conn = (HttpURLConnection) new URL(url).openConnection();
        buliderConnectionHeader(conn);
        addAuthInfo(conn, null == auth ? EMPTY_ABYTE : auth.getBytes());
        addHttpInfo(conn, info);
        conn.connect();

        // 2.获取响应数据
        BufferedReader in = new BufferedReader(//
                new InputStreamReader(conn.getInputStream()));
        String line, result = "";
        while ((line = in.readLine()) != null)
            result += line;
        in.close();
        return result;
    }

    public static void downImg(String url, String file, String path) throws IOException {
        // 1.打开连接
        HttpURLConnection conn = (HttpURLConnection) new URL(url).openConnection();
        buliderConnectionHeader(conn);
        conn.setRequestMethod("GET");

        conn.setConnectTimeout(10 * 1000);InputStream in = null;
        try {
            in = conn.getInputStream();
        } catch (FileNotFoundException e) {
            log.error("图片抓取出错:"+url, e);  
            System.err.println(url);
        }
        BufferedOutputStream out = null;
        try {
            out = new BufferedOutputStream(new FileOutputStream(path + File.separator + file));
        } catch (FileNotFoundException e) {
            int tail = RandomUtils.nextInt(0, 1000);
            long fileNum = System.currentTimeMillis() * 1000 + tail;
            out = new BufferedOutputStream(new FileOutputStream(path + File.separator + fileNum + ".jpg"));
        }
        byte[] buf = new byte[1024];
        int size;
        while (-1 != (size = in.read(buf))) {
            out.write(buf, 0, size);
        }
        out.close();
        in.close();
    }

    private static void buliderConnectionHeader(URLConnection connection) {
        connection.setRequestProperty("accept", "*/*");
        connection.setRequestProperty("Accept-Encoding", "gzip, deflate, sdch");
        connection.setRequestProperty("Accept-Language", "zh-CN,zh;q=0.8");
        connection.setRequestProperty("Connection", "keep-alive");
        connection.setRequestProperty("Cache-Control", "max-age=0");
        connection.setRequestProperty("Upgrade-Insecure-Requests", "1");
        connection.setRequestProperty("user-agent",
                "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/49.0.2623.87 Safari/537.36 Gecko/20100101 Firefox/55.0");

    }


    private static void addAuthInfo(URLConnection conn, byte[] auth) {
        String authInfo = "Basic " + BASE64.encode(auth);
        conn.addRequestProperty("Authorization", authInfo);
    }

    private static void addHttpInfo(URLConnection conn, Map<String, String> info) {
        if (null == info) return;
        Set<Entry<String, String>> attr = info.entrySet();
        for (Entry<String, String> a : attr) {
            conn.addRequestProperty(a.getKey(), a.getValue());
        }
    }

}
