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
	/**
	 * 获取所有房子图片方法接口
	 */
	List<HouseImg> getAllHouseImgInfo() throws SQLException;
	/**
	 * 通过房子图片ID获取单个房子图片方法接口
	 */
	HouseImg getHouseImgInfoByID(Integer HouseImgID) throws SQLException;
	/**
	 * 通过房子ID获取一组房子图片方法接口
	 */
	List<HouseImg> getHouseImgByHouseID(Integer HouseID) throws SQLException;
	/**
	 * 添加一个房子图片信息方法接口
	 * @param houseImg 传递一个房子图片实体类进来
	 * @return 返回值若大于0则插入成功
	 * @throws SQLException
	 */
	int addHouseImgInfo(HouseImg houseImg) throws SQLException;
}
