package com.kongxiaogang.tools.xml.other;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.SAXException;
/**
 * 
 * <pre>项目名称：xhcf-back-web    
 * 类名称：XmlLoad    
 * 类描述：    车险报文解析
 * 创建人：孔小刚 xiaogangkong@galaxyinternet.com    
 * 创建时间：2017年2月8日 上午11:42:34    
 * 修改人：孔小刚 xiaogangkong@galaxyinternet.com     
 * 修改时间：2017年2月8日 上午11:42:34    
 * 修改备注：       
 * @version </pre>
 */
public final class XmlLoad {
	
	private static final Logger _log = LoggerFactory.getLogger(XmlLoad.class);
	
	private static XmlLoad load = null;
	
	private Map<String, Object> datahandler;
	
	private XmlLoad(){
		InputStream inputstream = null;
		if(inputstream == null)
			inputstream = XmlLoad.class.getResourceAsStream("/XmlTemplate.xml");
		try {
			parseXml(inputstream);
		} catch (Exception ex) {
			_log.error("车险接口配置文件xmlTemplate加载失败:", ex);
		}
	}
	
	public static XmlLoad getInstance(){
		if(load == null)
			load = new XmlLoad();
		return load;
	}
	
	private void parseXml(InputStream input) throws ParserConfigurationException, SAXException, IOException{
		SAXParserFactory spf = SAXParserFactory.newInstance();
		SAXParser saxParser = spf.newSAXParser();
		XmlLoadHandler hendler = new XmlLoadHandler();
		saxParser.parse(input, hendler);
		datahandler = hendler.getCurrentData();
	}

	public Map<String, Object> getDatahandler() {
		return datahandler;
	}
}
