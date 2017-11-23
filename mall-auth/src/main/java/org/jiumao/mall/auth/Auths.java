package org.jiumao.mall.auth;

import org.apache.commons.lang3.StringUtils;
import org.jiumao.mall.domain.User;


public class Auths {

    /**
     * 根据token获取用户信息
     * 
     * @param token
     * @return
     */
    public static User sign(String token) {
        if (StringUtils.isEmpty(token)) {
            return null;
        } 
        return null;
    }


    /**
     * 获取接口信息，如收费等
     * 
     * @param appkey
     * @return
     */
    public static InterfaceInfo access(String appkey) {
        return null;
    }


}
