package com.zj.dao.impl;

import java.sql.SQLException;
import java.util.List;

import com.zj.entity.BuildingCodes;

/**
 * 
 * @author lijia
 *房屋守则数据库操作接口
 */
public interface BuildingCodesDaoImpl {
	/**
	 * 添加房屋守则
	 * @throws SQLException 
	 */
	int addBuildingCodes(Integer house_id,String building_name) throws SQLException;
	/**
	 * 删除房屋守则
	 * @throws SQLException 
	 */
	int deleteBuildingCodes(Integer building_codes_id) throws SQLException;
	/**
	 * 修改房屋守则
	 * @throws SQLException 
	 */
	int updateBuildingCodes(Integer building_codes_id,Integer house_id,String building_name) throws SQLException;
	/**
	 * 通过id查询一条守则
	 * @throws SQLException 
	 */
	BuildingCodes queryBuildingCodesById(Integer building_codes_id) throws SQLException;
	/**
	 * 查询所有房屋守则
	 * @throws SQLException 
	 */
	List<BuildingCodes> queryAllBuildingCodes() throws SQLException;
	/**
	 * 查询一个房子的所有房屋守则
	 * @throws SQLException 
	 */
	List<BuildingCodes> queryOneHouseBuildingCodes(Integer house_id) throws SQLException;
	
}
