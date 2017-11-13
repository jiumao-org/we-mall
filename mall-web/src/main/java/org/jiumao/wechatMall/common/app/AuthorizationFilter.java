package org.jiumao.wechatMall.common.app;

import java.io.IOException;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;

import org.apache.commons.lang3.StringUtils;
import org.jiumao.common.domain.ErrorCode;
import org.jiumao.mall.auth.Auths;
import org.jiumao.mall.domain.User;


public class AuthorizationFilter implements ContainerRequestFilter {

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {

        // 获取客户端Header中提交的token
        String token = requestContext.getHeaderString("Authorization");
        if (StringUtils.isEmpty(token)) {
            token = (String) requestContext.getProperty("token");
        }
        User u = Auths.sign(token);
        if (null == u) {
            requestContext.abortWith(Response.status(401)
                .header(HttpHeaders.WWW_AUTHENTICATE, "Barer no auth").entity(ErrorCode.AUTH_FAILED).build());
        }
    }

}
