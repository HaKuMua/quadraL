package com.zj.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

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

	public String callback;
	// 当前页数
	private Integer currentPage;
	// 总页数
	private Integer countPage;
	// 每页条数
	private Integer pageSize;

	// 返回所有文章评论信息
	public void getAllComment(HttpServletRequest request,
			HttpServletResponse response) throws IOException, SQLException {
		Map<String, Object> map = commentService.getAllComment(currentPage,
				pageSize);
		JSONObject obj = new JSONObject(map);
		System.out.println("obj:" + obj);
		response.getWriter().print(callback + "(" + obj + ")");
	}
}
