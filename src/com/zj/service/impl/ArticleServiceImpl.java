
﻿package com.zj.service.imp;
package com.zj.service.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.zj.entity.Article;

/**
 * 
 * @author lijia
 *文章service层接口
 */
public interface ArticleServiceImpl {
	/**
	 * 获取所有文章方法接口
	 */
	List<Map<String, Object>> getAllArticle();
	/**
	 * 分页显示文章所需信息方法接口
	 * @param articlePresentPage 当前页
	 * @throws SQLException
	 */
	List<Map<String, Object>> getPageArticleInfo(Integer articlePresentPage) throws SQLException;
	/**
	 * 一篇文章所有信息
	 * 一篇文章所有信息方法接口
	 * @throws SQLException 
	 */
	List<Map<String, Object>> getOneArticleInfo(Integer article_id) throws SQLException;
	/**
	 * 添加文章
	 * @throws SQLException 
	 */
	int addArticle(Integer user_id,String article_name,String article_content) throws SQLException;
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
	* 分页显示评论方法接口
	 * @param commPresentPage
	 * @param article_id
	 * @return
	 * @throws SQLException
	 */
	List<Map<String, Object>> getPageCommInfo(Integer commPresentPage,Integer article_id) throws SQLException;
}
