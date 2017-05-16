package com.kongxiaogang.tools.xml.unpackage;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.Map;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.apache.log4j.Logger;
import org.xml.sax.InputSource;

/**
 * 
 * <pre>项目名称：xhcf-back-web    
 * 类名称：UnPackageHandler    
 * 类描述：    车险报文解析
 * 创建人：孔小刚 xiaogangkong@galaxyinternet.com    
 * 创建时间：2017年2月8日 下午5:48:51    
 * 修改人：孔小刚 xiaogangkong@galaxyinternet.com     
 * 修改时间：2017年2月8日 下午5:48:51    
 * 修改备注：       
 * @version </pre>
 */
public class UnPackageHandler {
	private static final Logger _log = Logger.getLogger(UnPackageHandler.class);
	public Map<String, Object> messageToObj(String xml) {
		Map<String, Object> messages = null;
		try {
			SAXParserFactory spf = SAXParserFactory.newInstance();
			SAXParser saxParser = spf.newSAXParser();
			MessageHandler hendler = new MessageHandler();
			InputSource is = new InputSource ();
			String xmlnew =xml;
			if (xml.contains("\n")) {
				xmlnew = xml.replaceAll("\n", "");
			}
			if (xml.contains("[")) {
				xmlnew = xmlnew.replace("[", "|");
			}
			if (xml.contains("]")) {
				xmlnew = xmlnew.replace("]", "|");
			}
			
			StringReader xmlStr = new StringReader(xmlnew);
			is.setCharacterStream (xmlStr);
			saxParser.parse(is, hendler);
			messages = hendler.getMessages();
			messages.put("ORIGINAL_XML", xml);//保存原始xml
		} catch (Exception ex) {
			_log.error("人行返回报文解析失败：", ex);
		}
		return messages;
	}
	
}
