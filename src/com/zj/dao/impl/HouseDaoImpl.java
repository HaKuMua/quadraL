package com.zj.dao.impl;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import com.zj.entity.House;

/**
 * 
 * @author lijia
 *房子数据库操作接口
 */
public interface HouseDaoImpl {
	/**
	 * 获取所有房子信息方法接口
	 */
	List<House> getAllHouseInfo() throws SQLException;
	/**
	 * 通过房子ID获取单个房子信息方法接口
	 */
	House getHouseInfoByID(Integer HouseID) throws SQLException;
	/**
	 * 添加一个房子信息DAO层接口
	 * @param house
	 * @return
	 * @throws SQLException
	 */
	int addHouseInfo(House house) throws SQLException;
	/**
	 * 按房子地址模糊查询房子信息
	 * @param house_address
	 * @return
	 * @throws SQLException
	 */
	List<House> getHouseByAdd(String house_address) throws SQLException;
	/**
	 * 按是否有空闲时间查询房子
	 * @param reserve_date
	 * @param check_out_date
	 * @return
	 * @throws SQLException
	 */
	List<House> getHouseByDate(Date reserve_date ,Date check_out_date) throws SQLException;
	/**
	 * 按时间和地址联合查询房间
	 * @param reserve_date 预定时间
	 * @param check_out_date 退房时间
	 * @param house_address 房子地址
	 * @return
	 * @throws SQLException
	 */
	List<House> getHouseByDateAndAdd(Date reserve_date ,Date check_out_date,String house_address) throws SQLException;
	/**
	 * 通过用户ID获取此用户旗下所有房子信息
	 * @param user_id
	 * @return
	 * @throws SQLException
	 */
	List<House> getHouseByID(Integer user_id) throws SQLException;
}
