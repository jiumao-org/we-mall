package org.jiumao.wechatMall.common.auth;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 登陆认证服务
 * <p>
 * 一个上下文应用，登陆应该至少保证 {@link #id}或者某些字段不为空。 否则没有登陆验证
 * 
 * @author ppf@jiumao.org
 * @date 2017年10月23日
 */
public class Authorization {
	private static final long serialVersionUID = 1L;

	private String id;
	private String nick;
	private String token;
	private String source;

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
