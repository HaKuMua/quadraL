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
	/**
	 * 通过用户ID查询此用户的所有订单
	 * @param user_id
	 * @return
	 * @throws SQLException
	 */
	List<GrogshopOrder> getGrogshopOrderInfoByUserID(Integer user_id) throws SQLException;
	/**
	 * 通过预订ID获得单个订单信息
	 */
	GrogshopOrder getGrogshopOrderInfoByReserveID(Integer reserve_id) throws SQLException;
	/**
	 * 获取订单分页接口
	 * @param startRow
	 * @param pageSize
	 * @return
	 * @throws SQLException
	 */
	List<GrogshopOrder> queryOrderPage(Integer startRow,Integer pageSize)  throws SQLException;
	/**
	 * 获取订单总页数接口
	 * @param startRow
	 * @param pageSize
	 * @return
	 * @throws SQLException
	 */
	Long queryCountOrder()  throws SQLException;
}
