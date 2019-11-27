package com.zj.dao.realize;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import cn.com.uitl.GetConn;

import com.zj.dao.impl.ArticleDaoImpl;
import com.zj.entity.Article;
import com.zj.entity.Comment;

public class ArticleDao implements ArticleDaoImpl{
	QueryRunner qr = new QueryRunner();
	Connection conn = new GetConn().getConn();
	@Override
	public List<Article> getAllArticle() throws SQLException {
		String sql = "select * from article";
		return qr.query(conn, sql, new BeanListHandler<Article>(Article.class));
	}

}
