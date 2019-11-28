package com.zj.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import cn.com.uitl.GetConn;

import com.zj.dao.impl.BuildingCodesDaoImpl;
import com.zj.entity.BuildingCodes;

public class BuildingCodesDao implements BuildingCodesDaoImpl{
	private QueryRunner qr = new QueryRunner();
	private Connection conn = GetConn.getConn();
	/**
	 * 添加房屋守则
	 * @throws SQLException 
	 */
	public int addBuildingCodes(Integer house_id, String building_name) throws SQLException {
		String sql = "insert into building_codes(house_id,building_name) values(?,?)";
		return qr.update(conn, sql, house_id,building_name);
	}

	/**
	 * 删除房屋守则
	 * @throws SQLException 
	 */
	public int deleteBuildingCodes(Integer building_codes_id) throws SQLException {
		String sql = "delete from building_codes where building_codes_id = ?";
		return qr.update(conn, sql, building_codes_id);
	}

	/**
	 * 修改房屋守则
	 * @throws SQLException 
	 */
	public int updateBuildingCodes(Integer building_codes_id, Integer house_id,
			String building_name) throws SQLException {
		String sql = "update building_codes set house_id = ? and building_name = ? where building_codes_id = ?";
		return qr.update(conn, sql,  house_id,building_name,building_codes_id);
	}

	/**
	 * 通过id查询一条守则
	 * @throws SQLException 
	 */
	public BuildingCodes queryBuildingCodesById(Integer building_codes_id) throws SQLException {
		String sql = "select * from building_codes where building_codes_id = ?";
		return qr.query(conn, sql, new BeanHandler<BuildingCodes>(BuildingCodes.class), building_codes_id);
	}

	/**
	 * 查询所有房屋守则
	 * @throws SQLException 
	 */
	public List<BuildingCodes> queryAllBuildingCodes() throws SQLException {
		String sql = "select * from building_codes";
		return qr.query(conn, sql, new BeanListHandler<BuildingCodes>(BuildingCodes.class));
	}

	/**
	 * 查询一个房子的所有房屋守则
	 * @throws SQLException 
	 */
	public List<BuildingCodes> queryOneHouseBuildingCodes(Integer house_id) throws SQLException {
		String sql = "select * from building_codes where house_id = ?";
		return qr.query(conn, sql, new BeanListHandler<BuildingCodes>(BuildingCodes.class), house_id);
	}

}
