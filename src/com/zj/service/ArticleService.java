package com.zj.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.zj.control.ArticleControl;
import com.zj.control.UserControl;
import com.zj.entity.Article;
import com.zj.entity.Comment;

public class ArticleService {
	private ArticleControl articleControl = new ArticleControl();

	public List<Map<String, Object>> getAllArticle(){
		List<Map<String, Object>> list = null;
		try {
			List<Article> articleList = articleControl.getAllArticle();
			if(articleList != null){
				list = new ArrayList<Map<String,Object>>();
				for(Article article : articleList){
					Map<String, Object> map = new HashMap<String, Object>();
					map.put("article_id", article.getArticle_id());
					map.put("user_id", article.getUser_id());
					map.put("article_name", article.getArticle_name());
					map.put("article_content", article.getArticle_content());
					map.put("article_date", article.getArticle_date());
					map.put("article_praise", article.getArticle_praise());
					map.put("article_collect", article.getArticle_collect());
					list.add(map);
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
}
