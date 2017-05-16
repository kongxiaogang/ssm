package com.kongxiaogang.tools.util;

import java.io.ByteArrayOutputStream;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.kongxiaogang.tools.xml.inpackage.PackageImpl;


/**
 * 
 * <pre>项目名称：xhcf-back-web    
 * 类名称：XmlStringFormate    
 * 类描述：    xml格式化
 * 创建人：孔小刚 xiaogangkong@galaxyinternet.com    
 * 创建时间：2017年2月8日 下午2:42:45    
 * 修改人：孔小刚 xiaogangkong@galaxyinternet.com     
 * 修改时间：2017年2月8日 下午2:42:45    
 * 修改备注：       
 * @version </pre>
 */
public class XmlStringFormate {
	
	private static final Logger _log = LoggerFactory.getLogger(PackageImpl.class);
	
	public static String xmlformate(String xml) {
		String xmls = "";
		try{
			Document doc = DocumentHelper.parseText(xml);
			OutputFormat outformat = OutputFormat.createPrettyPrint();
			outformat.setEncoding("GBK");
			ByteArrayOutputStream baos2 = new ByteArrayOutputStream();
			XMLWriter writer = new XMLWriter(baos2, outformat);
			writer.write(doc);
			writer.flush();
			xmls = baos2.toString();
		}catch(Exception ex){
			_log.error("不是标准的XML：", ex);
			return xml;
		}
		return xmls;
	}
	
	public static void main(String[] args){
		System.out.println(xmlformate("<updates><ROWCOUNT></ROWCOUNT><recodelist><RECORD rowid=\"1\"><name>organinfo</name><version>46</version></RECORD><RECORD rowid=\"2\"><name>organinfo</name><version>46</version></RECORD></recodelist></updates>"));
	}
}
