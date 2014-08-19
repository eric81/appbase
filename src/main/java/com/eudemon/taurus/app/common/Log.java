/**
 * 
 */
package com.eudemon.taurus.app.common;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;

import com.eudemon.taurus.app.util.Dom4jUtil;



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
	
	public static void init(String logPath, String logName) {
		path = logPath;
		name = logName;
		config(path, name);
		
		infoLogger = Logger.getLogger("infoLogger");
		debugLogger = Logger.getLogger("debugLogger");
		errorLogger = Logger.getLogger("errorLogger");
	}
	
	public static Logger getInfoLogger(){
		return infoLogger;
	}
	
	public static Logger getDebugLogger(){
		return debugLogger;
	}
	
	public static Logger getErrorLogger(){
		return errorLogger;
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

	private static void config(String path, String name) {
		try {
			Document doc = Dom4jUtil.read(Log.class.getClassLoader().getResource("properties/log4j.xml").getFile());
			Element root = doc.getRootElement();
			@SuppressWarnings("unchecked")
			Iterator<Element> it = root.elementIterator("appender");
			while(it.hasNext()){
				Element appender = (Element) it.next();
				Element param = appender.element("param");
				String org = param.attributeValue("value");
				String file = StringUtils.replace(org, "${path}", path);
				file = StringUtils.replace(file, "${app}", name);
				param.attribute("value").setValue(file);
			}
			Dom4jUtil.write(Log.class.getClassLoader().getResource("properties/log4j.xml").getFile(), doc, "utf-8");
			
			DOMConfigurator.configure(Log.class.getClassLoader().getResource("properties/log4j.xml").getFile());
		} catch (DocumentException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
