package com.zj.dao.impl;

import java.sql.SQLException;
import java.util.List;

import com.zj.entity.House;

/**
 * 
 * @author lijia
 *房子数据库操作接口
 */
public interface HouseDaoImpl {
	public List<House> getAllHouseInfo() throws SQLException;
	public House getHouseInfoByID(Integer HouseID) throws SQLException;
}
