
package org.jiumao.sexy;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

import org.apache.commons.codec.binary.Base64InputStream;
import org.bson.internal.Base64;

public class Base64File {

    @SuppressWarnings("resource")
    public static void main(String[] args) throws Exception {
        String dir = "D:\\Users\\Administrator\\eclipse-workspace\\w4java\\wechat-mall\\mall-sexy\\src\\main\\resources\\";
        String fileName=dir+"pub_banned_words.txt";
        BufferedReader r1 = new BufferedReader(new  FileReader(fileName));
        String fileOut = dir+"1.txt";
        BufferedWriter w1 = new BufferedWriter(new FileWriter(fileOut));
         
        r1.lines().forEach((word)->{
            byte[] bs =Base64.decode(word);
            try {
                w1.write(new String(bs));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        fileName=dir+"pub_sms_banned_words.txt";
        BufferedReader r2 = new BufferedReader(new  FileReader(fileName));
        fileOut = dir+"2.txt";
        BufferedWriter w2 = new BufferedWriter(new FileWriter(fileOut));
        r2.lines().forEach((word)->{
            byte[] bs =Base64.decode(word);
            try {
                w2.write(new String(bs));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }); 
    }
}
