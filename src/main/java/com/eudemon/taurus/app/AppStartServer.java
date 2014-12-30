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
			Config.initFromFile();
		} catch (Exception e) {
			e.printStackTrace();
			return;
		}
		
		Log.init(Config.getString("log.path"), Config.getString("log.name"));
		Log.getInfoLogger().info("Starting applicatin[" + Config.getString("app.name") + "]");
		Log.getInfoLogger().info("Initialized Configuration with PropertySources [app.properties]");
		Log.getInfoLogger().info("Initialized logger, path:" + Config.getString("log.path") + ", name:" + Config.getString("log.name"));
		
		if(!Config.getBoolean("isStart")){
			Log.getInfoLogger().info("Application[" + Config.getString("app.name") + "] not sart by config ");
			return;
		}
		
		try {
			AppCtxServer.getInstance().init(sct);
		} catch (Exception e) {
			Log.getErrorLogger().error("Application context initial failed", e);
			return;
		}
		
		try {
			Config.initFromDatabase();
			Log.getInfoLogger().info("Configuration added from database");
		} catch (Exception e) {
			Log.getErrorLogger().error("Adding Configuration from database failed", e);
			return;
		}
		
		Config.logInfo();
		
		Log.getInfoLogger().info("Application[" + Config.getString("app.name") + "] startup in " + (System.currentTimeMillis() - st) + " ms");
	}

	public static void main(String[] args) {
		AppStartServer.getInstance().startSystem(null);
	}
}
