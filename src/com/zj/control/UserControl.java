package com.zj.control;


import java.sql.SQLException;
import java.util.List;


import com.zj.dao.impl.UserDaoImpl;
import com.zj.dao.realize.UserDao;
import com.zj.entity.User;
/**
 * 
 * @author lijia
 *用户数据操作类
 */
public class UserControl {
	private UserDaoImpl userDaoImpl = new UserDao();
	
	public List<User> getAllUserInfo() throws SQLException {
		return userDaoImpl.getAllUserInfo();
	}
	
	public User getUserInfoByPhone(String phone) throws SQLException {
		return userDaoImpl.getUserInfoByPhone(phone);
	}
	
}
