package com.kongxiaogang.tools.xml.other;

import java.util.HashMap;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 * <pre>项目名称：xhcf-back-web    
 * 类名称：XmlLoadHandler    
 * 类描述：    接口配置文件解析辅助工具
 * 创建人：孔小刚 xiaogangkong@galaxyinternet.com    
 * 创建时间：2017年2月8日 上午11:49:01    
 * 修改人：孔小刚 xiaogangkong@galaxyinternet.com     
 * 修改时间：2017年2月8日 上午11:49:01    
 * 修改备注：       
 * @version </pre>
 */
public class XmlLoadHandler extends DefaultHandler {
	
	private static final Logger _log = LoggerFactory.getLogger(XmlLoad.class);
	
	/**
	 * 存储序列号
	 */
	public static final String index_xml_message = "index";
	
	/**
	 * 数据类型
	 */
	public static final String type_xml_message = "type";
	
	/**
	 * 数据长度
	 */
	public static final String maxLength_xml_message = "maxLength";
	
	/**
	 * 数据描述
	 */
	public static final String description_xml_message = "description";
	
	private Map<String, Object> currentData = new HashMap<String, Object>();
	
	private String nodeName = "";
	
	private int index = 0;
	
	public void characters(char[] ch, int start, int length)
			throws SAXException {
	}

	public void endDocument() throws SAXException {
		_log.info("文档解析完成。");
	}

	public void endElement(String uri, String localName, String name)
			throws SAXException {
		nodeName = nodeName.substring(0, nodeName.lastIndexOf(name));
	}

	public void startDocument() throws SAXException {
		_log.info("文档解析开始... ...");
	}

	public void startElement(String uri, String localName, String name,
			Attributes attributes) throws SAXException {
		int attrlen = attributes.getLength();
		if(attrlen > 0){
			Map<String, String> xmlobj = new HashMap<String, String>();
			for(int i=0; i<attrlen; i++){
				xmlobj.put(attributes.getQName(i), attributes.getValue(i));
			}
			xmlobj.put(index_xml_message, String.valueOf(index++));
			currentData.put(this.nodeName + name, xmlobj);
		}
		nodeName += name + ".";
	}

	public Map<String, Object> getCurrentData() {
		return currentData;
	}

	public void setCurrentData(Map<String, Object> currentData) {
		this.currentData = currentData;
	}
}