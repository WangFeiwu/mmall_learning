package com.wfw.mmall.common;
/**
* @author F7689334
* @version 创建时间:2018年8月14日 下午2:28:08
* 类说明   一个常量类
*/
public class Const {

	public static final String CURRENT_USER="currentUser";
	
	public static final String EMAIL="email";
	public static final String USERNAME="username";
	
	public interface Role {
		int ROLE_CUSTOMER=0;//普通用户
		int ROLE_ADMIN=1;	//管理员
	}
}
