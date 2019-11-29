package com.zj.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import cn.com.uitl.GetConn;

import com.zj.dao.impl.HouseCommentDaoImpl;
import com.zj.entity.HouseComment;

/**
 * 
 * @author lijia
 *房子评论数据库操作
 */
public class HouseCommentDao implements HouseCommentDaoImpl{
	private QueryRunner qr = new QueryRunner();
	private Connection conn = GetConn.getConn();
	/**
	 * 同过房子ID获取此房子的所有评论DAO层方法
	 * @throws SQLException 
	 */
	public List<HouseComment> getHouseCommentByHouseID(Integer HouseID) throws SQLException {
		String sql = "select * from house_comment while house_id=?";
		return qr.query(conn, sql, new BeanListHandler<HouseComment>(HouseComment.class), HouseID);
	}
	
}
