package org.jiumao.wechatMall.common.app;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.jiumao.mall.auth.AuthException;


/**
 * 登陆认证服务
 * <p>
 * 一个上下文应用，登陆应该至少保证token或者某些字段不为空。 否则没有登陆验证
 * 
 * @author ppf@jiumao.org
 * @date 2017年10月23日
 */
@Provider
public class AuthExceptionMapper implements ExceptionMapper<AuthException> {

	@Override
	public Response toResponse(AuthException exception) {
		return Response.ok(exception.getResponse()).build();
	}

}

