package org.jiumao.wechatMall.common;
/**
 * 登陆认证服务
 * @author ppf@jiumao.org
 * @date 2017年10月23日
 */
public class Authentication {

	private String id;
	private String nick;
	private String token;
	private String source;
	
	public Authentication() {
		// id为空，说明没有进行认证
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getNick() {
		return nick;
	}
	public void setNick(String nick) {
		this.nick = nick;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	
	
}
