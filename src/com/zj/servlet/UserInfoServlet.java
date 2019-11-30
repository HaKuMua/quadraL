package com.zj.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import com.zj.service.UserService;
import com.zj.service.impl.UserServiceImpl;

import cn.com.uitl.BaseServlet;

public class UserInfoServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
	
	private UserServiceImpl userService = new UserService();
	
	public String callback;
	public String user_phone;
	
	//返回所有用户信息
	public void getAllUserInfo(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		List<Map<String, Object>> list = userService.getAllUserInfo();
		System.out.println(list);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("list", list);
		JSONObject obj = new JSONObject(map);
		response.getWriter().print(callback + "(" + obj + ")");
	}
	
	//按手机号返回单条用户信息
	public void getUserInfoByUserPhone(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		Map<String, Object> map = userService.getUserInfoByPhone(user_phone);
		System.out.println(map);
		JSONObject obj = new JSONObject(map);
		response.getWriter().print(callback + "(" + obj + ")");
	}
}
