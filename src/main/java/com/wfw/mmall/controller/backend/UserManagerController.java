package com.wfw.mmall.controller.backend;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wfw.mmall.common.Const;
import com.wfw.mmall.common.ServerResponse;
import com.wfw.mmall.pojo.User;
import com.wfw.mmall.service.IUserService;

/**
* @author F7689334
* @version 创建时间:2018年8月15日 下午1:45:40
* 类说明 
*/
@Controller
@RequestMapping("/manage/user")
public class UserManagerController {

	@Autowired
	private IUserService iUserService;
	
	/**
	 * 管理员登录
	 * @param username
	 * @param password
	 * @param session
	 * @return
	 */
	@RequestMapping(value="login.do",method=RequestMethod.POST)
	@ResponseBody
	public ServerResponse<User> login(String username,String password,HttpSession session) {
		ServerResponse<User> responseUser=iUserService.login(username, password);
		if (responseUser.isSuccess()) {
			User user=responseUser.getData();
			if (user.getRole()==Const.Role.ROLE_ADMIN) {
				session.setAttribute(Const.CURRENT_USER, user);
				return responseUser;
			}else {
				return ServerResponse.createByErrorMessage("不是管理员,无法登录");
			}
		}
		return responseUser;
	}
	
	
	
	
}
