package com.flong.controller;



import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.flong.BaseController;
import com.flong.dao.UserMapper;
import com.flong.pojo.vo.UserVo;
import com.flong.utils.web.WafRequestWrapper;



@Controller
@RequestMapping("/user2")
public class UserController2 extends BaseController{
 
	
	@Resource
	private UserMapper userMapper;
	
	/**
	 * 查询
	 * @param request
	 * @param response
	 * @param model
	 */
	@RequestMapping(value="/listBySQL", method = {RequestMethod.POST,RequestMethod.GET})
	public void listBySQL(HttpServletRequest request,HttpServletResponse response,Model model){
		/**
		 * 过滤 XSS SQL 注入
		 */
		//WafRequestWrapper wr = new WafRequestWrapper(request);
		//wr.getParameter("username");
		
		List<UserVo> list=userMapper.listBySQL();
		writeJson(list, response);
	}
	
	
	
	/**
	 * 插入
	 * @param request
	 * @param response
	 * @param model
	 */
	@RequestMapping(value="/insertBySQL", method = {RequestMethod.POST,RequestMethod.GET})
	public void insertBySQL(HttpServletRequest request,HttpServletResponse response,Model model){
		
		UserVo user = new UserVo();  
		user.setUsername("张三");  
		user.setPassword("123466");  
		user.setAge(45);  			
		user.setType("2");
		
		userMapper.insertBySQL(user);
		
		writeJson(user, response);
	}
	
	/**
	 * 修改
	 * @param request
	 * @param response
	 * @param model
	 */
	@RequestMapping(value="/updateBySQL", method = {RequestMethod.POST,RequestMethod.GET})
	public void updateBySQL(HttpServletRequest request,HttpServletResponse response,Model model){
		UserVo user = new UserVo();  
		user.setUsername("周伯通");  
		user.setId(1);
		userMapper.updateBySQL(user);
		writeJson(user, response);
	}
	
	
	/**
	 * 删除
	 * @param request
	 * @param response
	 * @param model
	 */
	@RequestMapping(value="/deleteSql", method = {RequestMethod.POST,RequestMethod.GET})
	public void deleteSql(HttpServletRequest request,HttpServletResponse response,Model model){
		 String deleteSql = userMapper.deleteSql("1");
		 writeJson(deleteSql, response);
	}
	
	 
	
	
	
}
