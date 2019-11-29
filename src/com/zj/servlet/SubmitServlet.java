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


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.zj.service.LandlordService;
import com.zj.service.impl.LandlordServiceImpl;

import cn.com.uitl.BaseServlet;

public class SubmitServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
	private LandlordServiceImpl landlordService = new LandlordService();
	public String map;
	public String callback;
	public void addLandlordInfo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		Map<String, Object> myMap = (Map<String, Object>) JSON.parse(map);
		String str = landlordService.addLandlordInfo(myMap);
		Map<String, String> hint = new HashMap<String, String>();
		hint.put("hint", str);
		JSONObject json = new JSONObject(hint);
		response.getWriter().print(callback+"("+json+")");
	}
	
}
