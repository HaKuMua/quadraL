package com.zj.service.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;


/**
 * 
 * @author lijia
 *房子服务层接口
 */
public interface HouseServiceImpl {
	/**
	 * 将所有房子信息包装成一个<map>返回方法接口
	 */
	Map<String, Object> getAllHouseInfo(Integer limit,Integer page)throws SQLException;
	/**
	 * 将单个房子信息包装成map返回方法接口
	 */
	Map<String, Object> getHouseInfoByID(Integer house_id);
	/**
	 * 添加一个房子信息方法接口
	 * @param house 一个房子信息
	 * @return
	 * @throws SQLException
	 */
	Integer addHouseInfo(Map<String, Object> houseInfo);
	/**
	 * 添加一组图片方法
	 */
	Integer addHouseImg(String house_name,String houseImgList);
	/**
	 * 通过用户ID获取此用户旗下所有房子信息
	 * @param user_id
	 * @return
	 */
	List<Map<String, Object>> getHouseByID(Integer user_id,Integer status);
	/**
	 * 通过筛选返回指定房子信息
	 * @return
	 */
	List<Map<String, Object>> getHouseByDateOrAddress(String reserve_date ,String check_out_date,String house_address);
	/**
	 * 修改房子状态服务层接口
	 * @param status
	 * @return
	 * @throws SQLException
	 */
	Integer updateHouseStatus(Integer status,Integer house_id);
	
	/**
	 * 用房东id分页得到房东所有房子
	 * @param user_id
	 * @param limit
	 * @param page
	 * @return
	 */
	Map<String, Object> getLandlordHouseById(Integer user_id,Integer limit,Integer page);
	
}
