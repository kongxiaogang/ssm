package com.kongxiaogang.tools.util;

import java.text.DecimalFormat;

/**
 * 
 * <pre>项目名称：xhcf-back-web    
 * 类名称：SimpleNumberUtil    
 * 类描述：   按照格式转化数据 
 * 创建人：孔小刚 xiaogangkong@galaxyinternet.com    
 * 创建时间：2017年2月8日 下午2:32:26    
 * 修改人：孔小刚 xiaogangkong@galaxyinternet.com     
 * 修改时间：2017年2月8日 下午2:32:26    
 * 修改备注：       
 * @version </pre>
 */
public class SimpleNumberUtil {
	/**
	 * 按照项目需求特殊处理
	 * 
	 * @param type
	 * @param value
	 * @return
	 * @throws Exception
	 */
	public static String toDataString(String type, String value)
			throws Exception {
		if (null == value || "".equals(value.trim()))
			return value;
		String tmp = "";
		int vallen = value.length();
		if (vallen == 1)
			tmp = "0.0" + value;
		else if (vallen == 2)
			tmp = "0." + value;
		else
			tmp = value.substring(0, vallen - 2) + "."
					+ value.substring(vallen - 2, vallen);
		DecimalFormat df = new DecimalFormat(createFormatstring(type));
		return df.format(Double.parseDouble(tmp));
	}

	/**
	 * 将数据转化为需要类型
	 * 
	 * @return
	 */
	public static String toDataString(String type, String value, int flag)
			throws Exception {
		DecimalFormat df = new DecimalFormat(createFormatstring(type));
		return df.format(Double.parseDouble(value));
	}

	/**
	 * 生成格式化字符串
	 * 
	 * @return
	 */
	private static String createFormatstring(String type) throws Exception {
		StringBuffer buff = new StringBuffer();
		int spoix = type.indexOf("(") + 1;
		int epoix = type.indexOf(")");
		String ssatrt = type.substring(spoix, epoix);
		String[] nums = ssatrt.split("\\,");
		int numns = Integer.parseInt(nums[0]);
		int numne = Integer.parseInt(nums[1]);

		for (int i = 0; i < (numns - numne - 1); i++)
			buff.append("#");
		buff.append("0.");
		for (int i = 0; i < numne; i++)
			buff.append("0");

		return buff.toString();
	}

	public static String toDeflutDateString(String strNumber, int integralLen,
			int decimalLen) {
		StringBuffer buff = new StringBuffer();
		for (int i = 0; i < (integralLen - 1); i++)
			buff.append("#");
		buff.append("0.");
		for (int i = 0; i < decimalLen; i++)
			buff.append("0");

		DecimalFormat df = new DecimalFormat(buff.toString());
		return df.format(Double.parseDouble(strNumber));
	}
}
