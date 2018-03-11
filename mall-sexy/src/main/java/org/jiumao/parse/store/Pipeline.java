package org.jiumao.parse.store;


/**
 * 数据存储方式，串行写
 * 
 * @author Bevis-Pei<ppf@jiumao.org>
 * @date 2018/03/11
 */
public interface Pipeline {
    void accept(String json);

    void write() throws Exception;
}
