package org.jiumao.common.utils;

/**
 * 系统级的配置文件
 * @author ppf@jiumao.org 
 * @date 2017/12/23
 */
public interface SysConfig {

    
    public static final String ZK_SERVER = System.getProperty("ZK_SERVER", "127.0.0.1:2181");
    String LINE_SEPARATOR = System.getProperty("line.separator");
}
