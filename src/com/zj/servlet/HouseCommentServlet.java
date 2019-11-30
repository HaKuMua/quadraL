package com.zj.servlet;



import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import com.zj.dao.HouseCommentDao;
import com.zj.dao.impl.HouseCommentDaoImpl;
import com.zj.service.HouseCommentService;
import com.zj.service.impl.HouseCommentServiceImpl;

import cn.com.uitl.BaseServlet;

public class HouseCommentServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
	
	private HouseCommentServiceImpl hCommentService =  new HouseCommentService();
	
	public String callback;
	
	// 返回所有文章评论信息
		public void getAllHouseComment(HttpServletRequest request,
				HttpServletResponse response) throws IOException {
			List<Map<String, Object>> list = hCommentService.getAllHouseComment();
			System.out.println(list);
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("list", list);
			JSONObject obj = new JSONObject(map);
			response.getWriter().print(callback + "(" + obj + ")");
		}
}
