package org.jiumao.wechatMall.common.app;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.container.DynamicFeature;
import javax.ws.rs.container.ResourceInfo;
import javax.ws.rs.core.FeatureContext;
import javax.ws.rs.ext.Provider;

import org.jiumao.wechatMall.common.auth.AuthAnnotation;
import org.jiumao.wechatMall.common.auth.AuthorizationFilter;

/**
 * 登陆认证服务
 * <p>
 * 一个上下文应用，登陆应该至少保证 {@link #id}或者某些字段不为空。 否则没有登陆验证
 * 
 * @author ppf@jiumao.org
 * @date 2017年10月23日
 */
@Provider
public class AuthServiceFeature implements DynamicFeature {

    @Override
    public void configure(ResourceInfo resourceInfo, FeatureContext context) {

        List<Annotation> authzSpecs = new ArrayList<>();

        Annotation classAuthzSpec =
            resourceInfo.getResourceClass().getAnnotation(AuthAnnotation.class);
        Annotation methodAuthzSpec =
            resourceInfo.getResourceMethod().getAnnotation(AuthAnnotation.class);

        if (classAuthzSpec != null)
            authzSpecs.add(classAuthzSpec);
        if (methodAuthzSpec != null)
            authzSpecs.add(methodAuthzSpec);

        if (!authzSpecs.isEmpty()) {
            // 需要拦截的api
            context.register(AuthorizationFilter.class);
        }
    }

}

