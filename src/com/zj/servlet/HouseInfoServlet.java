package com.zj.servlet;



import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileUploadException;
import org.json.JSONObject;

import com.zj.service.HouseService;
import com.zj.service.impl.HouseServiceImpl;

import cn.com.util.BaseServlet;
import cn.com.util.FileLoadServletUtil;

public class HouseInfoServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
	
	private HouseServiceImpl houseService = new HouseService();
	
	public String callback;
	public String house_id;
	
	//返回所有房子信息
	public void getAllHouseInfo(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		List<Map<String, Object>> list = houseService.getAllHouseInfo();
		System.out.println(list);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("list", list);
		JSONObject obj = new JSONObject(map);
		response.getWriter().print(callback + "(" + obj + ")");
	}

	// 按照房子id返回信息
	public void getHouseInfoByID(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		System.out.println(house_id);
		Map<String, Object> map = houseService.getHouseInfoByID(Integer
				.valueOf(house_id));
		System.out.println(map);
		JSONObject obj = new JSONObject(map);
		response.getWriter().print(callback + "(" + obj + ")");
	}
	
}
