package com.wfw.mmall.common;

/**
 * @author F7689334
 *
 */
public enum ResponseCode {
	SUCCESS(0,"SUCCESS"),
	ERROR(1,"ERROR"),
	NEED_LOGIN(10,"NEED_LOGIN"),//需要登录
	ILLEGAL_ARGUMENT(2,"ILLEGAL_ARGUMENT");//参数错误
	
	private final int code;
	private final String desc;
	
	ResponseCode(int code,String desc) {
		// TODO Auto-generated constructor stub
		this.code=code;
		this.desc=desc;
	}

	public int getCode() {
		return code;
	}

	public String getDesc() {
		return desc;
	}
}
