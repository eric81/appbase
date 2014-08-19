package com.eudemon.taurus.app.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.eudemon.taurus.app.AppStartServer;

public class AppStartListener implements ServletContextListener {

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		AppStartServer.getInstance().startSystem(sce.getServletContext());
	}

}
