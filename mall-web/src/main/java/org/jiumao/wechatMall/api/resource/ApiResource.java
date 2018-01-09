package org.jiumao.wechatMall.api.resource;

import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.jiumao.common.constants.LoggerName;
import org.jiumao.common.domain.Msg;
import org.jiumao.common.utils.JsonUtil;
import org.jiumao.mall.appkey.AppkeyDetail;
import org.jiumao.remote.client.NettyRemotingClient;
import org.jiumao.remote.exception.RemotingConnectException;
import org.jiumao.remote.exception.RemotingSendRequestException;
import org.jiumao.remote.exception.RemotingTimeoutException;
import org.jiumao.remote.service.RemotingCommand;
import org.jiumao.service.RPCServices;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Path("/api")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ApiResource {

    private static final Logger log = LoggerFactory.getLogger(LoggerName.Status);

    @GET
    @Path("appkey")
    public Msg getAppkey(AppkeyDetail detail) {

        NettyRemotingClient client = RPCServices.getAppkeyService();
        RemotingCommand request = RemotingCommand.createRequestCommand();
        request.setBody(JsonUtil.toBytes(detail));
        request.getExtFields().put("action", "insert");

        try {
            RemotingCommand responce = client.invokeSync(request);
            return new Msg(responce.getExtFields().get("appkey"), "appkey");
        } catch (RemotingConnectException | RemotingSendRequestException | RemotingTimeoutException | InterruptedException e) {
            e.printStackTrace();
            log.error("",e);
        }
        return new Msg(-1, "appkey");
    }
}
