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
	public List<User> getAllUserInfo() throws SQLException;
	public User getUserInfoByPhone(String phone) throws SQLException;
}
