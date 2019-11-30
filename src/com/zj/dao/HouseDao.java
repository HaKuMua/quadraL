package com.zj.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
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
	/**
	 * 获取jdbc连接
	 */
	private QueryRunner qr = new QueryRunner();
	private Connection conn = null;
	/**
	 * 获取所有房子信息方法
	 */
	public List<House> getAllHouseInfo() throws SQLException {
		conn = GetConn.getConn();
		String sql = "select * from house";
		List<House> data = qr.query(conn, sql, new BeanListHandler<House>(House.class));
		GetConn.colseConn(conn);
		return data;
	}
	/**
	 * 通过房子ID获取单个房子信息方法
	 */
	public House getHouseInfoByID(Integer HouseID) throws SQLException {
		conn = GetConn.getConn();
		String sql = "select * from house where house_id=?";
		House data = qr.query(conn, sql, new BeanHandler<House>(House.class),HouseID);
		GetConn.colseConn(conn);
		return data;
	}
	/**
	 * 添加一个房子DAO层方法
	 */
	public int addHouseInfo(House house) throws SQLException {
		conn = GetConn.getConn();
		String sql = "insert into house(User_id,house_name,house_intake,lease_type,may_check_in_date" +
				",may_check_out_date,house_type,house_particulars_id,house_state,travel_information,house_price" +
				",house_address) value(?,?,?,?,?,?,?,?,?,?,?,?)";
		int data = qr.update(conn, sql, house.getUser_id(),house.getHouse_name(),house.getHouse_intake()
				,house.getLease_type(),house.getMay_check_in_date(),house.getMay_check_out_date()
				,house.getHouse_type(),house.getHouse_particulars_id(),house.getHouse_state(),house.getTravel_information()
				,house.getHouse_price(),house.getHouse_address());
		GetConn.colseConn(conn);
		return data;
	}
	/**
	 * 按时间和地址联合查询房间
	 * @param reserve_date 预定时间
	 * @param check_out_date 退房时间
	 * @param house_address 房子地址
	 * @return
	 * @throws SQLException
	 */
	public List<House> getHouseByDateAndAdd(Date reserve_date ,Date check_out_date,String house_address) throws SQLException {
		conn = GetConn.getConn();
		String sql = "select * from reserve,house where (reserve_date > '?' or check_out_date < '?') and "+ 
"(house.house_address LIKE '%?%') and reserve.house_id=house.house_id";
		List<House> date = qr.query(conn, sql, new BeanListHandler<House>(House.class), check_out_date,reserve_date,house_address);
		conn.close();
		return date;
	}
	/**
	 * 按是否有空闲时间查询房子
	 * @param reserve_date
	 * @param check_out_date
	 * @return
	 * @throws SQLException
	 */
	public List<House> getHouseByDate(Date reserve_date ,Date check_out_date) throws SQLException {
		conn = GetConn.getConn();
		String sql = "select * from reserve,house where (reserve_date > '?' or check_out_date < '?') and "+ 
"reserve.house_id=house.house_id";
		List<House> date = qr.query(conn, sql, new BeanListHandler<House>(House.class), check_out_date,reserve_date);
		conn.close();
		return date;
	}
	/**
	 * 按房子地址模糊查询房子信息
	 * @param house_address
	 * @return
	 * @throws SQLException
	 */
	public List<House> getHouseByAdd(String house_address) throws SQLException {
		conn = GetConn.getConn();
		String sql = "select * from house where house_address LIKE '%?%'";
		List<House> date = qr.query(conn, sql, new BeanListHandler<House>(House.class),house_address);
		conn.close();
		return date;
	}
}
