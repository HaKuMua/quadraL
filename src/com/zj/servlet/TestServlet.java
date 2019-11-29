
package com.zj.servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import com.zj.service.ArticleService;
import com.zj.service.CheckInPersonService;
import com.zj.service.CommentService;
import com.zj.service.GrogshopOrderService;
import com.zj.service.HouseService;
import com.zj.service.LandlordService;
import com.zj.service.ReserveService;
import com.zj.service.UserService;
import com.zj.service.imp.ArticleServiceImpl;
import com.zj.service.imp.CheckInPersonServiceImpl;
import com.zj.service.imp.CommentServiceImpl;
import com.zj.service.imp.GrogshopOrderServiceImpl;
import com.zj.service.imp.HouseServiceImpl;
import com.zj.service.imp.LandlordServiceImpl;
import com.zj.service.imp.ReserveServiceImpl;
import com.zj.service.imp.UserServiceImpl;

import cn.com.uitl.BaseServlet;

public class TestServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
	
	public String callback;
	
}
