package com.wfw.mmall.service;

import com.wfw.mmall.common.ServerResponse;
import com.wfw.mmall.pojo.User;

/**
 * @author F7689334
 *
 */
public interface IUserService {
	/**
	 * 登录
	 * 
	 * @param username
	 * @param password
	 * @return
	 */
	ServerResponse<User> login(String username, String password);

	/**
	 * 注册
	 * 
	 * @param user
	 * @return
	 */
	ServerResponse<String> register(User user);

	/**
	 * 验证有效性,如用户名是否已存在
	 * 
	 * @param str
	 * @param type
	 *            类型:用户名或邮箱
	 * @return
	 */
	ServerResponse<String> checkValid(String str, String type);

	/**
	 * 通过用户名查询找回密码问题
	 * 
	 * @param username
	 * @return
	 */
	ServerResponse<String> seleteQuestion(String username);

	/**
	 * 验证找回密码问题的答案
	 * 
	 * @param username
	 * @param question
	 * @param answer
	 * @return
	 */
	ServerResponse<String> checkAnswer(String username, String question, String answer);

	/**
	 * 忘记密码->重置密码
	 * 
	 * @param username
	 * @param passwordNew
	 * @param forgetToken
	 * @return
	 */
	ServerResponse<String> forgetResetPassword(String username, String passwordNew, String forgetToken);

	/**
	 * 登录状态->重置密码
	 * 
	 * @param passwordOld
	 * @param passwordNew
	 * @param user
	 * @return
	 */
	ServerResponse<String> resetPassword(String passwordOld, String passwordNew, User user);

	/**
	 * 更新个人信息
	 * 
	 * @param user
	 * @return
	 */
	ServerResponse<User> updateInfomation(User user);

	/**
	 * 根据用户id获取用户信息
	 * 
	 * @param id
	 * @return
	 */
	ServerResponse<User> getInformation(Integer id);

	/**
	 * 验证是否管理员
	 * 
	 * @param user
	 * @return
	 */
	ServerResponse checkAdminRole(User user);

}
