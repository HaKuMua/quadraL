package com.zj.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import cn.com.util.GetConn;

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
	private Connection conn = null;
	/**
	 * 获取所有用户信息方法
	 */
	public List<User> getAllUserInfo() throws SQLException {
		conn = GetConn.getConn();
		String sql = "select * from user";
		List<User> data = qr.query(conn, sql, new BeanListHandler<User>(User.class));
		GetConn.closeConn(conn);
		return data;
	}
	
	/**
	 * 通过用户手机号获取单个用户信息方法
	 */
	public User getUserInfoByPhone(String phone) throws SQLException {
		conn = GetConn.getConn();
		String sql = "select * from user where user_phone=?";
		User data = qr.query(conn, sql, new BeanHandler<User>(User.class),phone);
		GetConn.closeConn(conn);
		return data;
	}
	
	/**
	 * 通过用户手机号和姓名获取单个用户信息方法
	 */
	public User getUserInfoByPhoneAndName(String name, String phone)
			throws SQLException {
		conn = GetConn.getConn();
		String sql = "select * from user where user_phone=? and user_name =?";
		User data = qr.query(conn, sql, new BeanHandler<User>(User.class),phone,name);
		GetConn.closeConn(conn);
		return data;
	}
	
	/**
	 * 通过用户id获取单个用户信息方法
	 * @param user_id
	 * @return
	 * @throws SQLException
	 */
	public User getUserInfoById(Integer user_id) throws SQLException {
		conn = GetConn.getConn();
		String sql = "select * from user where user_id=?";
		User data = qr.query(conn, sql, new BeanHandler<User>(User.class), user_id);
		GetConn.closeConn(conn);
		return data;
	}

	/**
	 * 添加用户
	 */
	public Integer addUser(String user_name, String user_phone) throws SQLException {
		conn = GetConn.getConn();
		String sql = "insert into user(user_name,user_phone) values(?,?,?)";
		int data = qr.update(conn, sql, user_name,user_phone);
		GetConn.closeConn(conn);
		return data;
	}
	
	/**
	 * 修改用户基本信息
	 */
	public Integer updateBasicInfo(Integer user_id,String user_name,String user_email,String user_describe) throws SQLException {
		conn = GetConn.getConn();
		String sql = "update user set user_name = ?, user_email = ?,  user_describe = ? where user_id = ?";
		int data = qr.update(conn, sql, user_name,user_email,user_describe,user_id);
		GetConn.closeConn(conn);
		return data;
	}
	
	/**
	 * 查询手机号是否存在
	 * @throws SQLException 
	 */
	public Integer queryPhoneExit(String user_phone) throws SQLException {
		conn = GetConn.getConn();
		String sql = "select count(*) from user where user_phone = ?";
		Integer data = qr.query(conn, sql, new ScalarHandler<Integer>(), user_phone);
		GetConn.closeConn(conn);
		return data;
	}
	/**
	 * 用户更新密码
	 * @throws SQLException 
	 */
	public Integer updateUserPwd(Integer user_id, String user_pwd) throws SQLException {
		conn = GetConn.getConn();
		String sql = "update user set user_pwd = ? where user_id = ?";
		Integer data = qr.update(conn, sql, user_pwd,user_id);
		GetConn.closeConn(conn);
		return data;
	}
	
	/**
	 * 用户上传头像
	 * @throws SQLException 
	 */
	public Integer addUserHead(Integer user_id, String user_headimg_url) throws SQLException {
		conn = GetConn.getConn();
		String sql = "update user set user_headimg_url = ? where user_id = ?";
		Integer data = qr.update(conn, sql, user_headimg_url,user_id);
		GetConn.closeConn(conn);
		return data;
	}
	
	
	/**
	 * 更新用户余额
	 */
	public Integer updateUserMoney(Double price,Integer user_id) throws SQLException {
		conn = GetConn.getConn();
		String sql = "update user set money=money-? where user_id=?";
		Integer num = qr.update(conn, sql, price,user_id);
		GetConn.closeConn(conn);
		return num;
	}
	
	/**
	 * 根据电话/email和密码查询用户
	 * @throws SQLException 
	 */
	public User queryUserInfo(String user_code ,String user_pwd) throws SQLException {
		conn = GetConn.getConn();
		String sql = "select * from user where((user_phone = ? or user_email = ?) and user_pwd = ?)";
		User data = qr.query(conn, sql, new BeanHandler<User>(User.class), user_code,user_code,user_pwd);
		GetConn.closeConn(conn);
		return data;
	}
	
	/**
	 * 修改用户电话
	 */
	@Override
	public Integer updateUserPhone(Integer user_id, String user_phone)
			throws SQLException {
		conn = GetConn.getConn();
		String sql = "update user set user_phone = ? where user_id = ?";
		Integer data = qr.update(conn, sql,new ScalarHandler<Integer>(), user_phone,user_id);
		GetConn.closeConn(conn);
		return data;
	}
	
	/**
	 * 实名认证
	 */
	@Override
	public Integer setRealName(Integer user_id, String real_name,
			String user_IDcard) throws SQLException {
		// TODO Auto-generated method stub
		conn = GetConn.getConn();
		String sql = "update user set real_name = ?,user_IDcard = ? where user_id = ?";
		Integer data = qr.update(conn, sql,new ScalarHandler<Integer>(),real_name, user_IDcard,user_id);
		GetConn.closeConn(conn);
		return data;
	}
	/**
	 * 用户充值
	 * @return
	 */
	public Integer topUp(Double price, Integer user_id) throws SQLException {
		conn = GetConn.getConn();
		String sql = "update user set money=money+? where user_id=?";
		Integer data = qr.update(conn, sql, price,user_id);
		GetConn.closeConn(conn);
		return data;
	}
	
}
