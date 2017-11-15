package org.jiumao.common.AsynHttp;

import org.apache.http.HttpResponse;
import org.apache.http.concurrent.FutureCallback;

public final class AsynHttps {
    
    public static final DoNothingListener RESPONCE_302 =  AsynHttps.newCallback();
    public static final FutureCallback<HttpResponse> DefaultCallback = new DoNothingListener();
    
    static{
        RESPONCE_302.getResult().setStatusCode(302);
    }
    
    public static DoNothingListener newCallback() {
        return new DoNothingListener();
    }
    
    //==========================================================================
    public final static String COMMA = ",";
    /** 英文分号“;” */
    public final static String SEMICOLON = ";";
    public final static String POST = "post";
    public final static String GET = "get";
    public final static String AND = "&";
    /** 英文冒号“:” */
    public final static String COLON = ":";
    public final static String QUESTION_MARK = "?";
    public final static String QUESTION_MARK_REGEX = "\\?";
    public final static String EQUAL_SIGN ="=";
    public final static String JSON ="json";
    public final static String BACKSLASH ="/";
    public final static String DOT ="."; 
    

}
