package com.flong.service.impl;

import java.util.List;

import org.apache.ibatis.jdbc.SQL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.flong.dao.UserMapper;
import com.flong.pojo.entity.User;
import com.flong.pojo.vo.UserVo;
import com.flong.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserMapper userMapper;

	
	
	public User getUserById(int id) {
		System.out.println(id + this.userMapper.selectId(id).getUsername());
		return this.userMapper.selectId(id);
	}

	public Boolean getLoginUser(User userLogin) {
		if (userLogin.getUsername().equals("") || (userLogin.getPassword().equals(""))) {
			return false;
		} else {

			User user = new User();
			if (this.userMapper.selectLogin(userLogin.getUsername()) != null) {
				user = userMapper.selectLogin(userLogin.getUsername());
				if (user.toString().isEmpty()) {
					return false;
				} else {
					if (user.getPassword().equals(userLogin.getPassword())) {
						return true;
					} else {
						return false;
					}
				}
			} else {
				return false;
			}
		}
	}

	public User user(User user) {
		return null;
	}

	public User selectLogin(String username) {
		return this.userMapper.selectLogin(username);
	}

	public User selectByPrimaryKey(int i) {
		return this.userMapper.selectByPrimaryKey(i);
	}

	public User getUserByusername(String username) {
		return this.userMapper.selectLogin(username);
	}

	public int inster(User user) {
		int i = this.userMapper.insert(user);
		return i;
	}

	public List<User> list() {
		return userMapper.list();
	}

	
	
	
	/****
	 * ==============================================================通过Java编写SQL进行操作数据库==================================
	 * 
	 * http://www.mybatis.org/mybatis-3/index.html
	 * @param id
	 * @return
	 */
	
	public String listBySQL() {
		 return new SQL(){
	        {
	        	 SELECT(" A.id,A.username,A.password ,B.type ")//SELECT关键字
	        	 .FROM(" user A ")//FROM关键字
	        	 .LEFT_OUTER_JOIN(" type B  on A.typeid=B.id");  //左连接
	                 
	        }}.toString();
	 
	}
	
	/**
	 * 
	 * @param user 这个是vo传值.#的字段要和vo实体一直才可以能获取到值.
	 * @return
	 */
	
	public String insertBySQL(UserVo user) {
		String sql = new SQL() 
					.INSERT_INTO("user")
				    .VALUES("username, password ,typeid,age", "#{username}, #{password},#{type},#{age}")
				    .toString();
		  return sql;
		
	}
	
	
	public String updateBySQL(UserVo user) {
		 return new SQL() {{
			    UPDATE("user");
			    SET("username = #{username}");
			    WHERE("id = #{id}");
		 }}.toString();
		
	}
	
	
	public String deleteSql(String id) {
		  return new SQL() {{
		    DELETE_FROM("user");
		    WHERE("ID = #{id}");
		  }}.toString();
	}


}
