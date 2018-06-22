package com.neusoft.duanmlssh.httpProxy;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class CopyOfMyHttpProxyListener implements ServletContextListener{
	
	private HttpProxy httpProxy;
	 
	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		if (httpProxy != null && httpProxy.isInterrupted()) {
			httpProxy.interrupt();  
        }  
	}

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		String str = null;  
        if (str == null && httpProxy == null) {  
        	httpProxy = new HttpProxy();  
        	httpProxy.startProxy(8088, HttpProxy.class);   // servlet 上下文初始化时启动 socket  
        }  
	}

}
