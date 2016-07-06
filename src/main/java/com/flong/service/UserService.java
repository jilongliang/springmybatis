 package com.flong.service;

import java.util.List;
import java.util.Map;

import com.flong.pojo.entity.User;
import com.flong.pojo.vo.UserVo;
import com.github.pagehelper.PageInfo;

public interface UserService {  	
	
	
	public List<User> list();

	public User user(User user);
	
	public User selectLogin(String username);

	public User getUserById(int i);

	public User selectByPrimaryKey(int i);
	
	public User getUserByusername(String username);
	
	public int inster(User user);
	
	public Boolean getLoginUser(User user);
	
	
	String listBySQL();

	
	String insertBySQL(UserVo user) ;
	 
	
	String updateBySQL(UserVo user);
	 
	
	
	String deleteSql(String id);

	public PageInfo<?> query(Map<String, Object> params);
	
}  
