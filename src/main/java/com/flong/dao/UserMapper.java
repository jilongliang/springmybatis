package com.flong.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.SelectProvider;

import com.flong.pojo.entity.User;
import com.flong.pojo.vo.UserVo;
import com.flong.service.impl.UserServiceImpl;
import com.github.pagehelper.Page;

public interface UserMapper {
	/****
	 * ==============================================================通过xml进行操作数据库==============================================================
	 * @param id
	 * @return
	 */
	
	
    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
    
    User selectId(int id);

	User selectLogin(String username);

	List<User> list();
	
	
	
	/****
	 * ==============================================================通过注解和Java编写SQL进行操作数据库==============================================================
	 * @param id
	 * @return
	 */
	
	

	/**
	 * 通过mybatis的SQL方法进行查询，无xml配置的出来
	 * 告诉method = "listBySQL"方法的提供方，是来原于UserServiceImpl这个实现类里面获取的。
	 * List<UserVo>这个UserVo相当于是Vo
	 */
	@SelectProvider(type = UserServiceImpl.class, method = "listBySQL")
	List<UserVo> listBySQL();
	
	
	/**
	 * 插入
	 * @param user
	 * @return
	 */
	
	@SelectProvider(type = UserServiceImpl.class, method = "insertBySQL")
	String insertBySQL(UserVo user);
	 
	
	/**
	 * 修改
	 * @param user
	 * @return
	 */
	@SelectProvider(type = UserServiceImpl.class, method = "updateBySQL")
	String updateBySQL(UserVo user);
	
	/**
	 * 删除
	 * @param id
	 * @return
	 */
	@SelectProvider(type = UserServiceImpl.class, method = "deleteSql")
	String deleteSql(String id);

	
	
	
	Page<User> query(Map<String, Object> params);
	 
	
	
}