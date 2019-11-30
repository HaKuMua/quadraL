package com.zj.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import cn.com.uitl.GetConn;

import com.zj.dao.impl.ReserveDaoImpl;
import com.zj.entity.House;
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
		String sql = "insert into reserve(reserve_date,reserve_day_number,check_out_date,user_id,house_id)";
		return qr.update(conn, sql, reserve.getReserve_date(),reserve.getReserve_day_number(),reserve.getCheck_out_date()
				,reserve.getUser_id(),reserve.getHouse_id());
	}
}
