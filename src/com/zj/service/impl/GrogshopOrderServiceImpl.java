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
	Map<String,Object> getAllGrogshopOrderInfo(Integer limit,Integer page) throws SQLException;
	/**
	 * 将单个订单信息包装成map返回方法接口
	 */
	Map<String, Object> getAllGrogshopOrderInfoByID(Integer grogshop_order_id);
	/**
	 * 添加一个订单信息方法接口
	 * @param grogshopOrder
	 * @return
	 */
	Integer addGrogshopOrderInfo(Map<String, Object> grogshopOrderInfo,List<Map<String, Object>> checkInPersonInfoMap);
	/**
	 * 通过用户ID获取此用户的所有订单信息
	 */
	List<Map<String, Object>> getGrogshopOrderInfoByUserID(Integer user_id);
	/**
	 * 通过房东ID获取所有在此房东的房子中下单的用户订单信息
	 * @param user_id
	 * @return
	 */
	List<Map<String, Object>> getGrogshopOrderInfoByLandlordID(Integer user_id);
	
}
