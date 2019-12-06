package com.zj.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import com.zj.entity.User;
import com.zj.service.UserService;
import com.zj.service.impl.UserServiceImpl;

import cn.com.util.BaseServlet;
import cn.com.util.PageUtil;

public class UserInfoServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;

	private UserServiceImpl userService = new UserService();

	public String callback;
	public String user_phone;
	// 当前页数
	private Integer page;
	// 总页数
	private Integer countPage;
	// 每页条数
	private Integer limit;

	// 返回所有用户信息
	public void getAllUserInfo(HttpServletRequest request,
			HttpServletResponse response) throws IOException, SQLException {
		Map<String, Object> map = userService.getAllUserInfo(limit, page);
		JSONObject obj = new JSONObject(map);
		System.out.println("obj:" + obj);
		response.getWriter().print(callback + "(" + obj + ")");
		
	}
	// 按手机号返回单条用户信息
	public void getUserInfoByUserPhone(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		Map<String, Object> map = userService.getUserInfoByPhone(user_phone);
		System.out.println(map);
		JSONObject obj = new JSONObject(map);
		response.getWriter().print(callback + "(" + obj + ")");
	}
}
