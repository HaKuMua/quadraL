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
	/*
	 * 获取所有房子信息方法接口
	 */
	public List<House> getAllHouseInfo() throws SQLException;
	/*
	 * 通过房子ID获取单个房子信息方法接口
	 */
	public House getHouseInfoByID(Integer HouseID) throws SQLException;
}
