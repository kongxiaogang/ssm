package com.kongxiaogang.tools.xml.inpackage;

import java.util.Comparator;


/**
 * <pre>项目名称：xhcf-back-web    
 * 类名称：XmlObjectComparator    
 * 类描述：    比较两个对象
 * 创建人：孔小刚 xiaogangkong@galaxyinternet.com    
 * 创建时间：2017年2月8日 下午2:08:16    
 * 修改人：孔小刚 xiaogangkong@galaxyinternet.com     
 * 修改时间：2017年2月8日 下午2:08:16    
 * 修改备注：       
 * @version </pre>
 */
public final class XmlObjectComparator implements Comparator<DataTypeInstance> {

	public int compare(DataTypeInstance o1, DataTypeInstance o2) {
		return o1.getIndex() - o2.getIndex();
	}

}
