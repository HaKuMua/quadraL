package com.zj.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import cn.com.util.GetConn;

import com.zj.dao.impl.ReserveDaoImpl;
import com.zj.entity.Reserve;

/**
 * 预定信息dao层实现类
 * 
 */
public class ReserveDao implements ReserveDaoImpl{
	/**
	 * 获取jdbc连接
	 */
	private QueryRunner qr = new QueryRunner();
	private Connection conn = null;
	/**
	 * 获取所有预定信息方法
	 */
	public List<Reserve> getAllReserve() throws SQLException {
		conn = GetConn.getConn();
		String sql = "select * from reserve";
		List<Reserve> data = qr.query(conn, sql, new BeanListHandler<Reserve>(Reserve.class));
		GetConn.closeConn(conn);
		return data;
	}
	/**
	 * 添加一条预订信息方法
	 * @param reserve
	 * @return
	 * @throws SQLException
	 */
	public int addReserve(Reserve reserve) throws SQLException {
		conn = GetConn.getConn();
		String sql = "insert into reserve(reserve_date,reserve_day_number,check_out_date,user_id,house_id)";
		int data = qr.update(conn, sql, reserve.getReserve_date(),reserve.getReserve_day_number(),reserve.getCheck_out_date()
				,reserve.getUser_id(),reserve.getHouse_id());
		GetConn.closeConn(conn);
		return data;
	}
	/**
	 * 通过房子ID获得一组此房子的预定信息
	 * @param house_id 房子ID
	 * @return
	 * @throws SQLException
	 */
	public List<Reserve> getReserveByHouseID(Integer house_id)
			throws SQLException {
		conn = GetConn.getConn();
		String sql = "select * from reserve where house_id=?";
		List<Reserve> data = qr.query(conn, sql, new BeanListHandler<Reserve>(Reserve.class), house_id);
		GetConn.closeConn(conn);
		return data;
	}
	/**
	 * 通过预订表ID获取单个预定信息
	 * @param reserve_id
	 * @return
	 * @throws SQLException
	 */
	public Reserve getReserveInfoByID(Integer reserve_id) throws SQLException {
		conn = GetConn.getConn();
		String sql = "select * from reserve where reserve_id=?";
		Reserve data = qr.query(conn, sql, new BeanHandler<Reserve>(Reserve.class), reserve_id);
		GetConn.closeConn(conn);
		return data;
	}
}
