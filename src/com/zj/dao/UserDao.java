package com.zj.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import cn.com.uitl.GetConn;

import com.zj.dao.impl.UserDaoImpl;
import com.zj.entity.User;
/**
 * 
 * @author lijia
 *用户数据库操作实现类
 */
public class UserDao implements UserDaoImpl{
	/*
	 * 获取jdbc连接
	 */
	private QueryRunner qr = new QueryRunner();
	private Connection conn = GetConn.getConn();
	/*
	 * 获取所有用户信息方法
	 */
	public List<User> getAllUserInfo() throws SQLException {
		String sql = "select * from user";
		return qr.query(conn, sql, new BeanListHandler<User>(User.class));
	}
	/*
	 * 通过用户手机号获取单个用户信息方法接口
	 */
	public User getUserInfoByPhone(String phone) throws SQLException {
		String sql = "select * from user where user_phone=?";
		return qr.query(conn, sql, new BeanHandler<User>(User.class),phone);
	}
}
