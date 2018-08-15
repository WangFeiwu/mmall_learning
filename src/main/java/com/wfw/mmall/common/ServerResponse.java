package com.wfw.mmall.common;

import java.io.Serializable;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.map.annotate.JsonSerialize;

/**
 * @author F7689334
 *
 * @param <T>
 */
//保证序列化json的时候,如果是null的对象,key也会消失:
//status=0,msg="success",data=null --> {status:0,msg:"success"}
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class ServerResponse<T> implements Serializable {
	
	private int status;
	private String msg;
	private T data;
	
	private ServerResponse(int status) {
		// TODO Auto-generated constructor stub
		this.status=status;
	}
	private ServerResponse(int status,String msg) {
		// TODO Auto-generated constructor stub
		this.status=status;
		this.msg=msg;
	}
	private ServerResponse(int status,T data) {
		// TODO Auto-generated constructor stub
		this.status=status;
		this.data=data;
	}
	private ServerResponse(int status,String msg,T data) {
		// TODO Auto-generated constructor stub
		this.status=status;
		this.msg=msg;
		this.data=data;
	}
	
	//使之不在json序列化结果当中
	@JsonIgnore	
	public boolean isSuccess() {
		return this.status==ResponseCode.SUCCESS.getCode();
	}
	
	public int getStatus() {
		return status;
	}
	public String getMsg() {
		return msg;
	}
	public T getData() {
		return data;
	}
	
	public static <T> ServerResponse<T> createBySuccess() {
		return new ServerResponse<T>(ResponseCode.SUCCESS.getCode());
	}
	public static <T> ServerResponse<T> createBySuccessMessage(String msg) {
		return new ServerResponse<T>(ResponseCode.SUCCESS.getCode(),msg);
	}
	public static <T> ServerResponse<T> createBySuccess(T data) {
		return new ServerResponse<T>(ResponseCode.SUCCESS.getCode(),data);
	}
	public static <T> ServerResponse<T> createBySuccess(String msg,T data) {
		return new ServerResponse<T>(ResponseCode.SUCCESS.getCode(),msg,data);
	}
	
	public static <T> ServerResponse<T> createByError() {
		return new ServerResponse<T>(ResponseCode.ERROR.getCode(),ResponseCode.ERROR.getDesc());
	}
	public static <T> ServerResponse<T> createByErrorMessage(String errorMessage) {
		return new ServerResponse<T>(ResponseCode.ERROR.getCode(),errorMessage);
	}
	
	public static <T> ServerResponse<T> createByErrorCodeMessage(int errorCode,String errorMessage) {
		return new ServerResponse<T>(errorCode,errorMessage);
	}
}




