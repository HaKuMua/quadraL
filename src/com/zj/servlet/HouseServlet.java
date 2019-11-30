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
import com.zj.service.HouseService;
import com.zj.service.impl.HouseServiceImpl;

import cn.com.uitl.BaseServlet;

public class HouseServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
	private HouseServiceImpl houseService = new HouseService();
	private Logger log = Logger.getLogger(HouseServlet.class);
	public String houseMap;
	public String houseImgMap;
	public String callback;
	
	public void addHouseInfo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		@SuppressWarnings("unchecked")
		Map<String, Object> house = (Map<String, Object>) JSON.parse(houseMap);
		@SuppressWarnings("unchecked")
		List<Map<String, Object>> houseImg = (List<Map<String, Object>>) JSON.parse(houseImgMap);
		log.info(house);
		log.info(houseImg);
		//String houseStr = houseService.addHouseInfo(house);
		//String houseImgStr = houseService.addHouseImg(houseImg);
		Map<String, String> hint = new HashMap<String, String>();
		hint.put("houseHint", "111");
		hint.put("houseImgHint", "222");
		JSONObject json = new JSONObject(hint);
		response.getWriter().print(callback+"("+json+")");
	}
}
