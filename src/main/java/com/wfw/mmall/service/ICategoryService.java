/**
 * 
 */
package com.wfw.mmall.service;

import java.util.List;
import java.util.Set;

import com.wfw.mmall.common.ServerResponse;
import com.wfw.mmall.pojo.Category;

/**
 * @author wfw
 *
 */
public interface ICategoryService {

	/**
	 * 添加商品类别
	 * 
	 * @param categoryName
	 * @param parentId
	 * @return
	 */
	ServerResponse<String> addCategory(String categoryName, Integer parentId);

	/**
	 * 更改类别名称
	 * 
	 * @param categoryId
	 * @param categoryName
	 * @return
	 */
	ServerResponse<String> updateCategoryName(Integer categoryId, String categoryName);

	/**
	 * 获取parentId为该categoryId的category列表
	 * 
	 * @param categoryId
	 * @return
	 */
	ServerResponse<List<Category>> getChildrenParallelCategory(Integer categoryId);

	/**
	 * 递归查询本节点的id及孩子节点的id
	 * 
	 * @param categoryId
	 * @return
	 */
	ServerResponse<List<Integer>> selectCategoryAndChildrenById(Integer categoryId);

	/**
	 * 递归算法，算出子节点
	 * 
	 * @param categorySet
	 * @param categoryId
	 * @return
	 */
	Set<Category> findChildCategory(Set<Category> categorySet, Integer categoryId);

}
