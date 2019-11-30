package com.zj.dao.impl;

import java.sql.SQLException;
import java.util.Date;
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
	/**
	 * 添加用户
	 */
	int addUser(String user_name,String user_phone) throws SQLException;
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
	Integer queryPhoneExit(String user_phone) throws SQLException;
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
	/**
	 * 通过用户id获取单个用户信息方法接口
	 * @param user_id
	 * @return
	 * @throws SQLException
	 */
	User getUserInfoByID(Integer user_id) throws SQLException;
	/**
	 * 添加一个用户信息方法接口
	 * @param user
	 * @return
	 * @throws SQLException
	 */
	int addUserInfo(User user) throws SQLException;
	/**
	 * 根据电话/email和密码查询用户
	 * @throws SQLException 
	 */
	User queryUserInfo(String user_phone,String user_email,String user_pwd) throws SQLException;
}
