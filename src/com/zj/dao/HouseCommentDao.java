package com.zj.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder.In;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import cn.com.util.GetConn;

import com.zj.dao.impl.HouseCommentDaoImpl;
import com.zj.entity.HouseComment;

/**
 * 
 * @author lijia
 *房子评论数据库操作
 */
public class HouseCommentDao implements HouseCommentDaoImpl{
	private QueryRunner qr = new QueryRunner();
	private Connection conn = null;
	/**
	 * 获取房子评论所有信息方法
	 */
	public List<HouseComment> getAllHouseComment() throws SQLException {
		conn = GetConn.getConn();
		String sql = "select * from house_comment";
		List<HouseComment> data = qr.query(conn, sql, new BeanListHandler<HouseComment>(HouseComment.class));
		GetConn.closeConn(conn);
		return data;
	}
	/**
	 * 通过房子ID获取此房子的所有评论DAO层方法
	 * @throws SQLException 
	 */
	public List<HouseComment> getHouseCommentByHouseID(Integer HouseID) throws SQLException {
		conn = GetConn.getConn();
		String sql = "select * from house_comment where house_id=?";
		List<HouseComment> data = qr.query(conn, sql, new BeanListHandler<HouseComment>(HouseComment.class), HouseID);
		GetConn.closeConn(conn);
		return data;
	}
	/**
	 * 给房子添加评论
	 * @throws SQLException 
	 */
	public Integer addComment(HouseComment comment) throws SQLException {
		conn = GetConn.getConn();
		String sql = "insert into house_comment(house_id, user_id,houseCom_content,replier_id) values(?,?,?,?)";
		Integer data = qr.update(conn, sql, comment.getHouse_id(), comment.getUser_id(),comment.getHouseCom_content(),comment.getReplier_id());
		GetConn.closeConn(conn);
		return data;
	}
	
	/**
	 * 获得user_id的评论
	 * @throws SQLException 
	 */
	public List<HouseComment> getUserComment(Integer user_id) throws SQLException {
		conn = GetConn.getConn();
		String sql = "select * from house_comment where user_id=?";
		List<HouseComment> data = qr.query(conn, sql, new BeanListHandler<HouseComment>(HouseComment.class), user_id);
		GetConn.closeConn(conn);
		return data;
	}
	
}
