package com.kongxiaogang.tools.xml.unpackage;

import java.util.HashMap;
import java.util.Map;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 * 
 * <pre>项目名称：xhcf-back-web    
 * 类名称：MessageHandler    
 * 类描述：    xml报文解析
 * 创建人：孔小刚 xiaogangkong@galaxyinternet.com    
 * 创建时间：2017年2月8日 下午5:58:35    
 * 修改人：孔小刚 xiaogangkong@galaxyinternet.com     
 * 修改时间：2017年2月8日 下午5:58:35    
 * 修改备注：       
 * @version </pre>
 */
public class MessageHandler extends DefaultHandler {
	private Map<String, Object> messages;
	private String headerString = "";
	private String strValue = new String();
	private boolean flag = false;

	public MessageHandler() {
		this.messages = new HashMap<String, Object>();
	}

	public void startElement(String uri, String localName, String qName,Attributes attributes) throws SAXException {
		headerString += qName + ".";
		flag = true;
	}

	public void characters(char[] ch, int start, int length)
			throws SAXException {
		char[] dest = new char[length];
		System.arraycopy(ch, start, dest, 0, length);
		strValue = String.valueOf(dest);
	}

	public void endElement(String uri, String localName, String qName)
			throws SAXException {
		if(true == flag){
			String node = headerString.substring(0, headerString.length()-1);
			Object obj = messages.get(node);
			if(obj instanceof String){
				Map<String, String> objlist = new HashMap<String, String>();
				objlist.put("1", (String)obj);
				objlist.put("2", strValue.toString().trim());
				messages.put(node, objlist);
			}else if(obj instanceof Map){
				int size = ((Map<String, String>)obj).size();
				((Map<String, String>)obj).put(String.valueOf(size + 1), strValue.toString().trim());
				messages.put(node, obj);
			}else{
				messages.put(node, strValue.toString().trim());
			}
			strValue = "";
		}
		headerString = headerString.substring(0, headerString.length() - qName.length() - 1);
		flag = false;
	}

	public Map<String, Object> getMessages() {
		return messages;
	}
}
