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
	public List<Landlord> getAllLandlordInfo() throws SQLException;
	public Landlord getLandlordInfoByPhone(String phone) throws SQLException;
}
