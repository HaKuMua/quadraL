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
	 * 根据用户电话查询并返回其信息
	 * 
	 */
	Map<String, Object> getUserInfoByPhone(String user_phone);
	
	
	
	/**
	 * 修改用户基本信息
	 * @throws SQLException 
	 */
	Map<String, Object> updateBasicInfo(Map<String, Object> userInfo);
	
	
	/**
	 * 手机号/邮箱和密码登录
	 * @throws SQLException 
	 */
	Map<String, Object> loginByCode(Map<String, Object> userInfo);
	
	/**
	 * 手机号登录
	 */
	Map<String, Object> loginByPhone(String user_phone);
	
	/**
	 * 用户实名认证
	 * @throws SQLException 
	 */
	Map<String, Object> setRealName(Map<String, Object> userInfo) ;
	
	/**
	 * 设置密码
	 * @throws SQLException 
	 */
	Map<String, Object> setUserPwd(Map<String, Object> userInfo);
	
	/**
	 * 修改密码
	 * @throws SQLException 
	 */
	Map<String, Object> updateUserPwd(Map<String, Object> userInfo);
	
	/**
	 * 用户上传头像
	 * @throws SQLException 
	 */

	Map<String, Object> addUserHead(Integer user_id,String url);

}
