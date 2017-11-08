package org.jiumao.wechatMall.api.resource;

import java.net.URI;

import javax.annotation.Resource;
import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.HEAD;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.CacheControl;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.NewCookie;
import javax.ws.rs.core.Response;

import org.jiumao.common.constants.LoggerName;
import org.jiumao.common.domain.ErrorCode;
import org.jiumao.common.domain.Msg;
import org.jiumao.common.utils.JsonUtil;
import org.jiumao.mall.OAuth2.OAuth2Provider;
import org.jiumao.mall.OAuth2.client.ClientToken;
import org.jiumao.mall.OAuth2.code.ReturnCode;
import org.jiumao.mall.OAuth2.code.CodeToken;
import org.jiumao.mall.OAuth2.domain.Authorize;
import org.jiumao.mall.OAuth2.domain.GrantType;
import org.jiumao.mall.OAuth2.domain.ResponseType;
import org.jiumao.mall.OAuth2.implicit.ImplicitToken;
import org.jiumao.mall.OAuth2.password.PasswdToken;
import org.jiumao.mall.auth.AuthAnnotation;
import org.jiumao.mall.domain.Authentication;
import org.jiumao.wechatMall.common.domain.ResponseUtil;
import org.jiumao.wechatMall.entity.User;
import org.jiumao.wechatMall.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Component;


@Component
@Path("/oauth2")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AuthResource {
    private static final Logger log = LoggerFactory.getLogger(LoggerName.Status);

    @Resource
    UserService userService;


    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String helloWorld(@QueryParam("msg") @DefaultValue("hello world") String msg) {
        return msg;
    }


    @GET
    @Path("authorize")
    public Response authorize(Authorize auth) {
        String entity = ErrorCode.NOT_AUTHED.toString();
        ResponseType type = ResponseType.valueOf(auth.getResponse_type());
        switch (type) {
        case code:
            ReturnCode code = OAuth2Provider.giveCode(auth.getClient_id());
            if (null != code) {
                entity = JsonUtil.bean2Json(code);
            }
        case token:
            ImplicitToken token = OAuth2Provider.giveToken(auth.getClient_id());
            if (null != token) {
                token.setState(auth.getState());
                entity = JsonUtil.bean2Json(token);
            }
            break;

        default:
            break;
        }
        return ResponseUtil.defaultRes(entity);
    }


    @POST
    @Path("token")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response token(Authentication auth) {
        GrantType type = GrantType.valueOf(auth.getGrant_type());
        String entity = ErrorCode.NOT_AUTHED.toString();
        switch (type) {
        case authorization_code:
            CodeToken ct = OAuth2Provider.hasCode(auth.getCode());
            if (null != ct) {
                entity = JsonUtil.bean2Json(ct);
            }
            break;
        case password:
            // base64 author
            PasswdToken pwd = OAuth2Provider.login(auth.getUsername(), auth.getPassword());
            if (null != pwd) {
                entity = JsonUtil.bean2Json(pwd);
            }
            break;
        case clientcredentials:
            ClientToken clientToken = OAuth2Provider.client(auth.getClient_id(), auth.getClient_secret());
            if (null != clientToken) {
                entity = JsonUtil.bean2Json(clientToken);
            }
            break;
        case refresh_token:
            PasswdToken pwdT = OAuth2Provider.refresh(auth.getClient_id(), auth.getClient_secret(),auth.getRefresh_token());
            if (null != pwdT) {
                entity = JsonUtil.bean2Json(pwdT);
            }
            break;

        default:
            break;
        }

        return ResponseUtil.defaultRes(entity);

    }

}
