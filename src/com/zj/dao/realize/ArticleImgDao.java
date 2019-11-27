package com.zj.dao.realize;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import cn.com.uitl.GetConn;

import com.zj.dao.impl.ArticleImgDaoImpl;
import com.zj.entity.ArticleImg;
/**
 * 
 * @author liyifeng
 *文章图片数据库操作接口
 */
public class ArticleImgDao implements ArticleImgDaoImpl{
	private QueryRunner qr = new QueryRunner();
	private Connection conn = GetConn.getConn();
	/**
	 * 给文章添加图片
	 * @throws SQLException 
	 */
	public int addArticleImg(Integer article_id, String image_url) throws SQLException {
		String sql = "insert into article_image(article_id,image_url) values(?,?)";
		return qr.update(conn, sql,article_id,image_url );
	}

	/**
	 * 通过id删除文章图片
	 * @throws SQLException 
	 */
	public int deleteArticleImg(Integer image_id) throws SQLException {
		String sql = "delete from article_image where image_id = ?";
		return qr.update(conn, sql,image_id );
	}

	/**
	 * 通过id查询文章图片
	 * @throws SQLException 
	 */
	public ArticleImg queryArticleImgById(Integer image_id) throws SQLException {
		String sql = "select * from article_image where image_id = ?";
		return qr.query(conn, sql, new BeanHandler<ArticleImg>(ArticleImg.class), image_id);
	}

	/**
	 * 通过文章id查询一篇文章的图片
	 * @throws SQLException 
	 */
	public List<ArticleImg> queryArticleImgByArticleId(Integer article_id) throws SQLException {
		String sql = "select * from article_image where article_id = ?";
		return qr.query(conn, sql, new BeanListHandler<ArticleImg>(ArticleImg.class), article_id);
	}

	/**
	 * 查询所有文章图片
	 * @throws SQLException 
	 */
	public List<ArticleImg> queryArticleImg() throws SQLException {
		String sql = "select * from article_image";
		return qr.query(conn, sql, new BeanListHandler<ArticleImg>(ArticleImg.class));
	}
}
