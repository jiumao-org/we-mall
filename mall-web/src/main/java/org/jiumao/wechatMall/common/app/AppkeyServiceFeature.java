package org.jiumao.wechatMall.common.app;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.container.DynamicFeature;
import javax.ws.rs.container.ResourceInfo;
import javax.ws.rs.core.FeatureContext;
import javax.ws.rs.ext.Provider;

import org.jiumao.mall.auth.AppkeyAnnotation;

@Provider
public class AppkeyServiceFeature implements DynamicFeature {

    @Override
    public void configure(ResourceInfo resourceInfo, FeatureContext context) {

        List<Annotation> authzSpecs = new ArrayList<>();

        Annotation classAuthzSpec =
            resourceInfo.getResourceClass().getAnnotation(AppkeyAnnotation.class);
        Annotation methodAuthzSpec =
            resourceInfo.getResourceMethod().getAnnotation(AppkeyAnnotation.class);

        if (classAuthzSpec != null)
            authzSpecs.add(classAuthzSpec);
        if (methodAuthzSpec != null)
            authzSpecs.add(methodAuthzSpec);

        if (!authzSpecs.isEmpty()) {
            context.register(AppkeyFilter.class);
        }
    }

}

