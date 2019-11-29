package com.zj.servlet;



import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import com.zj.service.CommentService;
import com.zj.service.imp.CommentServiceImpl;

import cn.com.uitl.BaseServlet;
/**
 * 文章评论信息审核 servlet
 * @author LanceEdward
 *
 */
public class CommenServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
	
	private CommentServiceImpl commentService = new CommentService();
	
	public String callback;
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
}
