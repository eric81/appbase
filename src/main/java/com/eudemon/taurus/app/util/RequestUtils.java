package com.eudemon.taurus.app.util;

import java.util.Enumeration;

import javax.servlet.ServletContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;

import com.eudemon.taurus.app.common.Log;

public class RequestUtils {

	public RequestUtils() {
	}

	public static int getParameterAsInt(HttpServletRequest request, String param) {
		return getParameterAsInt(request, param, 0);
	}

	public static int getParameterAsInt(HttpServletRequest request, String param, int defaultValue) {
		String value = request.getParameter(param);
		if (null != value) {
			try {
				return Integer.parseInt(value);
			} catch (NumberFormatException e) {
				Log.getErrorLogger().warn("getParameterAsInt NumberFormatException, " + e.getMessage() + ", uri:" + request.getRequestURI());
			}
		}

		return defaultValue;
	}

	public static boolean getParameterAsBoolean(HttpServletRequest request, String param) {
		return getParameterAsBoolean(request, param, false);
	}

	public static boolean getParameterAsBoolean(HttpServletRequest request, String param, boolean defaultValue) {
		String temp = request.getParameter(param);
		if (temp != null) {
			return Boolean.parseBoolean(temp);
		}
		return defaultValue;
	}

	/**
	 * @deprecated Method getParameterAsGBK is deprecated
	 */

	public static String getParameterAsGBK(HttpServletRequest req, String param) {
		String result = req.getParameter(param);
		return result != null ? result.trim() : "";
	}

	public static String getParameterAndTrim(HttpServletRequest req, String param) {
		String result = req.getParameter(param);
		return result != null ? result.trim() : "";
	}

	public static String getParameterSafe(HttpServletRequest req, String param) {
		String result = req.getParameter(param);
		if (result == null || "".equals(result))
			return "";
		StringBuffer strBuff = new StringBuffer();
		char charArray[] = result.toCharArray();
		for (int i = 0; i < charArray.length; i++)
			if (charArray[i] != '<' && charArray[i] != '>')
				strBuff.append(charArray[i]);

		return strBuff.toString();
	}

	public static String getAttributeAsTrimStr(HttpServletRequest req, String attrName) {
		return getAttributeAsTrimStr(req, attrName, "");
	}

	public static String getAttributeAsTrimStr(HttpServletRequest req, String attrName, String defValue) {
		Object obj = req.getAttribute(attrName);
		return (obj instanceof String) ? ((String) obj).trim() : defValue;
	}

	/**
	 * @deprecated Method existParameter is deprecated
	 */

	public static boolean existParameter(HttpServletRequest req, String param) {
		if (param == null)
			return false;
		return req.getParameter(param) != null;
	}

	public static String getFullGetStr(HttpServletRequest req) {
		String qryStr = req.getQueryString();
		if (qryStr == null)
			return req.getRequestURL().toString();
		else
			return req.getRequestURL().append('?').append(qryStr).toString();
	}

	public static String getCurrentPage(HttpServletRequest req) {
		String requestURI = req.getRequestURI();
		return requestURI.substring(requestURI.lastIndexOf('/') + 1);
	}

	public static String getCurPageWithQryStr(HttpServletRequest req) {
		return getCurPageWithQryStr(req, null);
	}

	public static String getCurPageWithQryStr(HttpServletRequest req, String param) {
		String qryStr = removeQryParam(req.getQueryString(), param);
		if (qryStr == null)
			return getCurrentPage(req);
		else
			return getCurrentPage(req) + '?' + qryStr;
	}

	public static String removeQryParam(String qryStr, String param) {
		if (qryStr == null || param == null)
			return qryStr;
		String params[] = StringUtils.split(qryStr, "&");
		StringBuffer sb = new StringBuffer(qryStr.length());
		for (int i = 0; i < params.length; i++)
			if (!params[i].startsWith(param + "="))
				sb.append(params[i]).append('&');

		return sb.length() <= 0 ? null : sb.deleteCharAt(sb.length() - 1).toString();
	}

