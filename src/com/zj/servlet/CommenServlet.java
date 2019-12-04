package com.zj.servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import com.alibaba.fastjson.JSON;
import com.zj.service.CommentService;
import com.zj.service.impl.CommentServiceImpl;

import cn.com.util.BaseServlet;

/**
 * 文章评论信息审核 servlet
 * 
 * @author LanceEdward
 *
 */
public class CommenServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;

	private CommentServiceImpl commentService = new CommentService();
	private String map;
	private String callback;

	// 返回所有文章评论信息
	public void getAllComment(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		List<Map<String, Object>> list = commentService.getAllComment();
		System.out.println(list);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("list", list);
		JSONObject obj = new JSONObject(map);
		response.getWriter().print(callback + "(" + obj + ")");
	}
	
	/**
	 * 添加评论
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	public void addComment(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		@SuppressWarnings("unchecked")
		Map<String, Object> myMap = (Map<String, Object>) JSON.parse(map);
		Map<String, Object> sendMap = new HashMap<String, Object>();
		sendMap = commentService.addComment(myMap);
		System.out.println(sendMap);
		JSONObject obj = new JSONObject(sendMap);
		response.getWriter().print(callback + "(" + obj + ")");
	}
}
