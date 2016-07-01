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
import com.flong.pojo.entity.User;
import com.flong.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController extends BaseController{
	
	@Resource
	private UserService userService;
	
	
	
	/**
	 * 
	 * @param request
	 * @param model
	 */
	@RequestMapping(value="/list", method = {RequestMethod.POST,RequestMethod.GET})
	public void list(HttpServletRequest request,HttpServletResponse response,Model model){
		List<User> list=userService.list();
		writeJson(list, response);
		
	}
	
	/**
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/register",method = {RequestMethod.POST,RequestMethod.GET})
	public String registerIndex(HttpServletRequest request,Model model){
		User user = new User();  
		user.setUsername("张三");  
		user.setPassword("123466");  
		user.setAge(45);  			
		user.setTypeid("2");
		this.userService.inster(user);
      
	    return "index";
	}
	

	
	
}
