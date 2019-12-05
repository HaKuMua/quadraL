package com.zj.servlet;
package com.zj.servlet;

import java.io.IOException;
import java.sql.SQLException;
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
	/**
	 * 分页显示评论
	 * @throws SQLException 
	 * @throws IOException 
	 */
	public void getPageCommInfo(HttpServletRequest request,HttpServletResponse response) throws SQLException, IOException {
		Integer article_id = Integer.valueOf(JSON.parse("article_id").toString());
		Integer commPresentPage = Integer.valueOf(JSON.parse("commPresentPage").toString());
		List<Map<String, Object>> list = commentService.getPageCommInfo(commPresentPage, article_id);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("pageCommentInfo", list);
		JSONObject json = new JSONObject(map);
		response.getWriter().print(callback+"("+json+")");
	}

	/**
	 * 添加评论
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	public void addComment(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		System.out.println(map);
		@SuppressWarnings("unchecked")
		Map<String, Object> myMap = (Map<String, Object>) JSON.parse(map);
		Map<String, Object> sendMap = new HashMap<String, Object>();
		sendMap = commentService.addComment(myMap);
		System.out.println(sendMap);
		JSONObject obj = new JSONObject(sendMap);
		response.getWriter().print(callback + "(" + obj + ")");
	}
}
