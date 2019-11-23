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
	private QueryRunner qr = new QueryRunner();
	private Connection conn = GetConn.getConn();
	public List<House> getAllHouseInfo() throws SQLException {
		String sql = "select * from house";
		return qr.query(conn, sql, new BeanListHandler<House>(House.class));
	}
	public House getHouseInfoByID(Integer HouseID) throws SQLException {
		String sql = "select * from house where house_id=?";
		return qr.query(conn, sql, new BeanHandler<House>(House.class),HouseID);
	}
}
