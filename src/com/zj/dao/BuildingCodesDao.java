package com.zj.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import cn.com.util.GetConn;

import com.zj.dao.impl.BuildingCodesDaoImpl;
import com.zj.entity.BuildingCodes;
import com.zj.entity.Facilities;

public class BuildingCodesDao implements BuildingCodesDaoImpl{
	private QueryRunner qr = new QueryRunner();
	private Connection conn = null;
	/**
	 * 添加房屋守则
	 * @throws SQLException 
	 */
	public Integer addBuildingCodes(Integer house_id, String building_name) throws SQLException {
		conn = GetConn.getConn();
		String sql = "insert into building_codes(house_id,building_name) values(?,?)";
		Integer data = qr.update(conn, sql, house_id,building_name);
		GetConn.closeConn(conn);
		return data;
		
	}
	
	/**
	 * 添加房屋设施
	 * @throws SQLException 
	 */
	public Integer addFacilities(Integer house_id, String facilities_name) throws SQLException {
		conn = GetConn.getConn();
		String sql = "insert into facilities(house_id,facilities_name) values(?,?)";
		Integer data = qr.update(conn, sql, house_id,facilities_name);
		GetConn.closeConn(conn);
		return data;
		
	}
	
	/**
	 * 删除房屋守则
	 * @throws SQLException 
	 */
	public int deleteBuildingCodes(Integer building_codes_id) throws SQLException {
		conn = GetConn.getConn();
		String sql = "delete from building_codes where building_codes_id = ?";
		int data =  qr.update(conn, sql, building_codes_id);
		GetConn.closeConn(conn);
		return data;
	}

	/**
	 * 修改房屋守则
	 * @throws SQLException 
	 */
	public int updateBuildingCodes(Integer building_codes_id, Integer house_id,
			String building_name) throws SQLException {
		conn = GetConn.getConn();
		String sql = "update building_codes set house_id = ? and building_name = ? where building_codes_id = ?";
		int data = qr.update(conn, sql,  house_id,building_name,building_codes_id);
		GetConn.closeConn(conn);
		return data;
	}

	/**
	 * 通过id查询一条守则
	 * @throws SQLException 
	 */
	public BuildingCodes queryBuildingCodesById(Integer building_codes_id) throws SQLException {
		conn = GetConn.getConn();
		String sql = "select * from building_codes where building_codes_id = ?";
		BuildingCodes data =  qr.query(conn, sql, new BeanHandler<BuildingCodes>(BuildingCodes.class), building_codes_id);
		GetConn.closeConn(conn);
		return data;
	}

	/**
	 * 查询所有房屋守则
	 * @throws SQLException 
	 */
	public List<BuildingCodes> queryAllBuildingCodes() throws SQLException {
		conn = GetConn.getConn();
		String sql = "select * from building_codes";
		List<BuildingCodes> data = qr.query(conn, sql, new BeanListHandler<BuildingCodes>(BuildingCodes.class));
		GetConn.closeConn(conn);
		return data;
	}

	/**
	 * 查询一个房子的所有房屋守则
	 * @throws SQLException 
	 */
	public List<BuildingCodes> queryOneHouseBuildingCodes(Integer house_id) throws SQLException {
		conn = GetConn.getConn();
		String sql = "select * from building_codes where house_id = ?";
		List<BuildingCodes> data = qr.query(conn, sql, new BeanListHandler<BuildingCodes>(BuildingCodes.class), house_id);
		GetConn.closeConn(conn);
		return data;
	}
	
	/**
	 * 查询一个房子的所有房子设施
	 * @throws SQLException 
	 */
	public List<Facilities> queryFacilities(Integer house_id) throws SQLException {
		conn = GetConn.getConn();
		String sql = "select * from facilities where house_id = ?";
		List<Facilities> data = qr.query(conn, sql, new BeanListHandler<Facilities>(Facilities.class), house_id);
		GetConn.closeConn(conn);
		return data;
	}

}
