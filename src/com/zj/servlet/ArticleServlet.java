package com.zj.servlet;


import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import cn.com.util.BaseServlet;

import com.alibaba.fastjson.JSON;
import com.zj.service.ArticleService;
import com.zj.service.CommentService;
import com.zj.service.impl.ArticleServiceImpl;
import com.zj.service.impl.CommentServiceImpl;
/**
 * 文章
 * @author ml
 *
 */
public class ArticleServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
	private ArticleServiceImpl articleService = new ArticleService();
	private CommentServiceImpl commentServiceImpl = new CommentService();
	public String callback;
	public String map;
	public Integer articlePresentPage;
	public Integer article_id;
	public Integer commPresentPage;
	public String articleImgMap;
	@SuppressWarnings("unchecked")
	Map<String, Object> myMap = (Map<String, Object>) JSON.parse(map);
//	Integer article_id = Integer.valueOf(myMap.get("article_id").toString());
//	Integer house_id = Integer.valueOf(myMap.get("house_id").toString());
	
	// 返回所有的文章信息
	public void getAllArticle(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		List<Map<String, Object>> list = articleService.getAllArticle();
		System.out.println(list);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("list", list);
		JSONObject obj = new JSONObject(map);
		response.getWriter().print(callback + "(" + obj + ")");
	}
	/**
	 * 分页显示文章所需信息
	 * @throws SQLException 
	 * @throws IOException 
	 */
	public void getPageArticleInfo(HttpServletRequest request,HttpServletResponse response) throws SQLException, IOException {
		Integer articlePresentPage = Integer.valueOf(JSON.parse("articlePresentPage").toString());
		List<Map<String, Object>> list = articleService.getPageArticleInfo(articlePresentPage);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("pageArticleInfo", list);
		JSONObject json = new JSONObject(map);
		response.getWriter().print(callback+"("+json+")");
	}	
	
	/**
	 * 一篇文章所有信息
	 * 需要article_id
	 * @throws SQLException 
	 * @throws IOException 
	 */
	public void getOneArticleInfo(HttpServletRequest request,HttpServletResponse response) throws SQLException, IOException {
		Integer article_id = Integer.valueOf(JSON.parse("article_id").toString());
		List<Map<String, Object>> list = articleService.getOneArticleInfo(article_id);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("oneArticleInfo", list);
		JSONObject json = new JSONObject(map);
		response.getWriter().print(callback+"("+json+")");
	}
	/**
	 * 分页显示评论
	 * @throws SQLException 
	 * @throws IOException 
	 */
	public void getPageCommInfo(HttpServletRequest request,HttpServletResponse response) throws SQLException, IOException {
		Integer article_id = Integer.valueOf(JSON.parse("article_id").toString());
		Integer commPresentPage = Integer.valueOf(JSON.parse("commPresentPage").toString());
		List<Map<String, Object>> list = articleService.getPageCommInfo(commPresentPage, article_id);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("pageCommentInfo", list);
		JSONObject json = new JSONObject(map);
		response.getWriter().print(callback+"("+json+")");
	}
	/**
	 * 添加文章
	 * @param request
	 * @param response
	 * @throws SQLException 
	 */
	public void addArticle(HttpServletRequest request,HttpServletResponse response) throws SQLException {
		@SuppressWarnings("unchecked")
		Map<String, Object> myMap = (Map<String, Object>) JSON.parse(map);
		Map<String, Object> sendMap = new HashMap<String, Object>();
		sendMap = articleService.addArticle(myMap);
		JSONObject obj = new JSONObject(sendMap);
		try {
			response.getWriter().print(callback + "(" + obj + ")");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * 添加文章图片
	 */
	public void login(HttpServletRequest request,HttpServletResponse response) {
		
	}
}
