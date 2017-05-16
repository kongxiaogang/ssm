package com.kongxiaogang.tools.xml.inpackage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.kongxiaogang.tools.util.SimpleNumberUtil;
import com.kongxiaogang.tools.util.XmlStringFormate;
import com.kongxiaogang.tools.xml.other.ServiceVO;
import com.kongxiaogang.tools.xml.other.XmlLoad;
import com.kongxiaogang.tools.xml.other.XmlLoadHandler;


/**
 * 
 * <pre>项目名称：xhcf-back-web    
 * 类名称：PackageImpl    
 * 类描述：组装xml报文
 * 创建人：孔小刚 xiaogangkong@galaxyinternet.com    
 * 创建时间：2017年2月8日 上午11:17:56    
 * 修改人：孔小刚 xiaogangkong@galaxyinternet.com     
 * 修改时间：2017年2月8日 上午11:17:56    
 * 修改备注：       
 * @version </pre>
 */
public class PackageImpl {
	private static final Logger _log = LoggerFactory.getLogger(PackageImpl.class);

	
	
	/*public static void main(String[] args) {
		ServiceVO vo = new ServiceVO();
		vo.setCurService("HEAD,MAIN");
		vo.setBusiParam("INSUREQ.HEAD.USER_NAME", "1111");
		vo.setBusiParam("INSUREQ.HEAD.USER_PSW", "1111");
		vo.setBusiParam("INSUREQ.HEAD.BUSINESS_CODE", "20010101");
		vo.setBusiParam("INSUREQ.MAIN.TRANSRNO", "1111");
		System.out.println(XmlStringFormate.xmlformate(new PackageImpl().compose(vo)));
	}*/
	public static void main(String[] args) throws Exception {
		ServiceVO vo = new ServiceVO();
		vo.setCurService("HEAD,MAIN,BASE");
		vo.setBusiParam("INSUREQ.HEAD.USER_NAME", "ECUser");
		vo.setBusiParam("INSUREQ.HEAD.USER_PSW", "EC100");
		vo.setBusiParam("INSUREQ.HEAD.BUSINESS_CODE", "11");
		vo.setBusiParam("INSUREQ.MAIN.TRANSRNO", "1030");
		vo.setBusiParam("INSUREQ.MAIN.SERIALDECIMAL", "5555555");
		vo.setBusiParam("INSUREQ.MAIN.TRANSRDATE", "2017-02-20 10:47:50");
		vo.setBusiParam("INSUREQ.MAIN.CHANNELCODE", "009801");
		vo.setBusiParam("INSUREQ.BASE.VHLMODEL.C_DPT_CDE", "110101");
		vo.setBusiParam("INSUREQ.BASE.C_LCN_NO", "*-*");
		vo.setBusiParam("INSUREQ.BASE.C_NEW_VHL", "1");
		System.out.println(new PackageImpl().compose(vo));
		System.out.println(XmlStringFormate.xmlformate(new PackageImpl().compose(vo)));
		
		
		/*NetSalePlatformStub sss = new NetSalePlatformStub();
		//封装getGreeting方法的参数
		NetSalePlatformStub.GetRequest gg = new NetSalePlatformStub.GetRequest();
		gg.setContent(new PackageImpl().compose(vo));
		System.out.println(sss.getRequest(gg).get_return());*/
	}	
	

	/**
	 * 组装报文
	 * 
	 * @param vo
	 * @return
	 */
	public String compose(ServiceVO vo) {
		List<DataTypeInstance> arr = new ArrayList<DataTypeInstance>();
		Map<String, Object> conf = XmlLoad.getInstance().getDatahandler();
		// 需要显示的报文节点
		String[] viewtype = vo.getCurService().split("\\,");
		int viewtypelen = viewtype.length;
		Set<Map.Entry<String, Object>> sresult = conf.entrySet();
		Iterator<Map.Entry<String, Object>> itor = sresult.iterator();
		while (itor.hasNext()) {
			boolean flag = false;
			Map.Entry<String, Object> entry = itor.next();
			String key = entry.getKey();
			for (int i = 0; i < viewtypelen; i++) {
				String temp = "INSUREQ." + viewtype[i];
				if (key.indexOf(temp) == 0) {
					flag = true;
					break;
				}
			}
			if (true == flag) {
				// 只有存在的结点才做处理
				Map prop = (Map) entry.getValue();
				compContext(vo, (String) vo.getBusiParam(key),prop,arr,key);
			}
		}

		return toXml(arr);
	}

