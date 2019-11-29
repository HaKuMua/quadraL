package com.zj.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.zj.dao.UserDao;
import com.zj.dao.impl.UserDaoImpl;
import com.zj.entity.User;
import com.zj.service.impl.UserServiceImpl;
/**
 * 
 * @author lijia
 *用户服务层类
 */
public class UserService implements UserServiceImpl{
	private UserDaoImpl userDaoImpl = new UserDao();

	/**
	 * 将所有用户信息包装成一个list<map>返回
	 */
	public List<Map<String, Object>> getAllUserInfo(){
		List<Map<String, Object>> list = null;
		try {
			List<User> userList = userDaoImpl.getAllUserInfo();
			if(userList != null){
				list = new ArrayList<Map<String,Object>>();
				for(User user : userList){
					Map<String, Object> map = new HashMap<String, Object>();
					map.put("user_id", user.getUser_id());
					map.put("user_name", user.getUser_name());
					map.put("user_headimg_url", user.getUser_headimg_url());
					map.put("user_email", user.getUser_email());
					map.put("user_phone", user.getUser_phone());
					map.put("user_IDcard", user.getUser_IDcard());
					map.put("lanlord_id", user.getLandlord_id());
					map.put("real_name", user.getReal_name());
					map.put("user_describe", user.getUser_describe());
					list.add(map);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	/**
	 * 将单个用户信息包装成map返回
	 * 用user_phone查询
	 */
	public Map<String, Object> getUserInfoByPhone(String user_phone){
		Map<String, Object> map = null;
		try {
			User user = userDaoImpl.getUserInfoByPhone(user_phone);
			if(user != null){
				map = new HashMap<String, Object>();
				map.put("user_id", user.getUser_id());
				map.put("user_name", user.getUser_name());
				map.put("user_headimg_url", user.getUser_headimg_url());
				map.put("user_email", user.getUser_email());
				map.put("user_phone", user.getUser_phone());
				map.put("user_IDcard", user.getUser_IDcard());
				map.put("lanlord_id", user.getLandlord_id());
				map.put("real_name", user.getReal_name());
				map.put("user_describe", user.getUser_describe());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return map;
	}

}
