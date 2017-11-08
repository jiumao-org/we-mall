package org.jiumao.mall.domain;

public class Authentication {

    private String grant_type;// 表示授权类型
    private String username;// 表示用户名，必选项。
    private String password;// 表示用户的密码，必选项。
    private String scope;// 表示权限范围，可选项。
    private String code;// 表示上一步获得的授权码，必选项。
    private String redirect_uri;// 表示重定向URI，必选项，且必须与A步骤中的该参数值保持一致。
    private String client_id;// 表示客户端ID，必选项
    private String client_secret;//应用注册时获得的client secret
    private String refresh_token;// 表示早前收到的更新令牌，必选项。


    public Authentication() {
        super();
    }


    public String getGrant_type() {
        return grant_type;
    }


    public void setGrant_type(String grant_type) {
        this.grant_type = grant_type;
    }


    public String getUsername() {
        return username;
    }


    public void setUsername(String username) {
        this.username = username;
    }


    public String getPassword() {
        return password;
    }


    public void setPassword(String password) {
        this.password = password;
    }


    public String getScope() {
        return scope;
    }


    public void setScope(String scope) {
        this.scope = scope;
    }


    public String getCode() {
        return code;
    }


    public void setCode(String code) {
        this.code = code;
    }


    public String getRedirect_uri() {
        return redirect_uri;
    }


    public void setRedirect_uri(String redirect_uri) {
        this.redirect_uri = redirect_uri;
    }


    public String getClient_id() {
        return client_id;
    }


    public void setClient_id(String client_id) {
        this.client_id = client_id;
    }


    public String getRefresh_token() {
        return refresh_token;
    }


    public void setRefresh_token(String refresh_token) {
        this.refresh_token = refresh_token;
    }


    public String getClient_secret() {
        return client_secret;
    }


    public void setClient_secret(String client_secret) {
        this.client_secret = client_secret;
    }

}
