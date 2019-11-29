package com.zj.servlet;



import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.json.JSONObject;


import com.alibaba.fastjson.JSON;
import com.zj.service.UserService;
import com.zj.service.impl.UserServiceImpl;

import cn.com.uitl.BaseServlet;

public class SubmitServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
	private UserServiceImpl userService = new UserService();
	private Logger log = Logger.getLogger(SubmitServlet.class);
	public String map;
	public String callback;
	public void addUserInfo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		@SuppressWarnings("unchecked")
		Map<String, Object> myMap = (Map<String, Object>) JSON.parse(map);
		log.debug(myMap);
		//String str = userService.addUserInfo(myMap);
		Map<String, String> hint = new HashMap<String, String>();
		//hint.put("hint", str);
		JSONObject json = new JSONObject(hint);
		response.getWriter().print(callback+"("+json+")");
	}
	
}
