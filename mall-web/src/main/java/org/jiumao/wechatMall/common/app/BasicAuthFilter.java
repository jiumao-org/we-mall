package org.jiumao.wechatMall.common.app;

import java.io.IOException;
import java.nio.charset.Charset;
import java.security.Principal;
import java.util.Base64;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

/**
 * http basic 认证协议
 * @author ppf@jiumao.org
 * @date 2017年11月13日
 */
public class BasicAuthFilter implements ContainerRequestFilter {

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {

        final Charset CHARACTER_SET = Charset.forName("utf-8");

        String authHeader = requestContext.getHeaders().getFirst(HttpHeaders.AUTHORIZATION);
        if (authHeader != null && authHeader.startsWith("Basic")) {
            String decoded =
                    new String(Base64.getDecoder().decode(authHeader.substring(6).getBytes()), CHARACTER_SET);
            final String[] split = decoded.split(":");
            final String username = split[0];
            final String password = split[1];
            // FIXME: 这里要验证登陆并在请求头或者参数中加入token
            boolean verify = false;
            if (!verify) {
                requestContext.abortWith(Response.status(401).header(HttpHeaders.WWW_AUTHENTICATE, "Basic")
                    .build());
            }
            else {
                requestContext.setSecurityContext(new SecurityContext() {
                    @Override
                    public Principal getUserPrincipal() {
                        return new Principal() {
                            @Override
                            public String getName() {
                                return username;
                            }
                        };
                    }


                    @Override
                    public boolean isUserInRole(String role) {
                        return true;
                    }


                    @Override
                    public boolean isSecure() {
                        return false;
                    }


                    @Override
                    public String getAuthenticationScheme() {
                        return "BASIC";
                    }
                });
            }
        }

    }

}
