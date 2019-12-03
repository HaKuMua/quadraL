package com.zj.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import cn.com.util.GetConn;

import com.zj.dao.impl.ArticleImgDaoImpl;
import com.zj.entity.ArticleImg;

/**
 * 
 * @author liyifeng
 *文章图片数据库操作接口
 */
public class ArticleImgDao implements ArticleImgDaoImpl{
	private QueryRunner qr = new QueryRunner();
	private Connection conn = null;
	
	/**
	 * 给文章添加图片
	 * @throws SQLException 
	 */
	public int addArticleImg(ArticleImg articleImgInfo) throws SQLException {
		conn = GetConn.getConn();
		String sql = "insert into article_image(article_id,image_url) values(?,?)";
		int data = qr.update(conn, sql,articleImgInfo.getArticle_id(),articleImgInfo.getImage_url());
		GetConn.closeConn(conn);
		return data;
	}

	/**
	 * 通过id删除文章图片
	 * @throws SQLException 
	 */
	public int deleteArticleImg(Integer image_id) throws SQLException {
		conn = GetConn.getConn();
		String sql = "delete from article_image where image_id = ?";
		int data = qr.update(conn, sql,image_id );
		GetConn.closeConn(conn);
		return data;
	}

	/**
	 * 通过id查询文章图片
	 * @throws SQLException 
	 */
	public ArticleImg queryArticleImgById(Integer image_id) throws SQLException {
		conn = GetConn.getConn();
		String sql = "select * from article_image where image_id = ?";
		ArticleImg data =  qr.query(conn, sql, new BeanHandler<ArticleImg>(ArticleImg.class), image_id);
		GetConn.closeConn(conn);
		return data;
	}

	/**
	 * 通过文章id查询一篇文章的图片
	 * @throws SQLException 
	 */
	public List<ArticleImg> queryArticleImgByArticleId(Integer article_id) throws SQLException {
		conn = GetConn.getConn();
		String sql = "select * from article_image where article_id = ?";
		List<ArticleImg> data =  qr.query(conn, sql, new BeanListHandler<ArticleImg>(ArticleImg.class), article_id);
		GetConn.closeConn(conn);
		return data;
	}

	/**
	 * 查询所有文章图片
	 * @throws SQLException 
	 */
	public List<ArticleImg> queryArticleImg() throws SQLException {
		conn = GetConn.getConn();
		String sql = "select * from article_image";
		List<ArticleImg> data =  qr.query(conn, sql, new BeanListHandler<ArticleImg>(ArticleImg.class));
		GetConn.closeConn(conn);
		return data;
	}
	/**
	 * 通过文章id删除一篇文章所有图片
	 * @throws SQLException 
	 */
	public Integer deleteImgByArticleId(Integer article_id) throws SQLException {
		conn = GetConn.getConn();
		String sql = "delete from article_image where article_id = ?";
		Integer data = qr.update(conn, sql, article_id);
		GetConn.closeConn(conn);
		return data;
	}
	
}
