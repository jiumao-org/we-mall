package org.jiumao.mall.OAuth2;

import org.apache.commons.lang3.StringUtils;
import org.jiumao.mall.OAuth2.client.ClientToken;
import org.jiumao.mall.OAuth2.code.ReturnCode;
import org.jiumao.mall.OAuth2.code.CodeToken;
import org.jiumao.mall.OAuth2.implicit.ImplicitToken;
import org.jiumao.mall.OAuth2.password.PasswdToken;


public interface OAuth2Provider {

    static ReturnCode giveCode(String clientId) {
        return null;
    }


    static CodeToken hasCode(String code) {
        return null;
    }


    static ImplicitToken giveToken(String client_id) {
        // TODO Auto-generated method stub
        return null;
    }


    static PasswdToken login(String username, String password) {
        // TODO Auto-generated method stub
        return null;
    }


    static PasswdToken refresh(String client_id, String client_secret, String refresh_token) {
        // TODO Auto-generated method stub
        return null;
    }


    static ClientToken client(String client_id, String client_secret) {
        // TODO Auto-generated method stub
        return null;
    }
    

    /**
     * 根据token获取用户信息
     * 
     * @param token
     * @return
     */
    public static boolean sign(String token) {
        if (StringUtils.isEmpty(token)) {
            return false;
        } 
        return false;
    }

}
