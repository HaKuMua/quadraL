package com.zj.service.impl;

import java.util.List;
import java.util.Map;
/**
 * 
 * @author lijia
 *房东服务层接口
 */
public interface LandlordServiceImpl {
	/**
	 * 将所有房东信息包装成list<map>返回方法接口
	 */
	List<Map<String, Object>> getAllLandlordInfo();
	/**
	 * 将单个房东信息包装成map返回方法接口
	 * 按照房东电话号码返回信息
	 */
	Map<String, Object> getLandlordInfoByPhone(String landlord_phone);
	/**
	 * 添加一个房东信息方法接口
	 * @param map
	 * @return
	 */
	String addLandlordInfo(Map<String, Object> map);
}
