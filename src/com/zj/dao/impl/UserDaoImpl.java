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
	User getUserInfoByPhone(String phone) throws SQLException;
	/**
	 * 通过用户手机号和姓名获取用户信息方法
	 * @param name
	 * @param phone
	 * @return
	 * @throws SQLException
	 */
	User getUserInfoByPhoneAndName(String name,String phone) throws SQLException;
	
	/**
	 * 通过用户id获取单个用户信息方法接口
	 */
	User getUserInfoById(Integer id) throws SQLException;
	/**
	 * 添加用户
	 */
	Integer addUser(String user_name,String user_phone) throws SQLException;
	/**
	 * 修改用户基础信息
	 */

	Integer updateBasicInfo(Integer user_id,String user_name,String user_email,String user_describe) throws SQLException;
	
	/**
	 * 修改用户电话
	 */
	Integer updateUserPhone(Integer user_id,String user_phone) throws SQLException;
	
	/**
	 * 用户实名认证
	 */
	Integer setRealName(Integer user_id,String real_name,String user_IDcard) throws SQLException;
	
	/**
	 * 查询手机号是否存在
	 * @throws SQLException 
	 */
	Integer queryPhoneExit(String user_phone) throws SQLException;
	
	
	/**
	 * 用户更新密码
	 * @throws SQLException 
	 */
	Integer updateUserPwd(Integer user_id,String user_pwd) throws SQLException;
	
	/**
	 * 用户上传头像
	 * @throws SQLException 
	 */
	Integer addUserHead(Integer user_id,String user_headimg_url) throws SQLException;
	
	
	/**
	 * 更新用户余额
	 */
	Integer updateUserMoney(Double price,Integer user_id) throws SQLException;
	
	/**
	 * 根据电话/email和密码查询用户
	 * @throws SQLException 
	 */

	User queryUserInfo(String user_code,String user_pwd) throws SQLException;
	/**
	 * 用户充值
	 * @return
	 */
	Integer topUp(Double price,Integer user_id) throws SQLException;

}
