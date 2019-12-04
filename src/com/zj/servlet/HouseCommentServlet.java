package com.zj.servlet;



import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import com.zj.dao.UserDao;
import com.zj.dao.impl.UserDaoImpl;
import com.zj.service.HouseCommentService;
import com.zj.service.UserService;
import com.zj.service.impl.HouseCommentServiceImpl;
import com.zj.service.impl.UserServiceImpl;

import cn.com.util.BaseServlet;

public class HouseCommentServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
	private UserServiceImpl userServiceImpl = new UserService();
	private HouseCommentServiceImpl hCommentService =  new HouseCommentService();
	private String user_id;
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
	/**
	 * 通过用户ID获得所有此用户的房子的评论
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	public void getHouseCommentByLendlordID(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		List<Map<String, Object>> list = hCommentService.getHouseCommentByLendlordID(Integer.valueOf(user_id));
		Map<String, Object> userInfoMap = userServiceImpl.getUserInfoByUserID(Integer.valueOf(user_id));
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("list", list);
		map.put("userInfo", userInfoMap);
		JSONObject obj = new JSONObject(map);
		response.getWriter().print(callback + "(" + obj + ")");
	}
}
