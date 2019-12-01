package com.zj.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.zj.dao.impl.HouseParticularsDaoImpl;
import com.zj.entity.HouseParticulars;

import cn.com.util.GetConn;

/**
 * 
 * @author lijia
 *房子详情数据库操作实现类
 */
public class HouseParticularsDao implements HouseParticularsDaoImpl{
	/**
	 * 获取jdbc连接
	 */
	private QueryRunner qr = new QueryRunner();
	private Connection conn = null;
	/**
	 * 获取所有房子详情方法
	 */
	public List<HouseParticulars> getAllHouseParticularsInfo()
			throws SQLException {
		conn = GetConn.getConn();
		String sql = "select * from house_particulars";
		List<HouseParticulars> data = qr.query(conn, sql, new BeanListHandler<HouseParticulars>(HouseParticulars.class));
		GetConn.closeConn(conn);
		return data;
	}
	/**
	 * 通过房子详情ID获取单个房子详情方法
	 */
	public HouseParticulars getHouseParticularsInfoByID(
			Integer HouseParticularsID) throws SQLException {
		conn = GetConn.getConn();
		String sql = "select * from house_particulars where house_particulars_id=?";
		HouseParticulars data =  qr.query(conn, sql, new BeanHandler<HouseParticulars>(HouseParticulars.class),HouseParticularsID);
		GetConn.closeConn(conn);
		return data;
	}
	/**
	 * 添加一个房子详情信息方法
	 */
	public int addHouseParticularsInfo(HouseParticulars houseParticulars)
			throws SQLException {
		conn = GetConn.getConn();
		String sql = "insert into house_particulars(room_number,address_describe,toilet_number" +
				",house_describe) value(?,?,?,?)";
		int data = qr.update(conn, sql, houseParticulars.getRoom_number(),houseParticulars.getAddress_describe()
				,houseParticulars.getToilet_number(),houseParticulars.getHouse_describe());
		GetConn.closeConn(conn);
		return data;
	}

}
