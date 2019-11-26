package com.zj.dao.impl;

import java.sql.SQLException;
import java.util.List;

import com.zj.entity.Article;
import com.zj.entity.Landlord;

/**
 * 
 * @author lijia
 *文章数据库操作接口
 */
public interface ArticleDaoImpl {
	/*
	 * 获取所有文章方法接口
	 */
	public List<Article> getAllArticle() throws SQLException;
}
