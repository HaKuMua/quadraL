
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
	* 分页显示评论方法接口
	 * @param commPresentPage
	 * @param article_id
	 * @return
	 * @throws SQLException
	 */
	List<Map<String, Object>> getPageCommInfo(Integer commPresentPage,Integer article_id) throws SQLException;
	/**
	 * 添加文章
	 */
	Integer addArticleInfo(Map<String, Object> addArticleInfo);
	/**
	 * 删除文章
	 */
	Integer deleteArticleInfo(Integer article_id);
	/**
	 * 查询一个用户所有文章
	 */
	List<Map<String, Object>> getAllArticleByUser(Integer user_id);
}
