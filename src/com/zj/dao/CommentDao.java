package com.zj.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;

import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import cn.com.uitl.GetConn;

import com.zj.dao.impl.CommentDaoImpl;
import com.zj.entity.Comment;

/**
 * 
 * @author liyifeng
 *评论数据库操作
 */
public class CommentDao implements CommentDaoImpl{
	private QueryRunner qr = new QueryRunner();
	private Connection conn = GetConn.getConn();
	/**
	 * 给文章添加评论
	 * @throws SQLException 
	 */
	public int addComment(Integer article_id, Integer user_id,
			String comment_content, String replier_id) throws SQLException {
		String sql = "insert into comment(article_id, user_id,comment_content,replier_id) values(?,?,?,?)";
		return qr.update(conn, sql, article_id, user_id,comment_content,replier_id);
	}

	/**
	 * 通过id删除文章评论
	 * @throws SQLException 
	 */
	public int deleteComment(Integer comment_id) throws SQLException {
		String sql = "delete from comment where comment_id = ?";
		return qr.update(conn, sql, comment_id);
	}

	/**
	 * 通过id查询评论
	 * @throws SQLException 
	 */
	public Comment queryCommentById(Integer comment_id) throws SQLException {
		String sql = "select * from comment where comment_id = ?";
		return qr.query(conn, sql, new BeanHandler<Comment>(Comment.class), comment_id);
	}
	
	/**
	 * 分页查询一篇文章评论
	 * @throws SQLException 
	 */
	public List<Comment> queryPageComment(Integer article_id, int startRow,
			int pageSize) throws SQLException {
		String sql = "(select * from comment where article_id = ?) limit ?,?";
		return qr.query(conn, sql, new BeanListHandler<Comment>(Comment.class),article_id, startRow,pageSize);
	}

	/**
	 * 不分页查询一篇文章所有评论
	 * @throws SQLException 
	 */
	public List<Comment> queryAllComment(Integer article_id)
			throws SQLException {
		String sql = "select * from comment";
		return qr.query(conn, sql, new BeanListHandler<Comment>(Comment.class));
	}
	/**
	 * 通过id查询一篇文章评论数量
	 */
	public Long queryCommCount(Integer article_id) throws SQLException {
		String sql = "select count(*) from comment where article_id = ?";
		return qr.query(conn, sql, new ScalarHandler<Long>(), article_id);
	}

	/**
	 * 查看一条评论的回复数量
	 * @throws SQLException 
	 */
	public Long queryReplierCount(Integer comment_id) throws SQLException {
		String sql = "select count(replier_id) from comment where comment_id = ?";
		return qr.query(conn, sql, new ScalarHandler<Long>(), comment_id);
	}

	/**
	 * 查询一条评论的赞数量
	 * @throws SQLException 
	 */
	public Long queryPraiseCount(Integer comment_id) throws SQLException {
		String sql = "select count(comment_praise) from comment where comment_id = ?";
		return qr.query(conn, sql, new ScalarHandler<Long>(), comment_id);
	}
	/**
	 * 获取所有评论方法
	 */
	public List<Comment> getAllComment() throws SQLException {
		String sql = "select * from comment";
		return qr.query(conn, sql, new BeanListHandler<Comment>(Comment.class));
	}
}
