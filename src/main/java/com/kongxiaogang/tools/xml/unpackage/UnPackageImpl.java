package com.kongxiaogang.tools.xml.unpackage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.kongxiaogang.tools.xml.other.ServiceVO;


/**
 * <pre>项目名称：xhcf-back-web    
 * 类名称：UnPackageImpl    
 * 类描述：    拆解xml报文
 * 创建人：孔小刚 xiaogangkong@galaxyinternet.com    
 * 创建时间：2017年2月8日 下午5:37:27    
 * 修改人：孔小刚 xiaogangkong@galaxyinternet.com     
 * 修改时间：2017年2月8日 下午5:37:27    
 * 修改备注：       
 * @version </pre>
 */
public class UnPackageImpl {
	
	private static final Logger _log = LoggerFactory.getLogger(UnPackageImpl.class);
	
	public ServiceVO parse(String message){ 
		UnPackageHandler handler = new UnPackageHandler();
		ServiceVO vo = new ServiceVO();
		vo.setBusiParam(handler.messageToObj(message));
		return vo;
	}
	
	public static void main(String[] args){
		String msg = "<?xml version=\"1.0\" encoding=\"GBK\" standalone=\"yes\"?><INSUREQRET><MAIN><SERIALDECIMAL>1</SERIALDECIMAL><TRANSRDATE>2</TRANSRDATE><RESULTCODE>3</RESULTCODE><ERR_INFO>4</ERR_INFO></MAIN><BASE><BUS_ADVANCE_DAYS>5</BUS_ADVANCE_DAYS><TRA_ADVANCE_DAYS>6</TRA_ADVANCE_DAYS></BASE><ATTRINFO_LIST><ATTRINFO><C_LABEL_NAME>7</C_LABEL_NAME><C_KEY_NAME>8</C_KEY_NAME><C_ATTRI_VALUE>9</C_ATTRI_VALUE><C_ATTRI_TYPE>10</C_ATTRI_TYPE><N_ATTRI_LENGTH>11</N_ATTRI_LENGTH><C_OPPTI_VALUE>12</C_OPPTI_VALUE><C_ATTRI_DESC>13</C_ATTRI_DESC></ATTRINFO><ATTRINFO><C_LABEL_NAME>14</C_LABEL_NAME><C_KEY_NAME>15</C_KEY_NAME><C_ATTRI_VALUE>16</C_ATTRI_VALUE><C_ATTRI_TYPE>17</C_ATTRI_TYPE><N_ATTRI_LENGTH>18</N_ATTRI_LENGTH><C_OPPTI_VALUE>19</C_OPPTI_VALUE><C_ATTRI_DESC>20</C_ATTRI_DESC></ATTRINFO></ATTRINFO_LIST></INSUREQRET>";
		ServiceVO vo =new UnPackageImpl().parse(msg);
		System.out.println(vo);
	}
}
