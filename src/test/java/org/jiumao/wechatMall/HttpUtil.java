package org.jiumao.wechatMall;


import sun.misc.BASE64Encoder;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

/**
 * 简单的http类
 * 
 * @author ppf
 * @since 2017年7月12日
 */
@SuppressWarnings("restriction")
public final class HttpUtil {
    /** jdk <= 7 */
	public static final BASE64Encoder BASE64 = new BASE64Encoder();
    public static final byte[] EMPTY_ABYTE = new byte[0];

    public static String sendGet(String url, String param) throws Exception {
        return sendGet(url, param, null, null);
    }

    public static String sendGet(String url, String param, String auth) throws Exception {
        return sendGet(url, param, null, null);
    }


    public static String sendPost(String url, String param) throws Exception {
        return sendPost(url, param, null, null);
    }

    public static String sendPost(String url, String param, String auth) throws Exception {
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
        URL realUrl = new URL(url + "?" + param);
        URLConnection conn = realUrl.openConnection();
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
    
    
    
    private static void buliderConnectionHeader(URLConnection connection) {
        connection.setRequestProperty("accept", "*/*");
        connection.setRequestProperty("connection", "Keep-Alive");
        connection.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
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
