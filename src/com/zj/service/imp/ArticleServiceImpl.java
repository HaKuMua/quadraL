package com.zj.service.imp;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface ArticleServiceImpl {
	List<Map<String, Object>> getAllArticle();
	List<Map<String, Object>> getPageArticleInfo(Integer articlePresentPage) throws SQLException;
	List<Map<String, Object>> getOneArticleInfo(Integer article_id) throws SQLException;
	List<Map<String, Object>> getPageCommInfo(Integer commPresentPage,Integer article_id) throws SQLException;
}
