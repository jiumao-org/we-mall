package org.jiumao.common.domain;

public abstract class ErrorCode {
    public static final Msg NOT_AUTHED = new Msg(400, "No Auth token.");
    public static final Msg AUTH_FAILED = new Msg(401, "Invalid token.");
    

}
