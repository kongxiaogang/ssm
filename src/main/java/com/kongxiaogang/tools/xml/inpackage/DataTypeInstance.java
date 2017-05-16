package com.kongxiaogang.tools.xml.inpackage;

/**
 * 
 * <pre>项目名称：xhcf-back-web    
 * 类名称：DataTypeInstance    
 * 类描述：    xml对应数据处理内部对象
 * 创建人：孔小刚 xiaogangkong@galaxyinternet.com    
 * 创建时间：2017年2月8日 上午11:39:10    
 * 修改人：孔小刚 xiaogangkong@galaxyinternet.com     
 * 修改时间：2017年2月8日 上午11:39:10    
 * 修改备注：       
 * @version </pre>
 */
public class DataTypeInstance {

	// 顺序号
	private int index;
	// 数据名
	private String type;
	// 数据值
	private String value;

	public DataTypeInstance(int index, String type, String value) {
		this.index = index;
		this.type = type;
		this.value = value;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
}
