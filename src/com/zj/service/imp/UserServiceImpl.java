package com.zj.service.imp;

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
	 * 将单个用户信息包装成map返回
	 * 用user_id查询
	 */
	Map<String, Object> getUserInfoById(Integer user_id);
	
}
