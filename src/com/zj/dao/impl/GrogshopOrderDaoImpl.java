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
	public List<GrogshopOrder> getAllGrogshopOrderInfo() throws SQLException;
	public GrogshopOrder getGrogshopOrderInfoByID(Integer GrogshopOrderID) throws SQLException;
}
