package org.jiumao.wechatMall.entity;
public class OauthRefreshToken {
    private java.util.Date createTime;
    private String tokenId;
    private String token;
    private String authentication;
    public OauthRefreshToken() {
        super();
    }
    public OauthRefreshToken(java.util.Date createTime,String tokenId,String token,String authentication) {
        super();
        this.createTime = createTime;
        this.tokenId = tokenId;
        this.token = token;
        this.authentication = authentication;
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

    public String getAuthentication() {
        return this.authentication;
    }

    public void setAuthentication(String authentication) {
        this.authentication = authentication;
    }

}
