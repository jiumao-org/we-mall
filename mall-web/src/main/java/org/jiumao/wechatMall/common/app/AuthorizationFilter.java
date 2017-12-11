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
import org.jiumao.remote.client.NettyRemotingClient;
import org.jiumao.remote.exception.RemotingConnectException;
import org.jiumao.remote.exception.RemotingSendRequestException;
import org.jiumao.remote.exception.RemotingTimeoutException;
import org.jiumao.remote.service.RemotingCommand;
import org.jiumao.service.RPCServices;
import org.jiumao.wechatMall.common.domain.ResponseUtil;


public class AuthorizationFilter implements ContainerRequestFilter {

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {

        // 获取客户端Header中提交的token
        String token = requestContext.getHeaderString("Authorization");
        if (StringUtils.isEmpty(token)) {
            requestContext.abortWith(
                    Response.status(401).header(HttpHeaders.WWW_AUTHENTICATE, "Barer no auth").entity(ErrorCode.AUTH_FAILED).build());
        }
        
        // rpc
        NettyRemotingClient client = RPCServices.getAuthService();
        RemotingCommand request = RemotingCommand.createRequestCommand();
        request.getExtFields().put("action", "access");

        try {
            RemotingCommand responce = client.invokeSync(request);
            Boolean access = Boolean.valueOf(responce.getExtFields().get("access"));
            if (!access) {
                requestContext.abortWith(
                    Response.status(401).header(HttpHeaders.WWW_AUTHENTICATE, "Barer no auth").entity(ErrorCode.AUTH_FAILED).build());
            }
        } catch (RemotingConnectException | RemotingSendRequestException | RemotingTimeoutException | InterruptedException e) {
            requestContext.abortWith(
                    Response.status(401).header(HttpHeaders.WWW_AUTHENTICATE, "Barer no auth").entity(ErrorCode.AUTH_FAILED).build());
        }
    }

}
