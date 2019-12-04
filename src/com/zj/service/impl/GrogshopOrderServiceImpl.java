package com.zj.service.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.zj.entity.GrogshopOrder;

/**
 * 
 * @author lijia
 *订单服务层接口
 */
public interface GrogshopOrderServiceImpl {
	/**
	 * 将所有订单信息包装成一个map>返回方法接口
	 * @return
	 * @throws SQLException 
	 */
	Map<String,Object> getAllGrogshopOrderInfo(Integer orderPresentPage,Integer pageSize) throws SQLException;
	/**
	 * 将单个订单信息包装成map返回方法接口
	 */
	Map<String, Object> getAllGrogshopOrderInfoByID(Integer grogshop_order_id);
	/**
	 * 添加一个订单信息方法接口
	 * @param grogshopOrder
	 * @return
	 */
	String addGrogshopOrderInfo(Map<String, Object> grogshopOrderInfo,List<Map<String, Object>> checkInPersonInfoMap);
}
