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
	/*
	 * 获取所有房子详情方法接口
	 */
	public List<HouseParticulars> getAllHouseParticularsInfo() throws SQLException;
	/*
	 * 通过房子详情ID获取单个房子详情方法接口
	 */
	public HouseParticulars getHouseParticularsInfoByID(Integer HouseParticularsID) throws SQLException;
}
