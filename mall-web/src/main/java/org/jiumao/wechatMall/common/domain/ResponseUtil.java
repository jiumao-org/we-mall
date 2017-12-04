package org.jiumao.wechatMall.common.domain;

import javax.ws.rs.core.CacheControl;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


public class ResponseUtil {

    /**
     * FIXME 是否能直接写 byte[] 避免序列化？
     * @param entity
     * @return
     */
    public static Response defaultRes(Object entity) {
        return Response.ok().entity(entity).header("charset", "UTF-8").cacheControl(new CacheControl())
            .header("Pragma", "no-cache").type(MediaType.APPLICATION_JSON_TYPE).build();

    }
}
