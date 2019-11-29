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
	/**
	 * 获取jdbc连接
	 */
	private QueryRunner qr = new QueryRunner();
	private Connection conn = GetConn.getConn();
	/**
	 * 获取所有用户信息方法
	 */
	public List<User> getAllUserInfo() throws SQLException {
		String sql = "select * from user";
		return qr.query(conn, sql, new BeanListHandler<User>(User.class));
	}
	/**
	 * 通过用户手机号获取单个用户信息方法
	 */
	public User getUserInfoByPhone(String phone) throws SQLException {
		String sql = "select * from user where user_phone=?";
		return qr.query(conn, sql, new BeanHandler<User>(User.class),phone);
	}
	/**
	 * 通过用户id获取单个用户信息方法
	 * @param user_id
	 * @return
	 * @throws SQLException
	 */
	public User getUserInfoByID(Integer user_id) throws SQLException {
		String sql = "select * from user where user_id=?";
		return qr.query(conn, sql, new BeanHandler<User>(User.class), user_id);
	}
	/**
	 * 添加一个用户信息
	 */
	public int addUserInfo(User user) throws SQLException {
		String sql = "insert into user(user_name,user_headimg_url,user_email,user_phone,user_IDcard,is_landlord" +
				",user_pwd,money,real_name,user_describe) value(?,?,?,?,?,?,?,?,?,?)";
		return qr.update(conn, sql, user.getUser_name(),user.getUser_headimg_url(),user.getUser_email(),user.getUser_phone()
				,user.getUser_IDcard(),user.getIs_landlord(),user.getUser_pwd(),user.getMoney(),user.getReal_name(),user.getUser_describe());
	}
	
}
