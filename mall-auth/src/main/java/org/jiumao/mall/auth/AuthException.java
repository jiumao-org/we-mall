package org.jiumao.mall.auth;

import org.jiumao.common.domain.ErrorCode;




public class AuthException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	private String response = ErrorCode.NOT_AUTHED.toString();
    public String getResponse(){
        return this.response;
    }
}

