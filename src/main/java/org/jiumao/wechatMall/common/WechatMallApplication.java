package org.jiumao.wechatMall.common;


import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.spring.scope.RequestContextFilter;

/**
 * Register JAX-RS application components.
 */
public class WechatMallApplication extends ResourceConfig {

	public WechatMallApplication() {
		register(RequestContextFilter.class);
		register(JacksonFeature.class);
		register(LoggingResponseFilter.class);
		register(CORSResponseFilter.class);
	}
}