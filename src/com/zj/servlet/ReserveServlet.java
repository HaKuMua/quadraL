package com.zj.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import com.zj.service.ReserveService;
import com.zj.service.impl.ReserveServiceImpl;

import cn.com.util.BaseServlet;

public class ReserveServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
	public String callback;

	private ReserveServiceImpl reserveService = new ReserveService();

	// 当前页数
	private Integer currentPage;
	// 总页数
	private Integer countPage;
	// 每页条数
	private Integer pageSize;
	private String house_id;

	// 返回所有的预定信息
	public void getAllReserve(HttpServletRequest request,
			HttpServletResponse response) throws IOException, SQLException {
		Map<String, Object>map = reserveService.getAllReserve(currentPage, pageSize);
		JSONObject obj = new JSONObject(map);
		System.out.println("obj:" + obj);
		response.getWriter().print(callback + "(" + obj + ")");
	}
	/**
	 * 获取一个房子所有已被预定时间
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	public void getReserveDateByHouseID(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		List<String> list = reserveService.getReserveDate(Integer.valueOf(house_id));
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("list", list);
		System.out.println(list);
		JSONObject obj = new JSONObject(map);
		response.getWriter().print(obj);
	}
}
