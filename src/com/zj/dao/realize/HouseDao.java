package com.zj.dao.realize;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import cn.com.uitl.GetConn;

import com.zj.dao.impl.HouseDaoImpl;
import com.zj.entity.House;
/**
 * 
 * @author lijia
 *房子数据库操作实现类
 */
public class HouseDao implements HouseDaoImpl{
	/*
	 * 获取jdbc连接
	 */
	private QueryRunner qr = new QueryRunner();
	private Connection conn = GetConn.getConn();
	/*
	 * 获取所有房子信息方法
	 */
	public List<House> getAllHouseInfo() throws SQLException {
		String sql = "select * from house";
		return qr.query(conn, sql, new BeanListHandler<House>(House.class));
	}
	/*
	 * 通过房子ID获取单个房子信息方法接口
	 */
	public House getHouseInfoByID(Integer HouseID) throws SQLException {
		String sql = "select * from house where house_id=?";
		return qr.query(conn, sql, new BeanHandler<House>(House.class),HouseID);
	}
//	/*
//	 * 添加一个房子方法
//	 * @see com.zj.dao.impl.HouseDaoImpl#addHouseInfoByID()
//	 */
//	public Boolean addHouseInfoByID() throws SQLException {
//		String sql = "insert into house(landlord_id,house_name,house_intake,lease_type,may_check_in_date" +
//				",may_check_out_date,house_type,house_particulars_id,house_state,travel_information,house_price" +
//				",house_address) value(?,?,?,?,?,)";
//		return qr.u;
//	}
}
