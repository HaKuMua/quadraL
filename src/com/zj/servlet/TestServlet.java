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

import com.zj.service.CheckInPersonService;
import com.zj.service.GrogshopOrderService;
import com.zj.service.HouseService;
import com.zj.service.LandlordService;
import com.zj.service.UserService;

import cn.com.uitl.BaseServlet;

public class TestServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
	private UserService userService = new UserService();
	private HouseService houseService = new HouseService();
	private LandlordService landlordService = new LandlordService();
	private GrogshopOrderService orderService = new GrogshopOrderService();
	private CheckInPersonService cipService = new CheckInPersonService();
	
	public String callback;
	public String house_id;
	public String grogshop_order_id;
	public String landlord_phone;
	public String check_in_person_ID_card;
	
	public void getAllHouseInfo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		List<Map<String, Object>> list = houseService.getAllHouseInfo();
		System.out.println(list);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("list", list);
		JSONObject obj = new JSONObject(map);
		response.getWriter().print(callback+"("+obj+")");
	}
	
	public void getHouseInfoByID(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		Map<String, Object> map = houseService.getHouseInfoByID(Integer.valueOf(house_id));
		System.out.println(map);
		JSONObject obj = new JSONObject(map);
		response.getWriter().print(callback+"("+obj+")");
	}
	//返回订单所有信息
	public void getAllGrogshopOrderInfo(HttpServletRequest request, HttpServletResponse response) throws IOException {
		List<Map<String, Object>> list = orderService.getAllGrogshopOrderInfo();
		System.out.println(list);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("list", list);
		JSONObject obj = new JSONObject(map);
		response.getWriter().print(callback+"("+obj+")");
	}
	//按订单id返回订单信息
	public void getGrogshopOrderInfoByID(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		Map<String, Object> map = orderService.getAllGrogshopOrderInfoByID(Integer.valueOf(grogshop_order_id));
		System.out.println(map);
		JSONObject obj = new JSONObject(map);
		response.getWriter().print(callback+"("+obj+")");
	}
	
	//返回所有入住人员信息
	public void getAllCheckInPersonInfo(HttpServletRequest request, HttpServletResponse response) throws IOException {
		List<Map<String, Object>> list = cipService.getAllCheckInPersonInfo();
		System.out.println(list);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("list", list);
		JSONObject obj = new JSONObject(map);
		response.getWriter().print(callback+"("+obj+")");
	}
	
	//按身份证号返回单条入住人员信息
		public void getCheckInPersonInfoByIDCard(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
			Map<String, Object> map = cipService.getCheckInPersonInfoByIdCard(check_in_person_ID_card);
			System.out.println(map);
			JSONObject obj = new JSONObject(map);
			response.getWriter().print(callback+"("+obj+")");
		}
	
}