	public static String addQryParam(String qryStr, String param) {
		if (qryStr == null || param == null)
			return qryStr;
		if (qryStr.indexOf('?') == -1 && qryStr.indexOf('=') == -1)
			return qryStr + '?' + param;
		if (qryStr.endsWith("?"))
			qryStr = qryStr.substring(0, qryStr.indexOf('?'));
		return qryStr + (qryStr.indexOf('?') < 0 ? 63 : '&') + param;
	}

	public static String getRequestInfo(HttpServletRequest req) {
		StringBuffer sb = new StringBuffer(320);
		sb.append("[Req]");
		sb.append(req.getClass().getName());
		sb.append(": (").append(req.getScheme()).append(')').append(req.getServerName()).append(':')
				.append(req.getServerPort());
		sb.append(", ").append(req.getMethod()).append(' ').append(req.getProtocol());
		sb.append(", uri=").append(req.getRequestURI());
		sb.append(", ctx=").append(req.getContextPath());
		sb.append(", servlet=").append(req.getServletPath());
		sb.append(", qryStr=").append(req.getQueryString());
		sb.append(", refer=").append(req.getHeader("referer"));
		sb.append(", useragt=").append(req.getHeader("user-agent"));
		sb.append(", ip=").append(req.getRemoteAddr());
		sb.append(", encoding=").append(req.getCharacterEncoding());
		return sb.toString();
	}

	@SuppressWarnings("rawtypes")
	public static String getAllHeadersStr(HttpServletRequest req) {
		StringBuffer sb = new StringBuffer(256);
		String header = null;
		for (Enumeration headers = req.getHeaderNames(); headers.hasMoreElements(); sb.append("\r\n")) {
			header = (String) headers.nextElement();
			sb.append(header);
			sb.append("=");
			sb.append(req.getHeader(header));
		}

		return sb.toString();
	}

	public static String getUserAgentAndCookies(HttpServletRequest req) {
		StringBuffer sb = new StringBuffer(256);
		sb.append("user-agent").append('=').append(req.getHeader("user-agent")).append(';');
		sb.append("trs-ids-cmd").append('=').append(req.getHeader("trs-ids-cmd")).append('\n');
		sb.append("cookies: ");
		Cookie cookies[] = req.getCookies();
		if (cookies != null) {
			for (int i = 0; i < cookies.length; i++) {
				sb.append(cookies[i].getName()).append('=').append(cookies[i].getValue());
				sb.append(';');
			}

		}
		return sb.toString();
	}

	public static String getReferUrl(HttpServletRequest req) {
		String referUrl = req.getHeader("referer");
		return referUrl != null ? referUrl : "";
	}

	public static int getPageUriPosInRequest(HttpServletRequest req, String someUri) {
		if (someUri == null || someUri.trim().length() == 0)
			return -2;
		else
			return getRelativePath(req).indexOf(someUri);
	}

	public static String getContextRoot(HttpServletRequest request) {
		String sysUrl = request.getRequestURL().toString();
		if (sysUrl.indexOf(request.getServletPath()) == -1)
			return sysUrl;
		else
			return sysUrl.substring(0, sysUrl.indexOf(request.getServletPath()));
	}

	public static String getRelativePath(HttpServletRequest req) {
		return req.getRequestURI().substring(req.getContextPath().length());
	}

	public static String getServletContainerInfo(ServletContext application) {
		StringBuffer sb = new StringBuffer(64);
		sb.append(application.getServerInfo());
		sb.append(" (Servlet ").append(application.getMajorVersion()).append('.').append(application.getMinorVersion())
				.append(')');
		return sb.toString();
	}

	public static void log(HttpServletRequest req) {
		Log.getDebugLogger().debug(getRequestInfo(req));
	}
}