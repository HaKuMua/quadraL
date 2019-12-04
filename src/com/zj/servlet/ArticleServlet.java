package com.zj.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.jws.WebService;
import javax.servlet.annotation.WebServlet;
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
 * 
 * @author ml
 * 
 */
public class ArticleServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
	private ArticleServiceImpl articleService = new ArticleService();
	private CommentServiceImpl commentServiceImpl = new CommentService();
	public String callback;
	public String map;

	// 当前页数
	private Integer currentPage;
	// 总页数
	private Integer countPage;
	// 每页条数
	private Integer pageSize;

	// 返回所有的文章信息
	public void getAllArticle(HttpServletRequest request,
			HttpServletResponse response) throws IOException, SQLException {
		Map<String, Object> map = articleService.getAllArticle(currentPage, pageSize);
		JSONObject obj = new JSONObject(map);
		System.out.println("obj:" + obj);
		response.getWriter().print(callback + "(" + obj + ")");
	}

	/**
	 * 分页显示文章所需信息
	 */
	public void getPageArticleInfo(HttpServletRequest request,
			HttpServletResponse response) {
		// 获取用户设置的当前页
		String articleCurrentPage = request.getParameter("articleCurrentPage");
		Integer articlePresentPage = 1;
		try {
			articlePresentPage = new Integer(articleCurrentPage);
		} catch (Exception e) {
			articlePresentPage = 1;
		}
		//
		try {
			List<Map<String, Object>> list = articleService
					.getPageArticleInfo(articlePresentPage);
			JSONObject obj = new JSONObject(list);
			response.getWriter().print(callback + "(" + obj + ")");
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {

		}
	}

	/**
	 * 一篇文章所有信息 需要article_id
	 */
	public void getOneArticleInfo(HttpServletRequest request,
			HttpServletResponse response) {
		Integer article_id = 1;
		try {
			List<Map<String, Object>> list = articleService
					.getOneArticleInfo(article_id);
			JSONObject obj = new JSONObject(list);
			response.getWriter().print(callback + "(" + obj + ")");
			System.out.println("list---" + list);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {

		}
	}

	/**
	 * 分页显示评论
	 */
	public void getPageCommInfo(HttpServletRequest request,
			HttpServletResponse response) {
		@SuppressWarnings("unchecked")
		Map<String, Object> myMap = (Map<String, Object>) JSON.parse(map);
		Integer article_id = Integer
				.valueOf(myMap.get("article_id").toString());
		String commCurrentPage = request.getParameter("commCurrentPage");
		Integer commPresentPage = 1;
		try {
			commPresentPage = new Integer(commCurrentPage);
		} catch (Exception e) {
			commPresentPage = 1;
		}
		try {
			List<Map<String, Object>> list = commentServiceImpl
					.getPageCommInfo(commPresentPage, article_id);
			JSONObject obj = new JSONObject(list);
			response.getWriter().print(callback + "(" + obj + ")");
			System.out.println(list);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {

		}
	}

	/**
	 * 添加文章
	 * 
	 * @param request
	 * @param response
	 */
	public void addArticle(HttpServletRequest request,
			HttpServletResponse response) {
		Integer user_id = new Integer(request.getParameter("article_id"));
		String article_name = request.getParameter("article_name");
		String article_content = request.getParameter("article_content");
		Integer house_id = new Integer(request.getParameter("house_id"));
		try {
			int count = articleService.addArticle(user_id, article_name,
					article_content, house_id);
			if (count > 0) {

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
