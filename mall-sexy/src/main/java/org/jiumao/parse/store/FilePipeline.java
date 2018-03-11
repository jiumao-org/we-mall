package org.jiumao.parse.store;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

/**
 * 将数据存入文件
 * 
 * @author Bevis-Pei<ppf@jiumao.org>
 * @date 2018/03/11
 */
public class FilePipeline implements Pipeline {


    // tmp for write
    private String json;

    private String fileName;
    private FileWriter w;
    private BufferedWriter buffW;

    public FilePipeline(String fileName) throws IOException {
        super();
        this.fileName = fileName;
        w = new FileWriter(fileName, true);
        buffW = new BufferedWriter(w);
    }



    @Override
    public void accept(String json) {
        this.json = json;
    }



    @Override
    public void write() throws IOException {
        buffW.write(json);
        buffW.newLine();
    }

}
