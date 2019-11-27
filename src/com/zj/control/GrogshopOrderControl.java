package com.zj.control;

import java.sql.SQLException;
import java.util.List;

import com.zj.dao.impl.GrogshopOrderDaoImpl;
import com.zj.dao.realize.GrogshopOrderDao;
import com.zj.entity.GrogshopOrder;

/**
 * 订单数据操作类
 * @author LanceEdward
 *
 */
public class GrogshopOrderControl {
	private GrogshopOrderDaoImpl orderDaoImpl = new GrogshopOrderDao(); 
	
	public  List<GrogshopOrder> getAllGrogshopOrderInfo() throws SQLException {
		return orderDaoImpl.getAllGrogshopOrderInfo();
	}
	
	public GrogshopOrder getGrogshopOrderInfoById(Integer GrogshopOrderID) throws SQLException{
		return orderDaoImpl.getGrogshopOrderInfoByID(GrogshopOrderID);
	}
}
