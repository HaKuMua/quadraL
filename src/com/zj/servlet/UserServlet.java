package com.zj.servlet;


import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import cn.com.uitl.BaseServlet;
import cn.com.uitl.CheckoutEmail;
import cn.com.uitl.CheckoutIDCard;
import cn.com.uitl.CheckoutPhoneNumber;

import com.alibaba.fastjson.JSON;
import com.zj.entity.User;
import com.zj.service.UserService;
import com.zj.service.impl.UserServiceImpl;
/**
 * 用户
 * @author ml
 *
 */
public class UserServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
	private UserServiceImpl userServiceImpl = new UserService();
	
	public String map;
	public String callback;
	/**
	 * 邮箱/电话密码登录
	 */
	public void login(HttpServletRequest request,HttpServletResponse response) {
		@SuppressWarnings("unchecked")
		Map<String, Object> myMap = (Map<String, Object>) JSON.parse(map);
		Map<String, Object> sendMap = new HashMap<String, Object>();
		sendMap = userServiceImpl.login(myMap);
		JSONObject obj = new JSONObject(sendMap);
		try {
			response.getWriter().print(callback + "(" + obj + ")");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * 手机号登录，若手机号存在则用户注册
	 */
	public void phoneLogin(HttpServletRequest request,HttpServletResponse response) {
		@SuppressWarnings("unchecked")
		Map<String, Object> myMap = (Map<String, Object>) JSON.parse(map);
		Map<String, Object> sendMap = new HashMap<String, Object>();
		sendMap = userServiceImpl.phoneLogin(myMap);
		try {
			JSONObject obj = new JSONObject(sendMap);
			response.getWriter().print(callback+"("+obj+")");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 用户设置密码
	 */
	public void setUserPwd(HttpServletRequest request,HttpServletResponse response) {
		@SuppressWarnings("unchecked")
		Map<String, Object> myMap = (Map<String, Object>) JSON.parse(map);
		Map<String, Object> sendMap = new HashMap<String, Object>();
		sendMap = userServiceImpl.setUserPwd(myMap);
		try {
			JSONObject obj = new JSONObject(sendMap);
			response.getWriter().print(callback+"("+obj+")");
		} catch (IOException e) {
			e.printStackTrace();
		}	
	}
	/**
	 * 信息基本修改
	 */
	public void updateBasicInfo(HttpServletRequest request,HttpServletResponse response) {
		@SuppressWarnings("unchecked")
		Map<String, Object> myMap = (Map<String, Object>) JSON.parse(map);
		Map<String, Object> sendMap = new HashMap<String, Object>();
		sendMap = userServiceImpl.updateBasicInfo(myMap);
	    try {
			JSONObject obj = new JSONObject(sendMap);
			response.getWriter().print(callback+"("+obj+")");
		} catch (IOException e) {

			e.printStackTrace();
		}
	}
	/**
	 * 用户修改密码
	 */
	public void updateUserPwd(HttpServletRequest request,HttpServletResponse response) {
		@SuppressWarnings("unchecked")
		Map<String, Object> myMap = (Map<String, Object>) JSON.parse(map);
		Map<String, Object> sendMap = new HashMap<String, Object>();
		sendMap = userServiceImpl.updateUserPwd(myMap);
		try {
			JSONObject obj = new JSONObject(sendMap);
			response.getWriter().print(callback+"("+obj+")");
		} catch (IOException e) {

			e.printStackTrace();
		}
	}
	/**
	 * 用户上传头像
	 */
	public void addUserHead(HttpServletRequest request,HttpServletResponse response) {
		@SuppressWarnings("unchecked")
		Map<String, Object> myMap = (Map<String, Object>) JSON.parse(map);
		Map<String, Object> sendMap = new HashMap<String, Object>();
		sendMap = userServiceImpl.addUserHead(myMap);
		try {
			JSONObject obj = new JSONObject(sendMap);
			response.getWriter().print(callback+"("+obj+")");
		} catch (IOException e) {

			e.printStackTrace();
		}
	}
	/**
	 * 用户实名
	 * @throws SQLException 
	 */
	public void realNameInfo(HttpServletRequest request,HttpServletResponse response) throws SQLException {
		@SuppressWarnings("unchecked")
		Map<String, Object> myMap = (Map<String, Object>) JSON.parse(map);
		Map<String, Object> sendMap = new HashMap<String, Object>();
		sendMap = userServiceImpl.realNameInfo(myMap);
		try {
			JSONObject obj = new JSONObject(sendMap);
			response.getWriter().print(callback+"("+obj+")");
		} catch (IOException e) {

			e.printStackTrace();
		}
	}
}
