package com.zj.service.impl;

import java.util.List;
import java.util.Map;


/**
 * 预定信息接口
 * @author LanceEdward
 *
 */
public interface ReserveServiceImpl {
	/**
	 * 将所有预定信息包装成一个list<map>返回
	 */
	List<Map<String, Object>> getAllReserve();
	/**
	 * 获得所有已预订时间
	 * @return
	 */
	List<String> getReserveDate(Integer house_id);
//	/**
//	 * 添加一个订单预定信息方法接口
//	 * @param reserve
//	 * @return
//	 */
//	String addReserveInfo(Map<String, Object> reserveInfo);
}
