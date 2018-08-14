package com.wfw.mmall.service.impl;

import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wfw.mmall.common.Const;
import com.wfw.mmall.common.ServerResponse;
import com.wfw.mmall.common.TokenCache;
import com.wfw.mmall.dao.UserMapper;
import com.wfw.mmall.pojo.User;
import com.wfw.mmall.service.IUserService;
import com.wfw.mmall.util.MD5Util;

/**
 * @author F7689334
 *
 */
@Service("iUserService")
public class UserServiceImpl implements IUserService {

	@Autowired
	private UserMapper userMapper;
	
	@Override
	public ServerResponse<User> login(String username, String password) {
		int resultCount=userMapper.checkUsername(username);
		if (resultCount==0) {
			return ServerResponse.createByErrorMessage("用户名不存在");
		}
		
		//TODO 密码登录MD5验证
		String md5Password=MD5Util.MD5EncodeUtf8(password);
		
		User user=userMapper.selectLogin(username, md5Password);
		if (user==null) {
			return ServerResponse.createByErrorMessage("密码错误");
		}
		
		user.setPassword(StringUtils.EMPTY);
		
		return ServerResponse.createBySuccess("登录成功", user);
	}

	@Override
	public ServerResponse<String> register(User user) {
		/**
		 * 验证用户名和邮箱是否已被注册,
		 * 如果已被注册则直接返回包含错误信息的validResponse
		 */
		ServerResponse validResponse=this.checkValid(user.getUsername(), Const.USERNAME);
		if (!validResponse.isSuccess()) {
			return validResponse;
		}
		validResponse=this.checkValid(user.getEmail(),Const.EMAIL);
		if (!validResponse.isSuccess()) {
			return validResponse;
		}
		
//		int resultCount=userMapper.checkUsername(user.getUsername());
//		if (resultCount>0) {
//			return ServerResponse.createByErrorMessage("用户名已存在");
//		}
//		resultCount=userMapper.checkEmail(user.getEmail());
//		if (resultCount>0) {
//			return ServerResponse.createByErrorMessage("email已存在");
//		}
		
		user.setRole(Const.Role.ROLE_CUSTOMER);
		
		//密码MD5加密
		user.setPassword(MD5Util.MD5EncodeUtf8(user.getPassword()));
		
		int resultCount=userMapper.insert(user);
		if (resultCount==0) {
			return ServerResponse.createByErrorMessage("注册失败");
		}
		return ServerResponse.createBySuccessMessage("注册成功");
	}

	@Override
	public ServerResponse<String> checkValid(String str, String type) {
		// TODO Auto-generated method stub
		if (StringUtils.isNotBlank(type)) {
			//验证类型不是空白,开始校验
			if (Const.USERNAME.equals(type)) {
				int resultCount=userMapper.checkUsername(str);
				if (resultCount>0) {
					return ServerResponse.createByErrorMessage("用户名已存在");
				}
			}
			if (Const.EMAIL.equals(type)) {
				int resultCount=userMapper.checkEmail(str);
				if (resultCount>0) {
					return ServerResponse.createByErrorMessage("email已存在");
				}
			}
		}else {
			return ServerResponse.createByErrorMessage("参数错误");
		}
		return ServerResponse.createBySuccessMessage("校验成功");
	}

	@Override
	public ServerResponse seleteQuestion(String username) {
		ServerResponse validResponse=this.checkValid(username, Const.USERNAME);
		if (validResponse.isSuccess()) {
			return ServerResponse.createByErrorMessage("用户不存在");
		}
		String question=userMapper.selectQuestionByUsername(username);
		if (StringUtils.isNotBlank(question)) {
			return ServerResponse.createBySuccess(question);
		}
		return ServerResponse.createByErrorMessage("找回密码的问题是空的");
	}

	@Override
	public ServerResponse<String> checkAnswer(String username, String question, String answer) {
		int resultCount=userMapper.checkAnswer(username, question, answer);
		if (resultCount>0) {
			//问题及问题答案是该用户的,并且答案正确
			String forgetToken=UUID.randomUUID().toString();
			TokenCache.setKey("token_"+username, forgetToken);
			return ServerResponse.createBySuccess(forgetToken);
		}
		return ServerResponse.createByErrorMessage("问题的答案错误");
	}

	@Override
	public ServerResponse<String> forgetResetPassword(String username, String passwordNew, String forgetToken) {
		
		return null;
	}

	
	
	
	
	
	
	
	
}