	/**
	 * 将对象转化成报文
	 * 
	 * @param arr
	 * @return
	 */
	private String toXml(List<DataTypeInstance> arr) {
		StringBuffer xml = new StringBuffer("<?xml version=\"1.0\" encoding=\"GBK\" standalone=\"yes\"?>");
		List<String> save = new ArrayList<String>();
		Comparator comp = new XmlObjectComparator();
		Collections.sort(arr, comp);
		int len = arr.size();
		boolean tmpb = true;
		for (int i = 0; i < len; i++) {
			DataTypeInstance data = arr.get(i);
			//判断标签的内容是否有值，空值的话标签去除
			String dataValue = data.getValue();
			if(null != dataValue && !"".equals(dataValue) && !"null".equals(dataValue)){
				String[] nodes = data.getType().split("\\.");
				int nodeslen = nodes.length;
				for (int j = 0; j < nodeslen - 1; j++) {
					if(!contains(save,nodes[j])){
//					if (!save.contains(nodes[j])) {
						int savesize = save.size();
						for (int k = savesize; k > j; k--) {
							xml.append("</").append(save.get(k - 1)).append(">");
						}
						save = save.subList(0, j);
						save.add(nodes[j]);
						xml.append("<").append(nodes[j]).append(">");
					} else {
						if ("INSUREQ".equals(nodes[j]) && j == 2 && tmpb == true) {
							int savesize = save.size();
							for (int k = savesize; k > j; k--) {
								xml.append("</").append(save.get(k - 1))
										.append(">");
							}
							save = save.subList(0, j);
							save.add(nodes[j]);
							xml.append("<").append(nodes[j]).append(">");
							tmpb = false;
						}
					}
				}
				if (!"".equals(data.getValue())) {
					xml.append("<").append(nodes[nodeslen - 1]).append(">");
					xml.append(data.getValue());
					xml.append("</").append(nodes[nodeslen - 1]).append(">");
				} else {
					xml.append("<").append(nodes[nodeslen - 1]).append("/>");
				}
			}
			
		}
		int savesize = save.size();
		for (int k = savesize; k > 0; k--) {
			xml.append("</").append(save.get(k - 1)).append(">");
		}
		return xml.toString();
	}
	private boolean contains(List<String> lists,String key){{
		return lists.contains(key);
	}
		
	}
	/**
	 * 组成内容
	 * 
	 * @return
	 */
	private String[] compContext(ServiceVO vo, String context, Map props,List<DataTypeInstance> arr,String key){
		String[] str = new String[2];
		str[0] = String.valueOf(props.get(XmlLoadHandler.index_xml_message));
		// 数据类型描述
		String type = String.valueOf(props.get(XmlLoadHandler.type_xml_message));

		if(type.startsWith("SimpleString")){//字符类型处理
			str[1] = context==null?"":context;
			arr.add(new DataTypeInstance(Integer.parseInt(str[0]), key,str[1]));
		}else if(type.startsWith("Decimal")){//数字类型
			try {
				if(null == context || "".equals(context.trim())) str[1] = "";
				else str[1] = SimpleNumberUtil.toDataString(type, context);
			} catch (Exception e) {
				_log.error("按照指定格式转化数值出错：", e);
			}
			arr.add(new DataTypeInstance(Integer.parseInt(str[0]), key,str[1]));
		}else if(type.startsWith("list")) {//list
			List<Map<String,String>> list =  (List<Map<String, String>>) vo.getBusiParam(key);
			for(int i=0;i<list.size();i++) {
				Set<Map.Entry<String, String>> sresult = list.get(i).entrySet();
				Iterator<Map.Entry<String, String>> itor = sresult.iterator();
				while(itor.hasNext()) {
					arr.add(new DataTypeInstance(i, key+"."+list.get(i).get(key),str[1]));
				}
			}
		} else {//其他
			
		}
		return str;
	}
}
