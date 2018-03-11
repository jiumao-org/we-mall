package org.jiumao.parse.store;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import org.apache.commons.lang3.StringUtils;
import org.codehaus.jackson.map.ObjectMapper;
import org.jiumao.common.utils.JsonUtil;
import org.jiumao.common.utils.JsonWraper;

/**
 * 图片转存到本地
 * <p>
 * json字符串必须包含 <code>path，fileName，imgUrl</code>
 * </p>
 * 
 * @author Bevis-Pei<ppf@jiumao.org>
 * @date 2018/03/11
 */
public class ImagePipeline implements Pipeline {

    private static ObjectMapper m = new ObjectMapper();
    private JsonWraper wraper;

    @Override
    public void accept(String json) {
        wraper = JsonWraper.of(JsonUtil.jsonTree(m, json));
    }

    @Override
    public void write() throws Exception {
        String path = wraper.getValue("path", "");
        String fileName = wraper.getValue("fileName", "");
        String imgUrl = wraper.getValue("imgUrl", "");

        if (StringUtils.isEmpty(path)//
                || StringUtils.isEmpty(fileName)//
                || StringUtils.isEmpty(imgUrl)//
        ) {
            return;
        }

        File dir = new File(path);
        if (!dir.exists()) dir.mkdirs();

        File file = new File(path + File.separator + fileName);
        URL url = new URL(imgUrl);
        URLConnection connection = url.openConnection();
        connection.setConnectTimeout(10 * 1000);
        InputStream in = connection.getInputStream();
        BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(file));
        byte[] buf = new byte[1024];
        int size;
        while (-1 != (size = in.read(buf))) {
            out.write(buf, 0, size);
        }
        out.close();
        in.close();
    }

}
