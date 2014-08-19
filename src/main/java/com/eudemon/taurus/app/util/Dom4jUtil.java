package com.eudemon.taurus.app.util;

import java.io.ByteArrayInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.StringWriter;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.commons.beanutils.PropertyUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

/**
 * Util of Dom4j
 * 
 * @author xiaoyang.zhang
 * 
 */
public class Dom4jUtil {
	/**
	 * create document example
	 * 
	 * @return
	 */
	public static Document createDocument() {
		// create Document
		Document doc = DocumentHelper.createDocument();

		// add root
		Element root = doc.addElement("Response");

		Element code = root.addElement("code");
		code.setText("1");

		Element desc = root.addElement("desc");
		desc.setText("send");

		return doc;
	}

	/**
	 * write doc to file
	 * 
	 * @param fileName
	 *            url of file
	 * @param doc
	 *            object of Document
	 * @param charSet
	 *            encoding of xml
	 * @throws IOException
	 */
	public static void write(String fileName, Document doc, String charSet)
			throws IOException {
		FileOutputStream out = new FileOutputStream(fileName);
		OutputFormat format = OutputFormat.createPrettyPrint();
		format.setEncoding(charSet);
		XMLWriter writer = new XMLWriter(out, format);
		writer.write(doc);
		writer.close();
		out.flush();
		out.close();
	}

	/**
	 * write doc to OutputStream
	 * 
	 * @param out
	 *            OutputStream
	 * @param doc
	 *            Document
	 * @param charSet
	 *            encoding of xml
	 * @throws IOException
	 */
	public static void write(OutputStream out, Document doc, String charSet)
			throws IOException {
		OutputFormat format = OutputFormat.createPrettyPrint();
		format.setEncoding(charSet);
		XMLWriter writer = new XMLWriter(out, format);
		writer.write(doc);
		writer.close();
		out.flush();
		out.close();
	}

	/**
	 * read the xml file to Document
	 * 
	 * @param fileName
	 * @return
	 * @throws DocumentException
	 */
	public static Document read(String fileName) throws DocumentException {
		SAXReader reader = new SAXReader();
		Document doc = reader.read(fileName);
		return doc;
	}

	/**
	 * read the xml InputStream to Document
	 * 
	 * @param in
	 * @return
	 * @throws DocumentException
	 * @throws IOException
	 */
	public static Document read(InputStream in) throws DocumentException,
			IOException {
		SAXReader reader = new SAXReader();
		Document doc = reader.read(in);
		in.close();
		return doc;
	}

	/**
	 * change xml string to Document
	 * 
	 * @param str
	 * @return
	 * @throws DocumentException
	 * @throws IOException
	 */
	public static Document str2Doc(String str, String encoding)
			throws DocumentException, IOException {
		if (null == str) {
			return null;
		}
		SAXReader reader = new SAXReader();
		InputStream in = new ByteArrayInputStream(str.getBytes(encoding));
		Document doc = reader.read(in);

		in.close();
		return doc;
	}

	/**
	 * change Document to xml string
	 * 
	 * @param doc
	 * @param charSet
	 * @return
	 * @throws IOException
	 */
	public static String doc2Str(Document doc, String charSet)
			throws IOException {
		StringWriter ss = new StringWriter();

		OutputFormat format = OutputFormat.createPrettyPrint();
		format.setEncoding(charSet);
		XMLWriter writer = new XMLWriter(ss, format);
		writer.write(doc);

		ss.close();
		writer.close();

		return ss.getBuffer().toString();
	}

	/**
	 * change the content of the Element to Map
	 * 
	 * @param ele
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static Map<String, String> element2Map(Element ele) {
		Map<String, String> hs = new HashMap<String, String>();

		Iterator<Element> keyIt = ele.elementIterator();
		Element keyEle = null;
		while (keyIt.hasNext()) {
			keyEle = keyIt.next();
			hs.put(keyEle.getName(), keyEle.getText());
		}

		return hs;
	}

	/**
	 * add the map content to the Element
	 * 
	 * @param hs
	 * @param ele
	 */
	public static void addMap2Element(Map<String, String> hs, Element ele) {
		if (null == hs) {
			return;
		}
		Iterator<String> it = hs.keySet().iterator();
		while (it.hasNext()) {
			String key = it.next();
			String value = hs.get(key);
			if (null != value) {
				ele.addElement(key).setText(value);
			}
		}
	}

	@SuppressWarnings("unchecked")
	public static void addBean2Element(Object bean, Element ele,
			boolean isCreateBeanNameTag) throws IllegalAccessException,
			InvocationTargetException, NoSuchMethodException {
		Element eBean = null;
		if (isCreateBeanNameTag) {
			String className = bean.getClass().getSimpleName();
			eBean = ele.addElement(className);
		} else {
			eBean = ele;
		}

		Map<String, String> map = PropertyUtils.describe(bean);
		addMap2Element(map, eBean);
	}

	public static void main(String[] args) {
;
		try {
			Document doc = createDocument();
			write("1.xml", doc, "gb2312");
			Element root = doc.getRootElement();
			Element code = root.element("code");
			code.setText("2");
			write("1.xml", doc, "gb2312");
//			Document doc = read("1.xml");
//			String strXml = doc2Str(doc, "gb2312");
//			System.out.println("strXml========================");
//			System.out.println(strXml);
//			Document strDoc = str2Doc(strXml, "gb2312");
//			System.out.println("strDoc========================");
//			System.out.println(doc2Str(strDoc, "gb2312"));
//
//			String str = "<?xml version='1.0' encoding='GBK'?><Response><HEAD><VER>1.0</VER></HEAD><BODY><Device/></BODY></Response>";
//			str2Doc(str, "GBK");
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
}
