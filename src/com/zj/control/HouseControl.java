package com.zj.control;

import java.sql.SQLException;
import java.util.List;


import com.zj.dao.impl.HouseDaoImpl;
import com.zj.dao.impl.HouseImgDaoImpl;
import com.zj.dao.impl.HouseParticularsDaoImpl;
import com.zj.dao.realize.HouseDao;
import com.zj.dao.realize.HouseImgDao;
import com.zj.dao.realize.HouseParticularsDao;
import com.zj.entity.House;
import com.zj.entity.HouseImg;
import com.zj.entity.HouseParticulars;

/**
 * 
 * @author lijia
 *房子数据操作类
 */
public class HouseControl {
	private HouseDaoImpl houseDaoImpl = new HouseDao();
	private HouseImgDaoImpl houseImgDaoImpl = new HouseImgDao();
	private HouseParticularsDaoImpl houseParticularsDaoImpl = new HouseParticularsDao();
	
	public List<House> getAllHouseInfo() throws SQLException {
		return houseDaoImpl.getAllHouseInfo();
	}
	public House getHouseInfoByID(Integer HouseID) throws SQLException {
		return houseDaoImpl.getHouseInfoByID(HouseID);
	}
	
	public List<HouseImg> getAllHouseImgInfo() throws SQLException {
		return houseImgDaoImpl.getAllHouseImgInfo();
	}
	public HouseImg getHouseImgInfoByID(Integer HouseImgID) throws SQLException {
		return houseImgDaoImpl.getHouseImgInfoByID(HouseImgID);
	}
	
	public List<HouseParticulars> getAllHouseParticularsInfo()
			throws SQLException {
		return houseParticularsDaoImpl.getAllHouseParticularsInfo();
	}
	public HouseParticulars getHouseParticularsInfoByID(
			Integer HouseParticularsID) throws SQLException {
		return houseParticularsDaoImpl.getHouseParticularsInfoByID(HouseParticularsID);
	}

}
