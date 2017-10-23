package org.jiumao.wechatMall.common;

import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.spring.scope.RequestContextFilter;

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
		register(LoggingResponseFilter.class);
		register(CORSResponseFilter.class);
		
		
	}
}