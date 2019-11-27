package com.zj.control;

import java.sql.SQLException;
import java.util.List;


import com.zj.dao.impl.LandlordDaoImpl;
import com.zj.dao.realize.LandlordDao;
import com.zj.entity.Landlord;

/**
 * 
 * @author lijia
 *房东数据操作类
 */
public class LandlordControl {
	private LandlordDaoImpl landlordDaoImpl = new LandlordDao();
	
	public List<Landlord> getAllLandlordInfo() throws SQLException {
		return landlordDaoImpl.getAllLandlordInfo();
	}
	public Landlord getLandlordInfoByPhone(String phone) throws SQLException {
		return landlordDaoImpl.getLandlordInfoByPhone(phone);
	}
	public Boolean addLandlordInfo(Landlord landlord) throws SQLException {
		return landlordDaoImpl.addLandlordInfo(landlord);
	}
}
