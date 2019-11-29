package com.zj.service.impl;

import java.util.List;
import java.util.Map;
/**
 * 
 * @author lijia
 *入住人servlce层接口
 */
public interface CheckInPersonServiceImpl {
	/**
	 * 将所有入住人员信息包装成一个list<map>返回方法接口
	 */
	List<Map<String, Object>> getAllCheckInPersonInfo();
	/**
	 * 将单个入住人员信息包装成map返回方法接口
	 */
	Map<String, Object> getCheckInPersonInfoByIdCard(String check_in_person_ID_card);
	/**
	 * 添加一组入住人信息
	 * @param checkInPersonInfoMap
	 * @return
	 */
	String addCheckInPerson(List<Map<String, Object>> checkInPersonInfoMap);
}
