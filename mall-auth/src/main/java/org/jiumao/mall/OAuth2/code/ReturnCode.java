package org.jiumao.mall.OAuth2.code;

public class ReturnCode {
    private String code;// 表示授权码，必选项。该码的有效期应该很短，通常设为10分钟，客户端只能使用该码一次，否则会被授权服务器拒绝。该码与客户端ID和重定向URI，是一一对应关系。
    private String state;// 如果客户端的请求中包含这个参数，认证服务器的回应也必须一模一样包含这个参数。


    public String getCode() {
        return code;
    }


    public ReturnCode() {
        super();
    }


    public void setCode(String code) {
        this.code = code;
    }


    public String getState() {
        return state;
    }


    public void setState(String state) {
        this.state = state;
    }
}
