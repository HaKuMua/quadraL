package com.zj.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.zj.control.UserControl;
import com.zj.entity.User;
/**
 * 
 * @author lijia
 *用户服务层类
 */
public class UserService {
	private UserControl userControl = new UserControl();
	/*
	 * 将所有用户信息包装成一个list<map>返回
	 */
	public List<Map<String, Object>> getAllUserInfo(){
		List<Map<String, Object>> list = null;
		try {
			List<User> userList = userControl.getAllUserInfo();
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
					list.add(map);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	/*
	 * 通过用户手机号返回一个包含此用户信息的map
	 */
	public Map<String, Object> getUserInfoByPhone(String user_phone){
		Map<String, Object> map = null;
		try {
			User user = userControl.getUserInfoByPhone(user_phone);
			if(user != null){
				map = new HashMap<String, Object>();
				map.put("user_id", user.getUser_id());
				map.put("user_name", user.getUser_name());
				map.put("user_headimg_url", user.getUser_headimg_url());
				map.put("user_email", user.getUser_email());
				map.put("user_phone", user.getUser_phone());
				map.put("user_IDcard", user.getUser_IDcard());
				map.put("lanlord_id", user.getLandlord_id());
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return map;
	}
}
