package org.jiumao.mall.db;

/**
 * Created by 13682 on 2017/11/14.
 */
public interface ExtractTokenKeyDigester {
    String digest(String tokenValue);
}
