package com.mmall.controller.backend;

import com.mmall.common.ResponseCode;
import com.mmall.common.ServerResponse;
import com.mmall.pojo.Category;
import com.mmall.pojo.User;
import com.mmall.service.ICategoryService;
import com.mmall.service.IUserService;
import com.mmall.util.CookieUtil;
import com.mmall.util.JsonUtil;
import com.mmall.util.RedisShardedPoolUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/manage/category")
public class CategoryManageController {

    @Autowired
    private ICategoryService iCategoryService;

    @Autowired
    private IUserService iUserService;

    @RequestMapping("add_category.do")
    @ResponseBody                                                                       //SpringMVC的注解
    public ServerResponse<String> addCategory(HttpServletRequest request, String categoryName, @RequestParam(value = "parentId", defaultValue = "0") Integer parentId) {
//        String loginToken = CookieUtil.readLoginToken(request);
//        if (StringUtils.isEmpty(loginToken)) {
//            return ServerResponse.createByErrorMessage("用户未登录，无法获取当前用户信息");
//        }
//        String userJson = RedisShardedPoolUtil.get(loginToken);
//        User user = JsonUtil.string2Obj(userJson, User.class);
//        if (user == null) {
//            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "用户未登录，请登录");
//        }
//        if (iUserService.checkAdminRole(user).isSuccess()) {
//            return iCategoryService.addCategory(categoryName, parentId);
//        } else {
//            return ServerResponse.createByErrorMessage("无操作权限，该操作需要管理员权限");
//        }
        return iCategoryService.addCategory(categoryName, parentId);
    }

    @RequestMapping("set_category_name.do")
    @ResponseBody
    public ServerResponse<String> setCategoryName(HttpServletRequest request, Integer categoryId, String categoryName) {
//        String loginToken = CookieUtil.readLoginToken(request);
//        if (StringUtils.isEmpty(loginToken)) {
//            return ServerResponse.createByErrorMessage("用户未登录，无法获取当前用户信息");
//        }
//        String userJson = RedisShardedPoolUtil.get(loginToken);
//        User user = JsonUtil.string2Obj(userJson, User.class);
//        if (user == null) {
//            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "用户未登录，请登录");
//        }
//        if (iUserService.checkAdminRole(user).isSuccess()) {
//            return iCategoryService.updateCategoryName(categoryId, categoryName);
//        } else {
//            return ServerResponse.createByErrorMessage("无操作权限，该操作需要管理员权限");
//        }
        return iCategoryService.updateCategoryName(categoryId, categoryName);
    }

    @RequestMapping("get_category.do")
    @ResponseBody
    public ServerResponse<List<Category>> getChildrenParallelCategory(HttpServletRequest request, @RequestParam(value = "categoryId", defaultValue = "0") Integer categoryId) {
//        String loginToken = CookieUtil.readLoginToken(request);
//        if (StringUtils.isEmpty(loginToken)) {
//            return ServerResponse.createByErrorMessage("用户未登录，无法获取当前用户信息");
//        }
//        String userJson = RedisShardedPoolUtil.get(loginToken);
//        User user = JsonUtil.string2Obj(userJson, User.class);
//        if (user == null) {
//            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "用户未登录，请登录");
//        }
//        if (iUserService.checkAdminRole(user).isSuccess()) {
//            return iCategoryService.getChildrenParallelCategory(categoryId);
//        } else {
//            return ServerResponse.createByErrorMessage("无操作权限，该操作需要管理员权限");
//        }
        return iCategoryService.getChildrenParallelCategory(categoryId);
    }

    @RequestMapping("get_deep_category.do")
    @ResponseBody
    public ServerResponse<List<Integer>> getCategoryAndDeepChildrenCategory(HttpServletRequest request, @RequestParam(value = "categoryId", defaultValue = "0") Integer categoryId) {
//        String loginToken = CookieUtil.readLoginToken(request);
//        if (StringUtils.isEmpty(loginToken)) {
//            return ServerResponse.createByErrorMessage("用户未登录，无法获取当前用户信息");
//        }
//        String userJson = RedisShardedPoolUtil.get(loginToken);
//        User user = JsonUtil.string2Obj(userJson, User.class);
//        if (user == null) {
//            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "用户未登录，请登录");
//        }
//        if (iUserService.checkAdminRole(user).isSuccess()) {
//            //查询当前节点及其子节点及其递归子节点
//            return iCategoryService.selectCategoryAndChildrenById(categoryId);
//        } else {
//            return ServerResponse.createByErrorMessage("无操作权限，该操作需要管理员权限");
//        }
        //查询当前节点及其子节点及其递归子节点
        return iCategoryService.selectCategoryAndChildrenById(categoryId);
    }
}
