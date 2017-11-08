package org.jiumao.wechatMall.common.domain;

import javax.ws.rs.core.CacheControl;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


public class ResponseUtil {

    public static Response defaultRes(String entity) {
        return Response.ok().entity(entity).header("charset", "UTF-8").cacheControl(new CacheControl())
            .header("Pragma", "no-cache").type(MediaType.APPLICATION_JSON_TYPE).build();

    }
}
