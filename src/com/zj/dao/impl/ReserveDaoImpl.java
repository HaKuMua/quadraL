package com.zj.dao.impl;

import java.sql.SQLException;
import java.util.List;

import com.zj.entity.CheckInPerson;
import com.zj.entity.Reserve;

/**
 * 
 * @author lijia
 *预定表数据库操作接口
 */
public interface ReserveDaoImpl {
	//获取所有预定信息
	List<Reserve> getAllReserve() throws SQLException;
}
