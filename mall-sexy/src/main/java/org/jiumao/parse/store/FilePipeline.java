package org.jiumao.parse.store;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import com.alibaba.fastjson.JSONObject;

/**
 * 将数据存入文件
 * 
 * @author Bevis-Pei<ppf@jiumao.org>
 * @date 2018/03/11
 */
public class FilePipeline implements Pipeline {


    // tmp for write
    private JSONObject json;

    private String fileName;
    private FileWriter w;
    private BufferedWriter buffW;

    public FilePipeline(String fileName) throws IOException {
        super();
        this.setFileName(fileName);
        w = new FileWriter(fileName, true);
        buffW = new BufferedWriter(w);
    }



    @Override
    public void accept(JSONObject json) {
        this.json = json;
    }



    @Override
    public void write() throws IOException {
        buffW.write(json.toJSONString());
        buffW.newLine();
        buffW.flush();
    }



    public String getFileName() {
        return fileName;
    }



    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

}
