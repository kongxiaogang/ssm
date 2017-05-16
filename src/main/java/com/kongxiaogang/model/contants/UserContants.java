package com.kongxiaogang.model.contants;
/**
 * User配置常量<br>
 * 注意：公司对业务常量声明有一套统一的业务声明规范<br>
 * 此处仅为说明常量声明位置，具体业务需要统一注册申请<br>
 * @author chenxuan
 *
 */
public class UserContants {

	/** 用户相关 **/
	public static final Integer USER_ISNOUT_EXIST = 10001;       // 用户不存在
	public static final Integer USER_PASSWD_ERROR = 10002;       // 用户密码错误
	public static final Integer USER_STATUS_ISDEL = 10003;       // 用户已被删除
	public static final Integer USER_LOGIN_ERROR = 10004;        // 登录失败
	public static final Integer USER_LOGIN_SUCCESS = 11000;      // 登录成功
	public static final Integer USER_ADD_FAILED =10101;          // 用户注册失败
	public static final Integer USER_ADD_FAILED_BYEXIST =10102;  // 用户注册失败,用户已存在
	public static final Integer USER_REGIST_SUCCESS = 12000;     // 注册成功
	/** 用户类型 **/
	public static final String USER_TYPE_GUEST = "guest";
	public static final String USER_TYPE_MANAGER = "manager";
	
}
