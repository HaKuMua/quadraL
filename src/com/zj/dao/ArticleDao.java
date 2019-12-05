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

import com.zj.dao.impl.ArticleDaoImpl;
import com.zj.entity.Article;
import com.zj.entity.GrogshopOrder;



public class ArticleDao implements ArticleDaoImpl{
	private QueryRunner qr = new QueryRunner();
	private Connection conn = null;
	
	/**
	 * 添加文章
	 * @throws SQLException 
	 */
	public int addArticle(Article articleInfo) throws SQLException {
		conn = GetConn.getConn();
		String sql = "insert into article(user_id,article_name,article_content,house_id) values(?,?,?,?)";
		int data = qr.update(conn, sql,articleInfo.getUser_id(), articleInfo.getArticle_name(),articleInfo.getArticle_content(),articleInfo.getHouse_id());
		GetConn.closeConn(conn);
		return data;
	}

	/**
	 * 通过id删除文章
	 * @throws SQLException 
	 */
	public int deleteArticleById(Integer article_id) throws SQLException {
		conn = GetConn.getConn();
		String sql = "delete from article where article_id = ?";
		String sql2 = "delete from article_image where article_id = ?";
		String sql3 = "delete from comment where article_id = ?";
		qr.update(conn,sql2,article_id);
		qr.update(conn,sql3,article_id);
		int data = qr.update(conn, sql, article_id);
		GetConn.closeConn(conn);
		return data;
	}

	/**
	 * 修改文章
	 * @throws SQLException 
	 */
	public int updateArticle(Integer article_id, String article_name,
			String article_content) throws SQLException {
		conn = GetConn.getConn();
		String sql = "update article set article_name = ?, article_content = ? where article_id = ?";
		int data = qr.update(conn, sql, article_name,article_content,article_id);
		GetConn.closeConn(conn);
		return data;
	}

	/**
	 * 通过id查询文章
	 * @throws SQLException 
	 */
	public Article queryArticleById(Integer article_id) throws SQLException {
		conn = GetConn.getConn();
		String sql = "select * from article where article_id = ?";
		Article data =  qr.query(conn, sql, new BeanHandler<Article>(Article.class), article_id);
		GetConn.closeConn(conn);
		return data;
	}

	/**
	 * 分页查询所有文章
	 * @throws SQLException 
	 */
	public List<Article> queryPageArticle(int startRow,int pageSize) throws SQLException {
		conn = GetConn.getConn();
		String sql = "select * from article limit ?,?";
		List<Article>  data = qr.query(conn, sql, new BeanListHandler<Article>(Article.class), startRow,pageSize); 
		GetConn.closeConn(conn);
		return data;
	}

	

	/**
	 * 更新赞数量
	 * @throws SQLException 
	 */
	public int updateArticle_praiset(Integer article_praise,Integer article_id) throws SQLException {
		conn = GetConn.getConn();
		String sql = "update article set article_praise = ? where article_id = ?";
		int data =  qr.update(conn, sql, article_praise,article_id);
		GetConn.closeConn(conn);
		return data;
	}

	/**
	 * 更新收藏数量
	 * @throws SQLException 
	 */
	public int updateArticle_collect(Integer article_id,Integer article_collect) throws SQLException {
		conn = GetConn.getConn();
		String sql = "update article set article_collect = ? where article_id = ?";
		int data =  qr.update(conn, sql,article_collect,article_id );
		GetConn.closeConn(conn);
		return data;
	}
	
	/**
	 * 获取所有的文章信息
	 */
	public List<Article> getAllArticle() throws SQLException {
		conn = GetConn.getConn();
		String sql = "select * from article";
		List<Article> data = qr.query(conn, sql, new BeanListHandler<Article>(Article.class));
		GetConn.closeConn(conn);
		return data;
	}

	/**
	 * 通过名字前两个字模糊查文章
	 * @throws SQLException 
	 */
	public List<Article> queryFuzzyQuery(String keyWord) throws SQLException {
		conn = GetConn.getConn();
		String sql = "select * from article where article_name like \"%\"?\"%\"";
		List<Article> data = qr.query(conn, sql, new BeanListHandler<Article>(Article.class), keyWord);
		GetConn.closeConn(conn);
		return data;
	}
	/**
	 * 通过文章名字查id
	 * @throws SQLException 
	 */
	public Integer queryIdByArticleName(String article_name) throws SQLException {
		conn = GetConn.getConn();
		String sql = "select article_id from article where article_name = ?";
		Integer data = qr.query(conn, sql, new ScalarHandler<Integer>(), article_name);
		GetConn.closeConn(conn);
		return data;
	}

	/**
	 * 获得一个用户所有文章
	 * @throws SQLException 
	 */
	public Long queryUserCountArticle(Integer user_id) throws SQLException {
		conn = GetConn.getConn();
		String sql = "select count(*) from article where user_id =?";
		Long data =  qr.query(conn, sql, new ScalarHandler<Long>(),user_id);
		GetConn.closeConn(conn);
		return data;
	}


	/**
	 * 前端后台文章分页查询
	 */
	public List<Article> queryArticlePage(Integer startRow, Integer pageSize)throws SQLException{
		conn = GetConn.getConn();
		String sql = "select * from article limit ?,?";
		List<Article> data = qr.query(conn, sql, new BeanListHandler<Article>(Article.class),startRow,pageSize);
		GetConn.closeConn(conn);
		return data;
	}
	
	/**
	 * 查询所有文章数量
	 * @throws SQLException 
	 */
	public Long queryCountArticle() throws SQLException {
		conn = GetConn.getConn();
		String sql = "select count(*) from article";
		Long data =  qr.query(conn, sql, new ScalarHandler<Long>());
		GetConn.closeConn(conn);
		return data;
	}
	
	/**
	 * 获得一个用户所有文章(分页)
	 * @throws SQLException 
	 */
	public List<Article> queryArticleByUserId(int startRow,int pageSize,Integer user_id) throws SQLException {
		conn = GetConn.getConn();
		String sql = "select * from article where user_id = ? limit ?,?";
		List<Article> data = qr.query(conn, sql, new BeanListHandler<Article>(Article.class), user_id,startRow,pageSize);
		GetConn.closeConn(conn);
		return data;
	}

	
}
