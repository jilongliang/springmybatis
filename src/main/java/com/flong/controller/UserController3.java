package com.flong.controller;



import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.flong.BaseController;
import com.flong.dao.UserMapper;
import com.flong.pojo.entity.User;
import com.flong.pojo.vo.UserVo;
import com.flong.service.UserService;
import com.flong.utils.WebUtil;
import com.github.pagehelper.PageInfo;



@Controller
@RequestMapping("/user3")
public class UserController3 extends BaseController{
 
	
	@Resource private UserMapper userMapper;
	
	@Resource private UserService userService;
	
	/**
	 *  查询用户
	 * @param modelMap
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/list")
	public ModelMap get(ModelMap modelMap, HttpServletRequest request) {
		Map<String, Object> params = WebUtil.getParameterMap(request);
		PageInfo<?> list = userService.query(params);
		
		return setSuccessModelMap(modelMap, list);
	}
	 
	
	
	@ResponseBody
	@RequestMapping(value = "/getUserListByPro")
	public ModelMap getUserListByPro(ModelMap modelMap, HttpServletRequest request,HttpServletResponse response) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("username", "周伯通");
		List<UserVo> selectUserListByPro = userMapper.selectUserListByPro(params);
		
		//writeJSON(selectUserListByPro, response);
		return setSuccessModelMap(modelMap, selectUserListByPro);
		 
	}
	 
	
	@RequestMapping
    public String getIndexPage() {
        return "user";
    }
	
	
	@RequestMapping("/userlist.json")
    public @ResponseBody List<User> getUserList() {
        return userService.list();
    }
	
	
}
