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

	private ReserveServiceImpl reserveService = new ReserveService();

	public String callback;
	// 当前页数
	private Integer currentPage;
	// 总页数
	private Integer countPage;
	// 每页条数
	private Integer pageSize;

	// 返回所有的预定信息
	public void getAllReserve(HttpServletRequest request,
			HttpServletResponse response) throws IOException, SQLException {
		Map<String, Object>map = reserveService.getAllReserve(currentPage, pageSize);
		JSONObject obj = new JSONObject(map);
		System.out.println("obj:" + obj);
		response.getWriter().print(callback + "(" + obj + ")");
	}
}
