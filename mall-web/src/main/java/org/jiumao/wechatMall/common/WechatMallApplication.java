package org.jiumao.wechatMall.common;

import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.spring.scope.RequestContextFilter;
import org.jiumao.wechatMall.common.app.AppkeyServiceFeature;
import org.jiumao.wechatMall.common.app.AuthServiceFeature;
import org.jiumao.wechatMall.common.app.CORSResponseFilter;
import org.jiumao.wechatMall.common.app.LoggingResponseFilter;

/**
 * Register JAX-RS application components.
 */
public class WechatMallApplication extends ResourceConfig {

	public WechatMallApplication() {

		String admin = "org.jiumao.wechatMall.admin.resource";
		String mall = "org.jiumao.wechatMall.mall.resource";
		String api = "org.jiumao.wechatMall.api.resource";
		String user = "org.jiumao.wechatMall.user.resource";
		packages(admin, mall, api, user);
		register(RequestContextFilter.class);
		register(JacksonFeature.class);
		register(AppkeyServiceFeature.class);
		register(AuthServiceFeature.class);
		register(LoggingResponseFilter.class);
		register(CORSResponseFilter.class);
		
		
	}
}