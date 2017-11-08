package org.jiumao.mall.OAuth2.domain;

/**
 * GET /authorize?response_type=code&client_id=s6BhdRkqt3&state=xyz
 * &redirect_uri=https%3A%2F%2Fclient%2Eexample%2Ecom%2Fcb HTTP/1.1 Host:
 * server.example.com
 * 
 * @author ppf@jiumao.org
 * @date 2017年11月6日
 */
public class Authorize {
    private String response_type;// 表示授权类型，必选项，此处的值固定为"code"
    private String client_id;// 表示客户端的ID，必选项
    private String redirect_uri;// 表示重定向URI，可选项
    private String scope;// 表示申请的权限范围，可选项
    private String state;// 表示客户端的当前状态，可以指定任意值，认证服务器会原封不动地返回这个值。


    public Authorize() {
        super();
    }


    public String getResponse_type() {
        return response_type;
    }


    public void setResponse_type(String response_type) {
        this.response_type = response_type;
    }


    public String getClient_id() {
        return client_id;
    }


    public void setClient_id(String client_id) {
        this.client_id = client_id;
    }


    public String getRedirect_uri() {
        return redirect_uri;
    }


    public void setRedirect_uri(String redirect_uri) {
        this.redirect_uri = redirect_uri;
    }


    public String getScope() {
        return scope;
    }


    public void setScope(String scope) {
        this.scope = scope;
    }


    public String getState() {
        return state;
    }


    public void setState(String state) {
        this.state = state;
    }
}
