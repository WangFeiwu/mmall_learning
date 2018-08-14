package com.wfw.mmall.controller.portal;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wfw.mmall.common.Const;
import com.wfw.mmall.common.ServerResponse;
import com.wfw.mmall.pojo.User;
import com.wfw.mmall.service.IUserService;

/**
 * @author F7689334
 *
 */
@Controller
@RequestMapping("/user/")
public class UserController {
	@Autowired
	private IUserService iUserService;
	
	/**
	 * 用户登录
	 * @param username
	 * @param password
	 * @param session
	 * @return
	 */
	@RequestMapping(value="login.do",method=RequestMethod.POST)
	@ResponseBody
	public ServerResponse<User> login(String username,String password,HttpSession session) {
		ServerResponse<User> response = iUserService.login(username, password);
		if (response.isSuccess()) {
			session.setAttribute(Const.CURRENT_USER, response.getData());
		}
		
		return response;
	}
	
	/**
	 * 注销登录
	 * @param session
	 * @return
	 */
	@RequestMapping(value="logout.do",method=RequestMethod.GET)
	@ResponseBody
	public ServerResponse<String> logout(HttpSession session) {
		session.removeAttribute(Const.CURRENT_USER);
		return ServerResponse.createBySuccess();
	}
	
	/**
	 * 用户注册
	 * @param user
	 * @return
	 */
	@RequestMapping(value="register.do",method=RequestMethod.GET)
	@ResponseBody
	public ServerResponse<String> register(User user) {
		
		return iUserService.register(user);
	}
	
	/**
	 * 验证用户名和邮箱是否已存在
	 * @param str
	 * @param type
	 * @return
	 */
	@RequestMapping(value="check_valid.do",method=RequestMethod.GET)
	@ResponseBody
	public ServerResponse<String> checkValid(String str,String type) {
		return iUserService.checkValid(str, type);
	}
	
	/**
	 * 获取当前用户的信息
	 * @param session
	 * @return
	 */
	@RequestMapping(value="get_user_info.do",method=RequestMethod.GET)
	@ResponseBody
	public ServerResponse<User> getUserInfo(HttpSession session) {
		User user=(User)session.getAttribute(Const.CURRENT_USER);
		if (user!=null) {
			return ServerResponse.createBySuccess(user);
		}
		return ServerResponse.createByErrorMessage("用户未登录,无法获取当前用户的信息");
	}
	
	/**
	 * 通过用户名获取找回密码提示问题
	 * @param username
	 * @return
	 */
	@RequestMapping(value="forget_get_question.do",method=RequestMethod.GET)
	@ResponseBody
	public ServerResponse<String> forgetGetQuestion(String username) {
		
		return iUserService.seleteQuestion(username);
	}
	
	/**
	 * 验证问题和答案的正确
	 * @param username
	 * @param question
	 * @param answer
	 * @return
	 */
	@RequestMapping(value="foget_check_answer.do",method=RequestMethod.GET)
	@ResponseBody
	public ServerResponse<String> fogetCheckAnswer(String username,String question,String answer) {
		return iUserService.checkAnswer(username, question, answer);
	}
	
	/**
	 * 重置密码
	 * @param username
	 * @param passwordNew
	 * @param forgetToken 需要验证生成的token
	 * @return
	 */
	@RequestMapping(value="forget_reset_password.do",method=RequestMethod.GET)
	@ResponseBody
	public ServerResponse<String> forgetResetPassword(String username,String passwordNew,String forgetToken) {
		return null;
	}
	
	
	
	
	
}







