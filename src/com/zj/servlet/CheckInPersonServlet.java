package com.zj.servlet;



import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import com.zj.service.CheckInPersonService;
import com.zj.service.impl.CheckInPersonServiceImpl;

import cn.com.util.BaseServlet;

public class CheckInPersonServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
	
	private CheckInPersonServiceImpl cipService = new CheckInPersonService();
	
	public String callback;
	public String check_in_person_ID_card;
	
	// 返回所有入住人员信息
	public void getAllCheckInPersonInfo(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		List<Map<String, Object>> list = cipService.getAllCheckInPersonInfo();
		System.out.println(list);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("list", list);
		JSONObject obj = new JSONObject(map);
		response.getWriter().print(callback + "(" + obj + ")");
	}

	// 按身份证号返回单条入住人员信息
	public void getCheckInPersonInfoByIDCard(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		Map<String, Object> map = cipService
				.getCheckInPersonInfoByIdCard(check_in_person_ID_card);
		System.out.println(map);
		JSONObject obj = new JSONObject(map);
		response.getWriter().print(callback + "(" + obj + ")");
	}
}
