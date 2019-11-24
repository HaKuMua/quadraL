package com.zj.servlet;



import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import com.zj.service.HouseService;
import com.zj.service.UserService;

import cn.com.uitl.BaseServlet;

public class TestServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
	private UserService userService = new UserService();
	private HouseService houseService = new HouseService();
	public String callback;
	public String house_id;
	
	public void getAllHouseInfo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		List<Map<String, Object>> list = houseService.getAllHouseInfo();
		System.out.println(list);
		JSONObject obj = new JSONObject(list);
		response.getWriter().print(callback+"("+obj+")");
	}
	
	public void getHouseInfoByID(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		Map<String, Object> map = houseService.getHouseInfoByID(Integer.valueOf(house_id));
		System.out.println(map);
		JSONObject obj = new JSONObject(map);
		response.getWriter().print(callback+"("+obj+")");
	}
	
}
