package com.wfw.mmall.service;

import com.github.pagehelper.PageInfo;
import com.wfw.mmall.common.ServerResponse;
import com.wfw.mmall.pojo.Product;
import com.wfw.mmall.vo.ProductDetailVo;

/**
 * @author wfw
 * @date 2018/8/28 15:00
 */
public interface IProductService {

    /**
     * 保存或更新产品信息
     * @param product
     * @return
     */
    ServerResponse saveOrUpdateProduct(Product product);

    /**
     * 更改该productId的产品的状态
     * @param productId
     * @param status
     * @return
     */
    ServerResponse setSaleStatus(Integer productId,Integer status);

    /**
     * 商品详情
     * @param productId
     * @return
     */
    ServerResponse<ProductDetailVo> manageProductDetail(Integer productId);

    /**
     * 分页获取商品列表
     * @param pageNum
     * @param pageSize
     * @return
     */
    ServerResponse getProductList(int pageNum,int pageSize);

    /**
     * 分页搜索商品
     * @param productName
     * @param productId
     * @param pageNum
     * @param pageSize
     * @return
     */
    ServerResponse productSearch(String productName,Integer productId,int pageNum,int pageSize);

    /**
     * 前台获取商品详情
     * @param productId
     * @return
     */
    ServerResponse<ProductDetailVo> getProductDetail(Integer productId);

    /**
     * 关键字分页搜索,动态排序
     * @param keyword
     * @param categoryId
     * @param pageNum
     * @param pageSize
     * @param orderBy
     * @return
     */
    ServerResponse<PageInfo> getProductByKeywordAndCategoryId(String keyword,Integer categoryId,int pageNum,int pageSize,String orderBy);

}
