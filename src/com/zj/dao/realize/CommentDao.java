package com.zj.dao.realize;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import cn.com.uitl.GetConn;

import com.zj.dao.impl.CommentDaoImpl;
import com.zj.entity.Comment;
import com.zj.entity.Landlord;

public class CommentDao implements CommentDaoImpl{

	/*
	 * 获取jdbc连接
	 */
	private QueryRunner qr = new QueryRunner();
	private Connection conn = GetConn.getConn();
	//获取所有的评论
	public List<Comment> getAllCommen() throws SQLException {
		String sql = "select * from comment";
		return qr.query(conn, sql, new BeanListHandler<Comment>(Comment.class));
	}

}
