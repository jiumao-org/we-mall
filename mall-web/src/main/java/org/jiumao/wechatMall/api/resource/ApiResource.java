package org.jiumao.wechatMall.api.resource;

import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.jiumao.common.constants.LoggerName;
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
    @Produces(MediaType.TEXT_PLAIN)
    public String getAppkey(@QueryParam("msg") @DefaultValue("hello world") String msg) {
        return msg;
    }
}
