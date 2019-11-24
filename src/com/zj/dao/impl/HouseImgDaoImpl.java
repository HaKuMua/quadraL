package com.zj.dao.impl;

import java.sql.SQLException;
import java.util.List;

import com.zj.entity.HouseImg;

/**
 * 
 * @author lijia
 *房子图片数据库操作接口
 */
public interface HouseImgDaoImpl {
	public List<HouseImg> getAllHouseImgInfo() throws SQLException;
	public HouseImg getHouseImgInfoByID(Integer HouseImgID) throws SQLException;
	public List<HouseImg> getHouseImgByHouseID(Integer HouseID) throws SQLException;
}
