package org.jiumao.wechatMall.common.app;

import java.io.IOException;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Response;

import org.apache.commons.lang3.StringUtils;
import org.jiumao.common.domain.ErrorCode;
import org.jiumao.common.domain.Msg;
import org.jiumao.mall.appkey.AppkeyDetail;
import org.jiumao.mall.auth.Auths;
import org.jiumao.mall.domain.User;
import org.jiumao.remote.client.NettyRemotingClient;
import org.jiumao.remote.exception.RemotingConnectException;
import org.jiumao.remote.exception.RemotingSendRequestException;
import org.jiumao.remote.exception.RemotingTimeoutException;
import org.jiumao.remote.service.RemotingCommand;
import org.jiumao.service.RPCServices;
import org.jiumao.wechatMall.common.domain.ResponseUtil;

/**
 * 访问次数与权限控制
 * 
 * @author ppf@jiumao.org
 * @date 2017年11月13日
 */
public class AppkeyFilter implements ContainerRequestFilter {

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {

        // 获取客户端Header中提交的token
        String appkey = requestContext.getHeaderString("appkey");
        if (StringUtils.isEmpty(appkey)) {

            Response response = ResponseUtil.defaultRes(ErrorCode.APPKEY_FAILED);
            requestContext.abortWith(response);
        }

        NettyRemotingClient client = RPCServices.getAppkeyService();
        RemotingCommand request = RemotingCommand.createRequestCommand();
        request.getExtFields().put("action", "access");

        try {
            RemotingCommand responce = client.invokeSync(request);
            Boolean isUsage = Boolean.valueOf(responce.getExtFields().get("isUsage"));
            if (!isUsage) {
                Response response = ResponseUtil.defaultRes(ErrorCode.APPKEY_FAILED);
                requestContext.abortWith(response);
            }
        } catch (RemotingConnectException | RemotingSendRequestException | RemotingTimeoutException | InterruptedException e) {
            e.printStackTrace();
            Response response = ResponseUtil.defaultRes(ErrorCode.APPKEY_FAILED);
            requestContext.abortWith(response);
        }
    }

}
