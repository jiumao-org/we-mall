package org.jiumao.mall.auth;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;


/**
 * 登陆认证服务
 * <p>
 * 一个上下文应用，登陆应该至少保证 {@link #id}或者某些字段不为空。 否则没有登陆验证
 * 
 * @author ppf@jiumao.org
 * @date 2017年10月23日
 */
@Provider
public class AuthExceptionMapper implements ExceptionMapper<AuthException> {

	@Override
	public Response toResponse(AuthException exception) {
		// TODO Auto-generated method stub
		return null;
	}

}

