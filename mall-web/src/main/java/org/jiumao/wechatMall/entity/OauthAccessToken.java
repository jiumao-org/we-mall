package org.jiumao.wechatMall.entity;
public class OauthAccessToken {
    private java.util.Date createTime;
    private String tokenId;
    private String token;
    private String authenticationId;
    private String userName;
    private String clientId;
    private String authentication;
    private String refreshToken;
    public OauthAccessToken() {
        super();
    }
    public OauthAccessToken(java.util.Date createTime,String tokenId,String token,String authenticationId,String userName,String clientId,String authentication,String refreshToken) {
        super();
        this.createTime = createTime;
        this.tokenId = tokenId;
        this.token = token;
        this.authenticationId = authenticationId;
        this.userName = userName;
        this.clientId = clientId;
        this.authentication = authentication;
        this.refreshToken = refreshToken;
    }
    public java.util.Date getCreateTime() {
        return this.createTime;
    }

    public void setCreateTime(java.util.Date createTime) {
        this.createTime = createTime;
    }

    public String getTokenId() {
        return this.tokenId;
    }

    public void setTokenId(String tokenId) {
        this.tokenId = tokenId;
    }

    public String getToken() {
        return this.token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getAuthenticationId() {
        return this.authenticationId;
    }

    public void setAuthenticationId(String authenticationId) {
        this.authenticationId = authenticationId;
    }

    public String getUserName() {
        return this.userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getClientId() {
        return this.clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getAuthentication() {
        return this.authentication;
    }

    public void setAuthentication(String authentication) {
        this.authentication = authentication;
    }

    public String getRefreshToken() {
        return this.refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

}
