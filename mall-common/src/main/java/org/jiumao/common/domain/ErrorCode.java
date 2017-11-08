package org.jiumao.common.domain;

public class ErrorCode {
    public static final Msg AUTH_FAILED = new Msg(-1, "Auth failed");
    public static final Msg NOT_AUTHED = new Msg(0, "No Auth");


    private ErrorCode() {
    }

}
