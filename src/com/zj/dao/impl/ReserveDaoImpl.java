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
}
