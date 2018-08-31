package com.wfw.mmall.controller.portal;

import com.wfw.mmall.common.ServerResponse;
import com.wfw.mmall.service.IProductService;
import com.wfw.mmall.vo.ProductDetailVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author wfw
 * @date 2018/8/31 9:53
 */
@Controller
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private IProductService iProductService;

    /**
    * Created by wfw
    * Date on 2018/8/31 9:57
    * 前台展示商品详情
    */
    @RequestMapping("/detail.do")
    @ResponseBody
    public ServerResponse<ProductDetailVo> detail(Integer productId){
        return iProductService.getProductDetail(productId);
    }

    /**
    * Created by wfw
    * Date on 2018/8/31 10:17
    * 搜索商品
    */
    @RequestMapping("/search.do")
    @ResponseBody
    public ServerResponse productSearch(@RequestParam(value = "keyWord",required = false) String keyWord,
                                        @RequestParam(value = "categoryId",required = false) Integer categoryId,
                                        @RequestParam(value = "pageNum",defaultValue = "1") int pageNum,
                                        @RequestParam(value = "pageSize",defaultValue = "10") int pageSize,
                                        @RequestParam(value = "orderBy",defaultValue = "") int orderBy){
            return ServerResponse.createByErrorMessage("无权限操作，需要管理员权限");
    }


}
