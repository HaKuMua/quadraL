package com.zj.dao.realize;

import java.sql.Connection;
import java.sql.SQLException;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import cn.com.uitl.GetConn;

import com.zj.dao.impl.ArticleDaoImpl;
import com.zj.entity.Article;
import com.zj.entity.Comment;



public class ArticleDao implements ArticleDaoImpl{
	private QueryRunner qr = new QueryRunner();
	private Connection conn = GetConn.getConn();
	/**
	 * 添加文章
	 * @throws SQLException 
	 */
	public int addArticle(Integer user_id, String article_name,
			String article_content) throws SQLException {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		String sql = "insert into article(user_id,article_name,article_content, article_date) values(?,?,?,?)";
		return qr.update(conn, sql, user_id,article_name,article_content,format.format(new Date()));
	}

	/**
	 * 通过id删除文章
	 * @throws SQLException 
	 */
	public int deleteArticleById(Integer article_id) throws SQLException {
		String sql = "delete from article where article_id = ?";
		return qr.update(conn, sql, article_id);
	}

	/**
	 * 修改文章
	 * @throws SQLException 
	 */
	public int updateArticle(Integer article_id, String article_name,
			String article_content) throws SQLException {
		String sql = "update article set article_name = ? and article_content = ? where article_id = ?";
		return qr.update(conn, sql, article_name,article_content,article_id);
	}

	/**
	 * 通过id查询文章
	 * @throws SQLException 
	 */
	public Article queryArticleById(Integer article_id) throws SQLException {
		String sql = "select * from article where article_id = ?";
		return qr.query(conn, sql, new BeanHandler<Article>(Article.class), article_id);
	}

	/**
	 * 分页查询所有文章
	 * @throws SQLException 
	 */
	public List<Article> queryPageArticle(int startRow,int pageSize) throws SQLException {
		String sql = "select * from article limit ?,?";
		return qr.query(conn, sql, new BeanListHandler<Article>(Article.class), startRow,pageSize); 
	}

	/**
	 * 查询所有文章数量
	 * @throws SQLException 
	 */
	public Long queryCountArticle() throws SQLException {
		String sql = "select count(*) from article";
		return qr.query(conn, sql, new ScalarHandler<Long>());
	}

	/**
	 * 更新赞数量
	 * @throws SQLException 
	 */
	public int updateArticle_praiset(Integer article_praise,Integer article_id) throws SQLException {
		String sql = "update article set article_praise = ? where article_id = ?";
		return qr.update(conn, sql, article_praise,article_id);
	}

	/**
	 * 更新收藏数量
	 * @throws SQLException 
	 */
	public int updateArticle_collect(Integer article_id,Integer article_collect) throws SQLException {
		String sql = "update article set article_collect = ? where article_id = ?";
		return qr.update(conn, sql,article_collect,article_id );
	}
}
