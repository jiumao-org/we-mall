package org.jiumao.wechatMall.common.auth;

import org.jiumao.wechatMall.common.domain.ErrorCode;


public class AuthException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	private String response = ErrorCode.NOT_AUTHED.getMsg();
    public String getResponse(){
        return this.response;
    }
}

