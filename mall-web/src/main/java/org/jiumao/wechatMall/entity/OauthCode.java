package org.jiumao.wechatMall.entity;
public class OauthCode {
    private java.util.Date createTime;
    private String code;
    private String authentication;
    public OauthCode() {
        super();
    }
    public OauthCode(java.util.Date createTime,String code,String authentication) {
        super();
        this.createTime = createTime;
        this.code = code;
        this.authentication = authentication;
    }
    public java.util.Date getCreateTime() {
        return this.createTime;
    }

    public void setCreateTime(java.util.Date createTime) {
        this.createTime = createTime;
    }

    public String getCode() {
        return this.code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getAuthentication() {
        return this.authentication;
    }

    public void setAuthentication(String authentication) {
        this.authentication = authentication;
    }

}
