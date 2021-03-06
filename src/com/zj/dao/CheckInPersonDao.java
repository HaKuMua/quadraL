package com.zj.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.zj.dao.impl.CheckInPersonDaoImpl;
import com.zj.entity.CheckInPerson;
import com.zj.entity.GrogshopOrder;

import cn.com.util.GetConn;

/**
 * 用户数据库操作实现类
 * 
 * @author LanceEdward
 * 
 */
public class CheckInPersonDao implements CheckInPersonDaoImpl {

	/**
	 * 获取jdbc连接
	 */
	private QueryRunner qr = new QueryRunner();
	private Connection conn = null;

	/**
	 * 获取所有入住人员信息方法
	 */
	public List<CheckInPerson> getAllCheckInPersonInfo() throws SQLException {
		conn = GetConn.getConn();
		String sql = "select * from check_in_person";
		List<CheckInPerson> data = qr.query(conn, sql,
				new BeanListHandler<CheckInPerson>(CheckInPerson.class));
		GetConn.closeConn(conn);
		return data;

	}

	/**
	 * 通过入住人员身份证号获取单个用户信息方法
	 */
	public CheckInPerson getCheckInPersonByIdCard(String check_in_person_ID_card)
			throws SQLException {
		conn = GetConn.getConn();
		String sql = "select * from check_in_person where check_in_person_ID_card=?";
		CheckInPerson data = qr.query(conn, sql,
				new BeanHandler<CheckInPerson>(CheckInPerson.class),
				check_in_person_ID_card);
		GetConn.closeConn(conn);
		return data;
	}
	/**
	 * 添加一个入住人信息
	 * @param checkInPerson
	 * @return
	 * @throws SQLException
	 */
	public int addCheckInPerson(CheckInPerson checkInPerson)
			throws SQLException {
		conn = GetConn.getConn();
		String sql = "insert into check_in_person(grogshop_order_id,check_in_person_name,check_in_person_ID_card)" +
				" value(?,?,?)";
		int data =  qr.update(conn, sql, checkInPerson.getGrogshop_order_id(),checkInPerson.getCheck_in_person_name(),checkInPerson.getCheck_in_person_ID_card());
		GetConn.closeConn(conn);
		return data;
	}
	/*
	 * 获取入住人员分页
	 */
	public List<CheckInPerson> queryCheckInPersonPage(Integer startRow,
			Integer pageSize) throws SQLException {
		conn = GetConn.getConn();
		String sql = "select * from check_in_person limit ?,?";
		List<CheckInPerson> data = qr.query(conn, sql, new BeanListHandler<CheckInPerson>(CheckInPerson.class),startRow,pageSize);
		GetConn.closeConn(conn);
		return data;
	}

	/*
	 * 入住人员总页数
	 */
	public Long queryCountCheckInPerson() throws SQLException {
		conn = GetConn.getConn();
		String sql = "select count(*) from check_in_person";
		Long data = qr.query(conn, sql, new ScalarHandler<Long>());
		GetConn.closeConn(conn);
		return data;
	}

}
