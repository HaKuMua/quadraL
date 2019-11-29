package com.zj.service.impl;

import java.util.List;
import java.util.Map;
/**
 * 
 * @author lijia
 *订单服务层接口
 */
public interface GrogshopOrderServiceImpl {
	/**
	 * 将所有订单信息包装成一个list<map>返回方法接口
	 * @return
	 */
	List<Map<String, Object>> getAllGrogshopOrderInfo();
	/**
	 * 将单个订单信息包装成map返回方法接口
	 */
	Map<String, Object> getAllGrogshopOrderInfoByID(Integer grogshop_order_id);
}
