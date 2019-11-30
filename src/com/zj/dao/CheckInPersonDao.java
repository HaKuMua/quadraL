package com.zj.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.zj.dao.impl.CheckInPersonDaoImpl;
import com.zj.entity.CheckInPerson;

import cn.com.uitl.GetConn;

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
	private Connection conn = GetConn.getConn();

	/**
	 * 获取所有入住人员信息方法
	 */
	public List<CheckInPerson> getAllCheckInPersonInfo() throws SQLException {
		String sql = "select * from check_in_person";
		return qr.query(conn, sql, new BeanListHandler<CheckInPerson>(
				CheckInPerson.class));
	}

	/**
	 * 通过入住人员身份证号获取单个用户信息方法
	 */
	public CheckInPerson getCheckInPersonByIdCard(String check_in_person_ID_card)
			throws SQLException {
		String sql = "select * from check_in_person where check_in_person_ID_card=?";
		return qr.query(conn, sql, new BeanHandler<CheckInPerson>(
				CheckInPerson.class), check_in_person_ID_card);
	}
	/**
	 * 添加一个入住人信息
	 * @param checkInPerson
	 * @return
	 * @throws SQLException
	 */
	public int addCheckInPerson(CheckInPerson checkInPerson)
			throws SQLException {
		String sql = "insert into check_in_person(grogshop_order_id,check_in_person_name,check_in_person_ID_card)" +
				" value(?,?,?)";
		return qr.update(conn, sql, checkInPerson.getGrogshop_order_id(),checkInPerson.getCheck_in_person_name(),checkInPerson.getCheck_in_person_ID_card());
	}

}
