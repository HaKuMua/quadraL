package com.zj.servlet;



import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import com.zj.service.LandlordService;

import cn.com.uitl.BaseServlet;

public class SubmitServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
	private LandlordService landlordService = new LandlordService();
	public String map;
	public String callback;
	public void addLandlordInfo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		System.out.println(map);
	}
	
}
