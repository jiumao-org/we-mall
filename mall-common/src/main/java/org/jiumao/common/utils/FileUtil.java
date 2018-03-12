package org.jiumao.common.utils;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

import org.apache.commons.lang3.StringUtils;

/**
 * 文件相关操作
 * 
 * @author Bevis-Pei<ppf@jiumao.org>
 * @date 2018/03/12
 */
public class FileUtil {


    /**
     * 下载网页图片
     * 
     * @param path 保存路径
     * @param fileName 保存文件名
     * @param imgUrl 网页图片 URL
     * @throws IOException 下载或者写文件出错
     */
    public static void downImg(String path, String fileName, String imgUrl) throws IOException {

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
