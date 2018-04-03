package org.jiumao.common.utils;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

import org.apache.commons.lang3.StringUtils;
import org.jsoup.Connection;
import org.jsoup.Jsoup;

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

        HttpUtil.downImg(imgUrl, fileName, path);

    }
}
