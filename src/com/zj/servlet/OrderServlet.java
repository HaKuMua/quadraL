package com.zj.servlet;



import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import com.zj.service.GrogshopOrderService;
import com.zj.service.imp.GrogshopOrderServiceImpl;

import cn.com.uitl.BaseServlet;

public class OrderServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
	
	private GrogshopOrderServiceImpl orderService = new GrogshopOrderService();
	
	public String callback;
	public String grogshop_order_id;
	
	// 返回订单所有信息
	public void getAllGrogshopOrderInfo(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		List<Map<String, Object>> list = orderService.getAllGrogshopOrderInfo();
		System.out.println(list);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("list", list);
		JSONObject obj = new JSONObject(map);
		response.getWriter().print(callback + "(" + obj + ")");
	}

	// 按订单id返回订单信息
	public void getGrogshopOrderInfoByID(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		Map<String, Object> map = orderService
				.getAllGrogshopOrderInfoByID(Integer.valueOf(grogshop_order_id));
		System.out.println(map);
		JSONObject obj = new JSONObject(map);
		response.getWriter().print(callback + "(" + obj + ")");
	}
}
