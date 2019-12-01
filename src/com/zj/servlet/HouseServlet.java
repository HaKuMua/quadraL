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

import cn.com.util.BaseServlet;

public class HouseServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
	private HouseServiceImpl houseService = new HouseService();
	private Logger log = Logger.getLogger(HouseServlet.class);
	public String houseMap;
	public String houseImgMap;
	public String callback;
	public String user_id;
	public String selectMap;
	/**
	 * 添加一个房子信息
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void addHouseInfo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		@SuppressWarnings("unchecked")
		Map<String, Object> house = (Map<String, Object>) JSON.parse(houseMap);
		@SuppressWarnings("unchecked")
		List<Map<String, Object>> houseImg = (List<Map<String, Object>>) JSON.parse(houseImgMap);
		log.info(house);
		log.info(houseImg);
		String houseStr = houseService.addHouseInfo(house);
		String houseImgStr = houseService.addHouseImg(houseImg);
		Map<String, String> hint = new HashMap<String, String>();
		hint.put("houseHint", houseStr);
		hint.put("houseImgHint", houseImgStr);
		JSONObject json = new JSONObject(hint);
		response.getWriter().print(callback+"("+json+")");
	}
	/**
	 * 通过用户ID获取此用户的所有房子
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void getHouseInfoByUserID(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		Integer userID = Integer.valueOf(JSON.parse(user_id).toString());
		List<Map<String, Object>> list = houseService.getHouseByID(userID);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("houseList", list);
		JSONObject json = new JSONObject(map);
		response.getWriter().print(callback+"("+json+")");
	}
	/**
	 * 通过选择返回一组房子信息
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void getHouseInfoBySelect(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		@SuppressWarnings("unchecked")
		Map<String, Object> select = (Map<String, Object>) JSON.parse(selectMap);
		String reserve_date = select.get("reserve_date").toString();
		String check_out_date = select.get("check_out_date").toString();
		String house_address = select.get("house_address").toString();
		List<Map<String, Object>> list = houseService.getHouseByDateOrAddress(reserve_date, check_out_date, house_address);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("houseList", list);
		JSONObject json = new JSONObject(map);
		response.getWriter().print(callback+"("+json+")");
	}
}
