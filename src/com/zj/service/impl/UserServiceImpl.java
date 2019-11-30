package com.zj.service.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.zj.entity.User;
/**
 * 
 * @author lijia
 *用户服务层接口
 */
public interface UserServiceImpl {
	/**
	 * 将所有用户信息包装成一个list<map>返回
	 */
	List<Map<String, Object>> getAllUserInfo();
	/**
	 * 将单个用户信息包装成map返回
	 * 用user_phone查询
	 */
	Map<String, Object> getUserInfoByPhone(String user_phone);
	/**
	 * 添加用户
	 * 返回user
	 */
	User addUser(String user_phone) throws SQLException;
	/**
	 * 修改用户
	 */
	int updateUser(Integer user_id,String user_name,String user_email,String user_phone,String real_name,String user_describe,String user_IDcard) throws SQLException;
	/**
	 * 通过id查询用户
	 * @throws SQLException 
	 */
	User queryUserById(Integer user_id) throws SQLException;
	/**
	 * 查询手机号是否存在
	 * @throws SQLException 
	 */
	boolean queryPhoneExit(String user_phone) throws SQLException;
	/**
	 * 用户设置密码
	 * @throws SQLException 
	 */
	int setUserPwd(Integer user_id,String user_pwd) throws SQLException;
	/**
	 * 用户修改密码
	 * @throws SQLException 
	 */
	int updateUserPwd(Integer user_id,String user_pwd) throws SQLException;
	/**
	 * 用户上传头像
	 * @throws SQLException 
	 */
	int addUserHead(Integer user_id,String user_headimg_url) throws SQLException;
	/** 添加一个用户方法接口
	 * @param map
	 * @return
	 */
//	String addUserInfo(Map<String, Object> map);
	/**
	 * 根据电话/email和密码查询用户
	 * @throws SQLException 
	 */
	User queryUserInfo(String user_phone,String user_email,String user_pwd) throws SQLException;
}
