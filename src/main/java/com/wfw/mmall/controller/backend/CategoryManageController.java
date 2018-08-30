package com.wfw.mmall.controller.backend;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wfw.mmall.common.Const;
import com.wfw.mmall.common.ResponseCode;
import com.wfw.mmall.common.ServerResponse;
import com.wfw.mmall.pojo.Category;
import com.wfw.mmall.pojo.User;
import com.wfw.mmall.service.ICategoryService;
import com.wfw.mmall.service.IUserService;

/**
 * @author wfw
 *
 */

@Controller
@RequestMapping("/manage/category")
public class CategoryManageController {
	@Autowired
	private IUserService iUserService;

	@Autowired
	private ICategoryService iCategoryService;

	/**
	 * 添加商品类别
	 * 
	 * @param session
	 * @param categoryName
	 * @param parentId
	 * @return
	 */
	@RequestMapping("/add_category.do")
	@ResponseBody
	public ServerResponse<String> addCategory(HttpSession session, String categoryName,
			@RequestParam(value = "parentId", defaultValue = "0") Integer parentId) {
		User user = (User) session.getAttribute(Const.CURRENT_USER);
		if (user == null) {
			return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "用户未登录，请登录");
		}
		if (iUserService.checkAdminRole(user).isSuccess()) {
			return iCategoryService.addCategory(categoryName, parentId);
		} else {
			return ServerResponse.createByErrorMessage("无权限操作，需要管理员权限");
		}
	}

	/**
	 * 修改类别名称
	 * 
	 * @param session
	 * @param categoryId
	 * @param categoryName
	 * @return
	 */
	@RequestMapping("/set_category_name.do")
	@ResponseBody
	public ServerResponse<String> setCategoryName(HttpSession session, Integer categoryId, String categoryName) {
		User user = (User) session.getAttribute(Const.CURRENT_USER);
		if (user == null) {
			return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "用户未登录，请登录");
		}
		if (iUserService.checkAdminRole(user).isSuccess()) {
			return iCategoryService.updateCategoryName(categoryId, categoryName);
		} else {
			return ServerResponse.createByErrorMessage("无权限操作，需要管理员权限");
		}
	}

	/**
	 * 获取该categoryId下子节点的category信息，并且不递归，保持平级
	 * 
	 * @param session
	 * @param categoryId
	 * @return
	 */
	@RequestMapping("/get_children_category.do")
	@ResponseBody
	public ServerResponse<List<Category>> getChildrenParallelCategory(HttpSession session,
			@RequestParam(value = "categoryId", defaultValue = "0") Integer categoryId) {
		User user = (User) session.getAttribute(Const.CURRENT_USER);
		if (user == null) {
			return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "用户未登录，请登录");
		}
		if (iUserService.checkAdminRole(user).isSuccess()) {
			return iCategoryService.getChildrenParallelCategory(categoryId);
		} else {
			return ServerResponse.createByErrorMessage("无权限操作，需要管理员权限");
		}
	}

	/**
	 * 深度查询，查询当前节点的id和递归子节点的id
	 * 
	 * @param session
	 * @param categoryId
	 * @return
	 */
	@RequestMapping("/get_deep_children_category.do")
	@ResponseBody
	public ServerResponse<List<Integer>> getCategoryAndDeepChildrenCategory(HttpSession session,
			@RequestParam(value = "categoryId", defaultValue = "0") Integer categoryId) {
		User user = (User) session.getAttribute(Const.CURRENT_USER);
		if (user == null) {
			return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "用户未登录，请登录");
		}
		if (iUserService.checkAdminRole(user).isSuccess()) {
			return iCategoryService.selectCategoryAndChildrenById(categoryId);
		} else {
			return ServerResponse.createByErrorMessage("无权限操作，需要管理员权限");
		}
	}
}
