package com.zj.dao.impl;

import java.sql.SQLException;
import java.util.List;

import com.zj.entity.Article;
import com.zj.entity.GrogshopOrder;

/**
 * 
 * @author lijia
 *文章数据库操作接口
 */
public interface ArticleDaoImpl {
	/**
	 * 获取所有文章方法接口
	 */
	List<Article> getAllArticle() throws SQLException;
	/**
	 * 获取文章分页接口
	 * @param startRow
	 * @param pageSize
	 * @return
	 * @throws SQLException
	 */
	List<Article> queryArticlePage(Integer startRow,Integer pageSize)  throws SQLException;
	
	/**
	 * 添加文章
	 * @throws SQLException 
	 */
	int addArticle(Integer user_id,String article_name,String article_content,Integer house_id) throws SQLException;
	/**
	 * 通过id删除文章
	 * @throws SQLException 
	 */
	int deleteArticleById(Integer article_id) throws SQLException;
	/**
	 * 修改文章
	 * @throws SQLException 
	 */
	int updateArticle(Integer article_id,String article_name,String article_content) throws SQLException;
	/**
	 * 通过id查询文章
	 * @throws SQLException 
	 */
	Article queryArticleById(Integer article_id) throws SQLException;
	/**
	 * 分页查询所有文章
	 * @throws SQLException 
	 */
	List<Article> queryPageArticle(int startRow,int pageSize) throws SQLException;
	/**
	 * 查询所有文章数量
	 * @throws SQLException 
	 */
	Long queryCountArticle() throws SQLException;
	/**
	 * 更新赞数量
	 * @throws SQLException 
	 */
	int updateArticle_praiset(Integer article_praise,Integer article_id) throws SQLException;
	/**
	 * 更新收藏数量
	 * @throws SQLException 
	 */
	int updateArticle_collect(Integer article_id,Integer article_collect) throws SQLException;
	/**
	 * 通过名字前两个字模糊查文章
	 * @throws SQLException 
	 */
	List<Article> queryFuzzyQuery(String keyWord) throws SQLException;
}
