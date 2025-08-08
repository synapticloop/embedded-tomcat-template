package com.embedded.tomcat.listener;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;

public class WebXMLContextListener implements ServletContextListener {
	@Override public void contextInitialized(ServletContextEvent sce) {
		System.out.println("WebXMLContextListener contextInitialized registered in the web.xml, not by an annotation.");
	}

	@Override public void contextDestroyed(ServletContextEvent sce) {
		System.out.println("WebXMLContextListener contextDestroyed  registered in the web.xml, not by an annotation.");
	}
}
