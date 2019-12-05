package com.zj.dao.impl;

import java.sql.SQLException;
import java.util.List;

import com.zj.entity.GrogshopOrder;
import com.zj.entity.Reserve;

/**
 * 
 * @author lijia
 *预定表数据库操作接口
 */
public interface ReserveDaoImpl {
	int addGrogshopOrderInfo(GrogshopOrder grogshopOrder) throws SQLException;
	
	/**
	 * 预定分页接口
	 * @param startRow
	 * @param pageSize
	 * @return
	 * @throws SQLException
	 */
	List<Reserve> queryReservePage(Integer startRow,Integer pageSize)  throws SQLException;
	/**
	 * 获取预定总页数接口
	 * @param startRow
	 * @param pageSize
	 * @return
	 * @throws SQLException
	 */
	Long queryCountReserve()  throws SQLException;
	/**
	 * 获取所有预定信息
	 * @return
	 * @throws SQLException
	 */
	List<Reserve> getAllReserve() throws SQLException;
	/**
	 * 添加一条预订信息方法接口
	 * @param reserve
	 * @return
	 * @throws SQLException
	 */
	int addReserve(Reserve reserve) throws SQLException;
	/**
	 * 通过房子ID获得一组此房子的预定信息
	 * @param house_id 房子ID
	 * @return
	 * @throws SQLException
	 */
	List<Reserve> getReserveByHouseID(Integer house_id) throws SQLException;
	/**
	 * 通过预订表ID获取单个预定信息
	 * @param reserve_id
	 * @return
	 * @throws SQLException
	 */
	Reserve getReserveInfoByID(Integer reserve_id) throws SQLException;
	/**
	 * 获得预订表ID
	 * @param reserve
	 * @return
	 * @throws SQLException
	 */
	Integer getReserveID(Reserve reserve) throws SQLException;
}
