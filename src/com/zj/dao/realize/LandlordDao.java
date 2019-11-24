package com.zj.dao.realize;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import cn.com.uitl.GetConn;

import com.zj.dao.impl.LandlordDaoImpl;
import com.zj.entity.Landlord;
/**
 * 
 * @author lijia
 *房东数据库操作实现类
 */
public class LandlordDao implements LandlordDaoImpl{
	/*
	 * 获取jdbc连接
	 */
	private QueryRunner qr = new QueryRunner();
	private Connection conn = GetConn.getConn();
	/*
	 * 获取所有房东信息方法
	 */
	public List<Landlord> getAllLandlordInfo() throws SQLException {
		String sql = "select * from landlord";
		return qr.query(conn, sql, new BeanListHandler<Landlord>(Landlord.class));
	}
	/*
	 * 通过房东手机获取单个房东信息方法
	 */
	public Landlord getLandlordInfoByPhone(String phone) throws SQLException {
		String sql = "select * from landlord where landlord_phone=?";
		return qr.query(conn, sql, new BeanHandler<Landlord>(Landlord.class),phone);
	}
	
	
}
