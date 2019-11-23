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
	public List<HouseParticulars> getAllHouseParticularsInfo() throws SQLException;
	public HouseParticulars getHouseParticularsInfoByID(Integer HouseParticularsID) throws SQLException;
}
