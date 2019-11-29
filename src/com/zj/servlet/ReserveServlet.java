package com.zj.servlet;



import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import com.zj.service.ReserveService;
import com.zj.service.imp.ReserveServiceImpl;

import cn.com.uitl.BaseServlet;

public class ReserveServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
	
	private ReserveServiceImpl reserveService = new ReserveService();
	
	public String callback;
	
	//返回所有的预定信息 
	public void getAllReserve(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		List<Map<String, Object>> list = reserveService.getAllReserve();
		System.out.println(list);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("list", list);
		JSONObject obj = new JSONObject(map);
		response.getWriter().print(callback + "(" + obj + ")");
	}
}
