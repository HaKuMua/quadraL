package com.zj.dao.impl;

import java.sql.SQLException;
import java.util.List;

import com.zj.entity.ArticleImg;

/**
 * 
 * @author liyifeng
 *文章图片数据库操作接口
 */
public interface ArticleImgDaoImpl {
	/**
	 * 给文章添加图片
	 * @throws SQLException 
	 */
	int addArticleImg(Integer article_id,String image_url) throws SQLException;
	/**
	 * 通过id删除文章图片
	 * @throws SQLException 
	 */
	int deleteArticleImg(Integer image_id) throws SQLException;
	/**
	 * 通过id查询文章图片
	 * @throws SQLException 
	 */
	ArticleImg queryArticleImgById(Integer image_id) throws SQLException;
	/**
	 * 通过文章id查询一篇文章的图片
	 * @throws SQLException 
	 */
	List<ArticleImg> queryArticleImgByArticleId(Integer article_id) throws SQLException;
	/**
	 * 查询所有文章图片
	 * @throws SQLException 
	 */
	List<ArticleImg> queryArticleImg() throws SQLException;
}
