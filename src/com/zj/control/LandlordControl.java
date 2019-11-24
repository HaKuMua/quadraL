package com.zj.control;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

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
}
