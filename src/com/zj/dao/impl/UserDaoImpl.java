package com.zj.dao.impl;

import java.sql.SQLException;
import java.util.List;

import com.zj.entity.User;

/**
 * 
 * @author lijia
 *用户数据库操作接口
 */
public interface UserDaoImpl {
	/**
	 * 获取所有用户信息方法接口
	 */
	List<User> getAllUserInfo() throws SQLException;
	/**
	 * 通过用户手机号获取单个用户信息方法接口
	 */
	public User getUserInfoByPhone(String phone) throws SQLException;
	/*
	 * 添加用户
	 */
	int addUser(String user_name,String user_phone,String user_IDcard,Integer landlord_id,String user_pwd,)
	User getUserInfoByPhone(String phone) throws SQLException;
}
