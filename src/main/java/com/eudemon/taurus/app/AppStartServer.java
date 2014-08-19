package com.eudemon.taurus.app;

import javax.servlet.ServletContext;

import com.eudemon.taurus.app.common.AppCtxServer;
import com.eudemon.taurus.app.common.Config;
import com.eudemon.taurus.app.common.Log;

public class AppStartServer {
	private static AppStartServer instance = null;

	private AppStartServer() {

	}

	public static AppStartServer getInstance() {
		if (null == instance) {
			instance = new AppStartServer();
		}

		return instance;
	}

	public void startSystem(ServletContext sct) {
		long st = System.currentTimeMillis();
		
		try {
			Config.init();
		} catch (Exception e) {
			Log.getErrorLogger().error("Config initial failed", e);
			e.printStackTrace();
			return;
		}
		
		if(!Config.getBoolean("isStart")){
			Log.getInfoLogger().info("Application : " + Config.getString("app.name") + " not sart by config ");
			return;
		}
		
		try {
			AppCtxServer.getInstance().init(sct);
		} catch (Exception e) {
			Log.getErrorLogger().error("Application context initial failed", e);
			e.printStackTrace();
			return;
		}
		
		Log.getInfoLogger().info("Application : " + Config.getString("app.name") + " startup in " + (System.currentTimeMillis() - st) + " ms");
	}

	public static void main(String[] args) {
		AppStartServer.getInstance().startSystem(null);
	}
}
