package com.zj.control;

import java.sql.SQLException;
import java.util.List;

import com.zj.dao.impl.ArticleDaoImpl;
import com.zj.dao.realize.ArticleDao;
import com.zj.entity.Article;



public class ArticleControl {
	
private ArticleDaoImpl articleDaoImpl = new ArticleDao();
	
	public List<Article> getAllArticle() throws SQLException {
		return articleDaoImpl.getAllArticle();
	}
}
