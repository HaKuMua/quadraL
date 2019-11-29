package com.zj.service.impl;

import java.util.List;
import java.util.Map;
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
	 * 添加一个用户方法接口
	 * @param map
	 * @return
	 */
	String addUserInfo(Map<String, Object> map);
}
