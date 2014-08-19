package com.eudemon.taurus.app.common;

import javax.servlet.ServletContext;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

public class AppCtxServer {
	private static AppCtxServer instance = null;

	private WebApplicationContext webCtx = null;

	private ApplicationContext clsPathCtx = null;

	private final String ctxFilePath = "classpath*:context";

	private AppCtxServer() {

	}

	public static AppCtxServer getInstance() {
		if (null == instance) {
			instance = new AppCtxServer();
		}

		return instance;
	}

	public void init(ServletContext sCtx) throws Exception {
		if (null != sCtx) {
			ContextLoader ctloader = new ContextLoader();
			webCtx = ctloader.initWebApplicationContext(sCtx);
		}

		if (null == webCtx) {
			clsPathCtx = new ClassPathXmlApplicationContext(beanConfigFiles());
		}
	}

	public Object getBean(String beanName) throws Exception {
		Object bean = getBeanByWebCtx(beanName);

		if (null == bean) {
			bean = getBeanByClsPathCtx(beanName);
		}

		if (null == bean) {
			throw new Exception(
					"No bean can be found from web context or class path context");
		}

		return bean;
	}

	private Object getBeanByWebCtx(String beanName) throws Exception {
		if (null == webCtx) {
			return null;
		}

		return webCtx.getBean(beanName);
	}

	private Object getBeanByClsPathCtx(String beanName) throws Exception {
		if (null == clsPathCtx) {
			return null;
		}

		return clsPathCtx.getBean(beanName);
	}

	private String[] beanConfigFiles() throws Exception {
		return new String[] { ctxFilePath + "/*.xml"};
	}
}
