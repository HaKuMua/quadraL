package com.zj.servlet;


import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import cn.com.uitl.BaseServlet;

import com.zj.service.ArticleService;
import com.zj.service.imp.ArticleServiceImpl;

public class ArticleServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
	private ArticleServiceImpl articleService = new ArticleService();
	public String callback;
	/**
	 * 分页显示文章所需信息
	 */
	public void getPageArticleInfo(HttpServletRequest request,HttpServletResponse response) {
		//获取用户设置的当前页
		String articleCurrentPage  = request.getParameter("articleCurrentPage");
		Integer articlePresentPage = 1;
		try{
			articlePresentPage = new Integer(articleCurrentPage);
		}catch(Exception e){
			articlePresentPage = 1;
		}	
		//
		try {
			List<Map<String, Object>> list = articleService.getPageArticleInfo(articlePresentPage);
			JSONObject obj = new JSONObject(list);
			response.getWriter().print(callback+"("+obj+")");
		} catch (SQLException e) {
			e.printStackTrace();
		} catch(IOException e) {
			
		}
	}
	
	/**
	 * 一篇文章所有信息
	 * @throws SQLException 
	 */
	public void getOneArticleInfo(HttpServletRequest request,HttpServletResponse response) {
		Integer article_id = new Integer(request.getParameter("article_id"));
		try {
			List<Map<String, Object>> list = articleService.getOneArticleInfo(article_id);
			JSONObject obj = new JSONObject(list);
			response.getWriter().print(callback+"("+obj+")");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	catch(IOException e) {
			
		}
	}
	/**
	 * 分页显示评论
	 */
	public void getPageCommInfo(HttpServletRequest request,HttpServletResponse response) {
		Integer article_id = 1;
		String commCurrentPage  = request.getParameter("commCurrentPage");
		Integer commPresentPage = 1;
		try{
			commPresentPage = new Integer(commCurrentPage);
		}catch(Exception e){
			commPresentPage = 1;
		}
		try {
			List<Map<String, Object>> list = articleService.getPageCommInfo(commPresentPage, article_id);
			JSONObject obj = new JSONObject(list);
			response.getWriter().print(callback+"("+obj+")");
			System.out.println(list);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	catch(IOException e) {
			
		}
	}
}
