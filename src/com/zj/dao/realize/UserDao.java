package com.zj.dao.realize;

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
	private QueryRunner qr = new QueryRunner();
	private Connection conn = GetConn.getConn();
	public List<User> getAllUserInfo() throws SQLException {
		String sql = "select * from user";
		return qr.query(conn, sql, new BeanListHandler<User>(User.class));
	}

	public User getUserInfoByPhone(String phone) throws SQLException {
		String sql = "select * from user where user_phone=?";
		return qr.query(conn, sql, new BeanHandler<User>(User.class),phone);
	}
}
