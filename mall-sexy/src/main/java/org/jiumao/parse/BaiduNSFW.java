package org.jiumao.parse;

import org.json.JSONObject;

import com.baidu.aip.imagecensor.AipImageCensor;

/**
 * Baidu反色情图片接口
 * 
 * @author Bevis-Pei<ppf@jiumao.org>
 * @date 2018/03/14
 */
public class BaiduNSFW {
    // 设置APPID/AK/SK
    public static final String APP_ID = "10924997";
    public static final String API_KEY = "XfrdRXkmD3erxbRCW4yb9st6";
    public static final String SECRET_KEY = "9GS4QBWQhQHHoltmxgr2DUf0Q6jZLwYx";
    public static final String TOKEN_URL =
            "https://aip.baidubce.com/oauth/2.0/token?grant_type=client_credentials&client_id=" + API_KEY + "&client_secret=" + SECRET_KEY;

    public static void main(String[] args) {
        AipImageCensor client = new AipImageCensor(APP_ID, API_KEY, SECRET_KEY);

        client.setConnectionTimeoutInMillis(2000);
        client.setSocketTimeoutInMillis(60000);

        System.setProperty("aip.log4j.conf", "path/to/your/log4j.properties");

        // 调用接口
        String path = "test.jpg";
        JSONObject res = client.antiPorn(path);
        System.out.println(res.toString(2));


    }

    public static final String SEXY_API = "https://aip.baidubce.com/rest/2.0/antiporn/v1/detect";
    public static final String SEXY_GIF_API = " https://aip.baidubce.com/rest/2.0/antiporn/v1/detect_gif";
    public static final String ILLEGAL_API = " https://aip.baidubce.com/api/v1/solution/direct/img_censor";
    public static final String FACE_ICON_API = " https://aip.baidubce.com/rest/2.0/solution/v1/face_audit";

    public static void detectSexy(String base64Img) {

    }
}
