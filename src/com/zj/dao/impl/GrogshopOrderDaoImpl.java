package com.zj.dao.impl;

import java.sql.SQLException;
import java.util.List;

import com.zj.entity.CheckInPerson;
import com.zj.entity.GrogshopOrder;

/**
 * 
 * @author lijia 订单数据库操作接口
 */
public interface GrogshopOrderDaoImpl {
	/**
	 * 获取所有酒店订单信息DAO层接口
	 * 
	 * @return
	 * @throws SQLException
	 */
	List<GrogshopOrder> getAllGrogshopOrderInfo() throws SQLException;

	/**
	 * 通过ID获取酒店订单信息DAO层接口
	 * 
	 * @param GrogshopOrderID
	 * @return
	 * @throws SQLException
	 */
	GrogshopOrder getGrogshopOrderInfoByID(String GrogshopOrderID)
			throws SQLException;

	/**
	 * 添加酒店订单信息DAO层接口
	 * 
	 * @param grogshopOrder
	 * @return
	 * @throws SQLException
	 */
	int addGrogshopOrderInfo(GrogshopOrder grogshopOrder) throws SQLException;

	/**
	 * 通过用户ID查询此用户的所有订单
	 * 
	 * @param user_id
	 * @return
	 * @throws SQLException
	 */
	List<GrogshopOrder> getGrogshopOrderInfoByUserID(Integer user_id)
			throws SQLException;
	
	
	/**
	 * 通过用户ID查询此用户的所有已完成订单
	 * @param user_id
	 * @return
	 * @throws SQLException
	 */
	List<GrogshopOrder> getFinishOrderInfoByUserID(Integer user_id)
			throws SQLException;
	
	/**
	 * 通过用户ID查询此用户的所有未完成订单
	 * @param user_id
	 * @return
	 * @throws SQLException
	 */
	List<GrogshopOrder> getNotOrderInfoByUserID(Integer user_id)
			throws SQLException;

	/**
	 * 通过预订ID获得单个订单信息
	 */
	GrogshopOrder getGrogshopOrderInfoByReserveID(Integer reserve_id)
			throws SQLException;

	/**
	 * 获取订单分页接口
	 * 
	 * @param startRow
	 * @param pageSize
	 * @return
	 * @throws SQLException
	 */
	List<GrogshopOrder> queryOrderPage(Integer startRow, Integer pageSize)
			throws SQLException;

	/**
	 * 获取订单总页数接口
	 * 
	 * @param startRow
	 * @param pageSize
	 * @return
	 * @throws SQLException
	 */
	Long queryCountOrder() throws SQLException;

	/**
	 * 房东改变订单状态
	 * @param order_id
	 * @param landlordState
	 * @return
	 * @throws SQLException
	 */
	Integer updateLandlordStatus(String order_id,Integer landlordState )
			throws SQLException;
	/**
	 * 用户改变订单状态
	 * @param order_id
	 * @param userState
	 * @return
	 * @throws SQLException
	 */
	Integer updateUserStatus(String order_id,Integer userState )
			throws SQLException;

	/**
	 * 根据订单id查到房东id
	 * 
	 * @param order_id
	 * @return
	 * @throws SQLException
	 */
	Integer getHouseUserIdByOrderId(String order_id) throws SQLException;

	/**
	 * 根据房东id查到订单
	 * @param user_id
	 * @param state
	 * @param startRow
	 * @param pageSize
	 * @return
	 * @throws SQLException
	 */
	List<GrogshopOrder> getOrderByHouseUserId(Integer user_id, Integer state,
			Integer startRow, Integer pageSize) throws SQLException;

	/**
	 * 获取一个房东的指定订单信息的数量
	 * 
	 * @return
	 * @throws SQLException
	 */
	Long getOrderNumByState(Integer user_id, Integer state)
			throws SQLException;
	
	/**
	 * 根据订单id查到所有入住人
	 * 
	 * @param order_id
	 * @return
	 * @throws SQLException
	 */
	List<CheckInPerson> getPersonByOrderId(String order_id) throws SQLException;
}
