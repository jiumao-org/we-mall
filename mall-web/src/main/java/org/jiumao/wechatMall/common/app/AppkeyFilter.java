package org.jiumao.wechatMall.common.app;

import java.io.IOException;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;

import org.apache.commons.lang3.StringUtils;
import org.jiumao.mall.auth.Auths;
import org.jiumao.mall.domain.User;

/**
 * 访问次数与权限控制
 * @author ppf@jiumao.org
 * @date 2017年11月13日
 */
public class AppkeyFilter implements ContainerRequestFilter {

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {

        //获取客户端Header中提交的token
        String token = requestContext.getHeaderString("appkey");
        if (StringUtils.isEmpty(token)) {
            // TODO:拦截响应
        }
        //判断该用户是否已登陆
        User user = Auths.sign(token);
        if (user == null) {
            // TODO:拦截响应
        } 
    }

}
