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
	 */
	int addUser(String user_phone) throws SQLException;
	/**
	 * 修改用户
	 */
	int updateUser(Integer user_id,String user_name,String user_email,String user_phone,String user_describe) throws SQLException;
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
	/**
	 * 手机号/邮箱和密码登录
	 */
	Map<String, Object> login(Map<String, Object> loginInfo);
	/**
	 * 手机号登录，若手机号存在则用户注册
	 */
	Map<String, Object> phoneLogin(Map<String, Object> phoneLoginInfo);
	/**
	 * 用户设置密码
	 */
	Map<String, Object> setUserPwd(Map<String, Object> setUserPwdInfo);
	/**
	 * 修改基本信息
	 */
	Map<String, Object> updateBasicInfo(Map<String, Object> setUserInfo);
	/**
	 * 修改密码
	 */
	Map<String, Object> updateUserPwd(Map<String, Object> updateUserPwdInfo);
	/**
	 * 用户上传头像
	 */
	Map<String, Object> addUserHead(Map<String, Object> addUserHeadInfo);
	/**
	 * 通过手机号获取用户
	 * @throws SQLException 
	 */
	User queryUserByPhone(String user_phone) throws SQLException;
	/**
	 *上传身份证和真实姓名
	 * @throws SQLException 
	 */
	Map<String, Object> realNameInfo(Map<String, Object> realNameInfo) throws SQLException;
}
