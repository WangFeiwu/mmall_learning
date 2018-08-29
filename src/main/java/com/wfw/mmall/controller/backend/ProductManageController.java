package com.wfw.mmall.controller.backend;

import com.google.common.collect.Maps;
import com.wfw.mmall.common.Const;
import com.wfw.mmall.common.ResponseCode;
import com.wfw.mmall.common.ServerResponse;
import com.wfw.mmall.pojo.Product;
import com.wfw.mmall.pojo.User;
import com.wfw.mmall.service.IFileService;
import com.wfw.mmall.service.IProductService;
import com.wfw.mmall.service.IUserService;
import com.wfw.mmall.util.PropertiesUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * @author wfw
 * @date 2018/8/28 14:55
 */

@Controller
@RequestMapping("/manage/product")
public class ProductManageController {

    @Autowired
    private IUserService iUserService;

    @Autowired
    private IProductService iProductService;

    @Autowired
    private IFileService iFileService;

    /**
    * Created by wfw
    * Date on 2018/8/28 15:31
    * 修改或新增商品信息
    */
    @RequestMapping(value = "/save.do",method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse productSave(HttpSession session, Product product){
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if (user == null) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "用户未登录，请登录");
        }
        if (iUserService.checkAdminRole(user).isSuccess()) {
            return iProductService.saveOrUpdateProduct(product);
        } else {
            return ServerResponse.createByErrorMessage("无权限操作，需要管理员权限");
        }
    }

    /**
    * Created by wfw
    * Date on 2018/8/28 15:32
    * 产品上下架，更新产品的销售状态
    */
    @RequestMapping("/set_sale_status.do")
    @ResponseBody
    public ServerResponse setSaleStatus(HttpSession session, Integer productId,Integer status){
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if (user == null) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "用户未登录，请登录");
        }
        if (iUserService.checkAdminRole(user).isSuccess()) {
            return iProductService.setSaleStatus(productId,status);
        } else {
            return ServerResponse.createByErrorMessage("无权限操作，需要管理员权限");
        }
    }

    /**
    * Created by wfw
    * Date on 2018/8/28 15:49
    * 获取产品详情
    */
    @RequestMapping("/detail.do")
    @ResponseBody
    public ServerResponse getDetail(HttpSession session, Integer productId){
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if (user == null) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "用户未登录，请登录");
        }
        if (iUserService.checkAdminRole(user).isSuccess()) {
            return iProductService.manageProductDetail(productId);
        } else {
            return ServerResponse.createByErrorMessage("无权限操作，需要管理员权限");
        }
    }

    /**
    * Created by wfw
    * Date on 2018/8/29 10:16
    * 分页获取商品列表
    */
    @RequestMapping("/list.do")
    @ResponseBody
    public ServerResponse getList(HttpSession session, @RequestParam(value = "pageNum",defaultValue = "1") int pageNum, @RequestParam(value = "pageSize",defaultValue = "10") int pageSize){
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if (user == null) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "用户未登录，请登录");
        }
        if (iUserService.checkAdminRole(user).isSuccess()) {
            return iProductService.getProductList(pageNum,pageSize);
        } else {
            return ServerResponse.createByErrorMessage("无权限操作，需要管理员权限");
        }
    }

    /**
    * Created by wfw
    * Date on 2018/8/29 11:03
    * 搜索商品
    */
    @RequestMapping("/search.do")
    @ResponseBody
    public ServerResponse productSearch(HttpSession session,String productName,Integer productId, @RequestParam(value = "pageNum",defaultValue = "1") int pageNum, @RequestParam(value = "pageSize",defaultValue = "10") int pageSize){
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if (user == null) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "用户未登录，请登录");
        }
        if (iUserService.checkAdminRole(user).isSuccess()) {
            return iProductService.productSearch(productName,productId,pageNum,pageSize);
        } else {
            return ServerResponse.createByErrorMessage("无权限操作，需要管理员权限");
        }
    }

    public ServerResponse upload(MultipartFile file, HttpServletRequest request){
        String path=request.getSession().getServletContext().getRealPath("upload");
        String targetFileName=iFileService.upload(file,path);
        String url= PropertiesUtil.getProperty("ftp.server.http.prefix")+targetFileName;
        Map fileMap= Maps.newHashMap();
        fileMap.put("uri",targetFileName);
        fileMap.put("url",url);
        return ServerResponse.createBySuccess(fileMap);
    }

}
