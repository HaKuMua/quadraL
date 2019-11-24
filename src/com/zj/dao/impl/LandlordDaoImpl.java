package com.zj.dao.impl;

import java.sql.SQLException;
import java.util.List;

import com.zj.entity.Landlord;

/**
 * 
 * @author lijia
 *房东数据库操作接口
 */
public interface LandlordDaoImpl {
	/*
	 * 获取所有房东信息方法接口
	 */
	public List<Landlord> getAllLandlordInfo() throws SQLException;
	/*
	 * 通过房东手机获取单个房东信息方法接口
	 */
	public Landlord getLandlordInfoByPhone(String phone) throws SQLException;
}
