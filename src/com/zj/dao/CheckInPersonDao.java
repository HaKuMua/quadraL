package com.zj.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.zj.dao.impl.CheckInPersonDaoImpl;
import com.zj.entity.CheckInPerson;

import cn.com.uitl.DBHelper;
import cn.com.uitl.GetConn;

/**
 * 用户数据库操作实现类
 * 
 * @author LanceEdward
 * 
 */
public class CheckInPersonDao implements CheckInPersonDaoImpl {

	/*
	 * 获取jdbc连接
	 */
	private QueryRunner qr = new QueryRunner();
	private Connection conn = null;

	/*
	 * 获取所有入住人员信息方法
	 */
	public List<CheckInPerson> getAllCheckInPersonInfo() throws SQLException {
		conn = GetConn.getConn();
		String sql = "select * from check_in_person";
		List<CheckInPerson> data = qr.query(conn, sql,
				new BeanListHandler<CheckInPerson>(CheckInPerson.class));
		GetConn.colseConn(conn);
		return data;

	}

	/*
	 * 通过入住人员身份证号获取单个用户信息方法接口
	 */
	public CheckInPerson getCheckInPersonByIdCard(String check_in_person_ID_card)
			throws SQLException {
		conn = GetConn.getConn();
		String sql = "select * from check_in_person where check_in_person_ID_card=?";
		CheckInPerson data = qr.query(conn, sql,
				new BeanHandler<CheckInPerson>(CheckInPerson.class),
				check_in_person_ID_card);
		GetConn.colseConn(conn);
		return data;
	}

}
