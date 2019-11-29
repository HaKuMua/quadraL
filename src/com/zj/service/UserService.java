package com.zj.service;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import cn.com.uitl.UUIDGenerator;

import cn.com.uitl.CheckoutEmail;
import cn.com.uitl.CheckoutIDCard;
import cn.com.uitl.CheckoutPhoneNumber;

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
					map.put("is_lanlord", user.getIs_landlord());
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
				map.put("is_lanlord", user.getIs_landlord());
				map.put("real_name", user.getReal_name());
				map.put("user_describe", user.getUser_describe());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return map;
	}
	/**
	 * 添加用户
	 */
	public int addUser(String user_phone) throws SQLException {
		//自动生成user_name
		String user_name = UUIDGenerator.getUUID().substring(0,8);
		return userDaoImpl.addUser(user_name, user_phone);
	}
	/**
	 * 修改用户
	 */
	public int updateUser(Integer user_id, String user_name,
			String user_email, String user_phone,
			String real_name,String user_describe,String user_IDcard) throws SQLException {
		return userDaoImpl.updateUser(user_id, user_name, user_email, user_phone, real_name, user_describe, user_IDcard);
	}
	/**
	 * 通过id查询用户
	 * @throws SQLException 
	 */
	public User queryUser(Integer user_id) throws SQLException {
		return userDaoImpl.queryUser(user_id);
	}
	/**
	 * 查询手机号是否存在
	 * @throws SQLException 
	 */
	public boolean queryPhoneExit(String user_phone) throws SQLException {
		boolean bool = true;
		Integer count = userDaoImpl.queryPhoneExit(user_phone);
		if(count == null) {
			bool = false;
		}
		return bool;
	}
	/**
	 * 用户设置密码
	 * @throws SQLException 
	 */
	public int setUserPwd(Integer user_id, String user_pwd) throws SQLException {
		return userDaoImpl.setUserPwd(user_id, user_pwd);
	}
	/**
	 * 用户修改密码
	 * @throws SQLException 
	 */
	public int updateUserPwd(Integer user_id, String user_pwd)
			throws SQLException {
		return userDaoImpl.updateUserPwd(user_id, user_pwd);
	}
	/**
	 * 用户上传头像
	 * @throws SQLException 
	 */
	public int addUserHead(Integer user_id, String user_headimg_url)
			throws SQLException {
		return userDaoImpl.addUserHead(user_id, user_headimg_url);
	}
	/**
	 * 添加一个用户信息
	 * @param map
	 * @return
	 */
	public String addUserInfo(Map<String, Object> map){
		User user = new User();
		if(!CheckoutEmail.checkEmail(map.get("user_email").toString())){
			return "邮箱有误";
		}
		try {
			if(!CheckoutIDCard.IDCardValidate(map.get("user_IDcard").toString())){
				return "身份证有误";
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		if(!CheckoutPhoneNumber.isPhoneNumberValid(map.get("user_phone").toString())){
			return "手机号有误";
		}
		try{
		user.setUser_describe(map.get("user_describe").toString());
		user.setUser_email(map.get("user_email").toString());
		user.setUser_headimg_url(map.get("user_headimg_url").toString());
		user.setUser_id(Integer.valueOf((String) map.get("is_landlord")) );
		user.setUser_IDcard(map.get("user_IDcard").toString());
		user.setUser_name(map.get("user_name").toString());
		user.setUser_phone( map.get("user_phone").toString());
		user.setReal_name( map.get("real_name").toString());
		user.setUser_pwd( map.get("user_pwd").toString());
		}catch (Exception e) {
			e.printStackTrace();
			return "输入有误";
		}
		try {
			if(userDaoImpl.addUserInfo(user) > 0){
				return "插入成功";
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "插入失败";
	}


}
