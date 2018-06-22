package com.neusoft.duanmlssh.httpProxy;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class MyHttpProxyListener implements ServletContextListener {

	private SocketThread socketThread;

	@Override
	public void contextDestroyed(ServletContextEvent e) {
		if (socketThread != null && socketThread.isInterrupted()) {
			socketThread.closeServerSocket();
			socketThread.interrupt();
		}
	}

	@Override
	public void contextInitialized(ServletContextEvent e) {
		ServletContext servletContext = e.getServletContext();
		System.out.println("Server contextInitialized over");
		if (socketThread == null) {
			socketThread = new SocketThread(null, servletContext);
			socketThread.start();
		}
	}

}
