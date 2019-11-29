package com.zj.dao.impl;

import java.sql.SQLException;
import java.util.List;

import com.zj.entity.GrogshopOrder;

/**
 * 
 * @author lijia
 *订单数据库操作接口
 */
public interface GrogshopOrderDaoImpl {
	/**
	 * 获取所有酒店订单信息DAO层接口
	 * @return
	 * @throws SQLException
	 */
	List<GrogshopOrder> getAllGrogshopOrderInfo() throws SQLException;
	/**
	 * 通过ID获取酒店订单信息DAO层接口
	 * @param GrogshopOrderID
	 * @return
	 * @throws SQLException
	 */
	GrogshopOrder getGrogshopOrderInfoByID(Integer GrogshopOrderID) throws SQLException;
	/**
	 * 添加酒店订单信息DAO层接口
	 * @param grogshopOrder
	 * @return
	 * @throws SQLException
	 */
	int addGrogshopOrderInfo(GrogshopOrder grogshopOrder) throws SQLException;
}
