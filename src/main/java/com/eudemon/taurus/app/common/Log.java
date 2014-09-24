/**
 * 
 */
package com.eudemon.taurus.app.common;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;

/**
 * @author xiaoyang.zhang
 * 
 */
public class Log {
	private static String path = "";
	private static String name = "";

	private static Logger infoLogger = null;
	private static Logger debugLogger = null;
	private static Logger errorLogger = null;
	private static Logger traceLogger = null;

	public static void init(String logPath, String logName) {
		path = logPath;
		name = logName;
		config(path, name);

		infoLogger = Logger.getLogger("infoLogger");
		debugLogger = Logger.getLogger("debugLogger");
		errorLogger = Logger.getLogger("errorLogger");
		traceLogger = Logger.getLogger("traceLogger");
	}

	public static Logger getInfoLogger() {
		return infoLogger;
	}

	public static Logger getDebugLogger() {
		return debugLogger;
	}

	public static Logger getErrorLogger() {
		return errorLogger;
	}

	public static Logger getTraceLogger() {
		return traceLogger;
	}

	public static void logMap(Map<String, Object> hs) {
		if (null == hs) {
			return;
		}
		Iterator<String> it = hs.keySet().iterator();
		while (it.hasNext()) {
			String key = it.next();
			Object value = hs.get(key);
			infoLogger.info(key + "=" + value);
		}
	}

	public static void logMapList(List<Map<String, Object>> ls) {
		for (int i = 0; i < ls.size(); i++) {
			logMap(ls.get(i));
		}
	}

	public static <T> void LogList(List<T> ls) {
		for (int i = 0; i < ls.size(); i++) {
			infoLogger.info(ls.get(i).toString());
		}
	}

	private static void config(String logPath, String logName) {
		System.setProperty("log.path", logPath);
		System.setProperty("log.name", logName);

		DOMConfigurator.configure(Log.class.getClassLoader().getResource("properties/log4j.xml").getFile());
	}
}
