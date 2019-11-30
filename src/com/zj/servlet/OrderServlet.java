package com.zj.servlet;



import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.json.JSONObject;

import com.alibaba.fastjson.JSON;
import com.zj.service.GrogshopOrderService;
import com.zj.service.impl.GrogshopOrderServiceImpl;

import cn.com.uitl.BaseServlet;

public class OrderServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
	private GrogshopOrderServiceImpl orderService = new GrogshopOrderService();
	private Logger log = Logger.getLogger(OrderServlet.class);
	public String callback;
	public String grogshop_order_id;
	public String grogshopOrderInfo;
	public String checkInPersonInfo;
	
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
	
	public void addOrderInfo(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		@SuppressWarnings("unchecked")
		Map<String, Object> grogshopOrder = (Map<String, Object>) JSON.parse(grogshopOrderInfo);
		@SuppressWarnings("unchecked")
		List<Map<String, Object>> checkInPerson = (List<Map<String, Object>>) JSON.parse(checkInPersonInfo);
		log.info(grogshopOrder);
		log.info(checkInPerson);
		String str = orderService.addGrogshopOrderInfo(grogshopOrder, checkInPerson);
		log.debug(str);
		Map<String, String> map = new HashMap<String, String>();
		map.put("hint", str);
		JSONObject json = new JSONObject(map);
		if(str == null)
			response.getWriter().print(callback+"(信息有误或余额不足)");
		else
			response.getWriter().print(callback+"("+json+")");
	}
}