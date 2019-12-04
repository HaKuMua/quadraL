package com.zj.dao.impl;

import java.sql.SQLException;
import java.util.List;

import com.zj.entity.HouseParticulars;

/**
 * 
 * @author lijia
 *房子详情数据库操作接口
 */
public interface HouseParticularsDaoImpl {
	/**
	 * 获取所有房子详情方法接口
	 */
	List<HouseParticulars> getAllHouseParticularsInfo() throws SQLException;
	/**
	 * 通过房子详情ID获取单个房子详情方法接口
	 */
	HouseParticulars getHouseParticularsInfoByID(Integer HouseParticularsID) throws SQLException;
	/**
	 * 添加一个房子详情信息方法接口
	 * @param houseParticulars
	 * @return
	 * @throws SQLException
	 */
	Integer addHouseParticularsInfo(HouseParticulars houseParticulars) throws SQLException;
	Integer getHouseParticularsIdByInfo(HouseParticulars houseParticulars)throws SQLException;
}
