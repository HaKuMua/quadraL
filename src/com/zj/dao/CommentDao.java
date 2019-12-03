package com.zj.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import cn.com.util.GetConn;

import com.zj.dao.impl.CommentDaoImpl;
import com.zj.entity.Comment;

/**
 * 
 * @author liyifeng
 *评论数据库操作
 */
public class CommentDao implements CommentDaoImpl{
	private QueryRunner qr = new QueryRunner();
	private Connection conn = null;
	/**
	 * 给文章添加评论
	 * @throws SQLException 
	 */
	public int addComment(Integer article_id, Integer user_id,
			String comment_content, String replier_id) throws SQLException {
		conn = GetConn.getConn();
		String sql = "insert into comment(article_id, user_id,comment_content,replier_id) values(?,?,?,?)";
		int data = qr.update(conn, sql, article_id, user_id,comment_content,replier_id);
		GetConn.closeConn(conn);
		return data;
	}

	/**
	 * 通过id删除文章评论
	 * @throws SQLException 
	 */
	public int deleteComment(Integer comment_id) throws SQLException {
		conn = GetConn.getConn();
		String sql = "delete from comment where comment_id = ?";
		int data = qr.update(conn, sql, comment_id);
		GetConn.closeConn(conn);
		return data;
	}

	/**
	 * 通过id查询评论
	 * @throws SQLException 
	 */
	public Comment queryCommentById(Integer comment_id) throws SQLException {
		conn = GetConn.getConn();
		String sql = "select * from comment where comment_id = ?";
		Comment data = qr.query(conn, sql, new BeanHandler<Comment>(Comment.class), comment_id);
		GetConn.closeConn(conn);
		return data;
	}
	
	/**
	 * 分页查询一篇文章评论
	 * @throws SQLException 
	 */
	public List<Comment> queryPageComment(Integer article_id, Integer startRow,
			Integer pageSize) throws SQLException {
		conn = GetConn.getConn();
		String sql = "(select * from comment where article_id = ?) limit ?,?";
		List<Comment> data =  qr.query(conn, sql, new BeanListHandler<Comment>(Comment.class),article_id, startRow,pageSize);
		GetConn.closeConn(conn);
		return data;
	}

	/**
	 * 不分页查询一篇文章所有评论
	 * @throws SQLException 
	 */
	public List<Comment> queryAllComment(Integer article_id)
			throws SQLException {
		conn = GetConn.getConn();
		String sql = "select * from comment";
		List<Comment> data = qr.query(conn, sql, new BeanListHandler<Comment>(Comment.class));
		GetConn.closeConn(conn);
		return data;
	}
	/**
	 * 通过id查询一篇文章评论数量
	 */
	public Long queryCommCount(Integer article_id) throws SQLException {
		conn = GetConn.getConn();
		String sql = "select count(*) from comment where article_id = ?";
		Long data = qr.query(conn, sql, new ScalarHandler<Long>(), article_id);
		GetConn.closeConn(conn);
		return data;
	}

	/**
	 * 查看一条评论的回复数量
	 * @throws SQLException 
	 */
	public Long queryReplierCount(Integer comment_id) throws SQLException {
		conn = GetConn.getConn();
		String sql = "select count(replier_id) from comment where comment_id = ?";
		Long data  = qr.query(conn, sql, new ScalarHandler<Long>(), comment_id);
		GetConn.closeConn(conn);
		return data;
	}

	/**
	 * 查询一条评论的赞数量
	 * @throws SQLException 
	 */
	public Long queryPraiseCount(Integer comment_id) throws SQLException {
		conn = GetConn.getConn();
		String sql = "select count(comment_praise) from comment where comment_id = ?";
		Long data = qr.query(conn, sql, new ScalarHandler<Long>(), comment_id);
		GetConn.closeConn(conn);
		return data;
	}
	/**
	 * 获取所有评论方法
	 */
	public List<Comment> getAllComment() throws SQLException {
		conn = GetConn.getConn();
		String sql = "select * from comment";
		List<Comment> data = qr.query(conn, sql, new BeanListHandler<Comment>(Comment.class));
		GetConn.closeConn(conn);
		return data;
	}

	/**
	 * 通过文章id删除一篇文章的所有评论
	 * @throws SQLException 
	 */
	public Integer deleteCommByArticleId(Integer article_id) throws SQLException {
		conn = GetConn.getConn();
		String sql = "delete from comment where article_id = ?";
		Integer data = qr.update(conn, sql, article_id);
		GetConn.closeConn(conn);
		return data;
	}
}
