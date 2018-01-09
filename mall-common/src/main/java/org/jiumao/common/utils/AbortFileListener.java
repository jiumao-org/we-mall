package org.jiumao.common.utils;



import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AbortFileListener implements ServletContextListener {
	
	private static final Logger log = LoggerFactory.getLogger(AbortFileListener.class);

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		LocalPageCacheUtil.backup();
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
	    LocalPageCacheUtil.recovery();
	}

}
