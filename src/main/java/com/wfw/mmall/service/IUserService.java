package com.wfw.mmall.service;

import com.wfw.mmall.common.ServerResponse;
import com.wfw.mmall.pojo.User;

/**
 * @author F7689334
 *
 */
public interface IUserService {
	//登录
	ServerResponse<User> login(String username,String password);
	//注册
	ServerResponse<String> register(User user);
	
	/**
	 * 验证有效性,如用户名是否已存在
	 * @param str
	 * @param type 类型:用户名或邮箱
	 * @return
	 */
	ServerResponse<String> checkValid(String str,String type);
	//通过用户名查询找回密码问题
	ServerResponse<String> seleteQuestion(String username);
	//验证找回密码问题的答案
	ServerResponse<String> checkAnswer(String username,String question,String answer);
	//忘记密码->重置密码
	ServerResponse<String> forgetResetPassword(String username,String passwordNew,String forgetToken);
	//登录状态->重置密码
	ServerResponse<String> resetPassword(String passwordOld,String passwordNew,User user);
	//更新个人信息
	ServerResponse<User> updateInfomation(User user);
	//根据用户id获取用户信息
	ServerResponse<User> getInformation(Integer id);
	
	
	
	
}
